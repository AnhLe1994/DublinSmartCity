package com.londonsmartcity.service.environment;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.londonsmartcity.proto.EnvironmentData;
import com.londonsmartcity.proto.EnvironmentMonitorRequest;
import com.londonsmartcity.proto.EnvironmentRequest;
import com.londonsmartcity.proto.EnvironmentResponse;
import com.londonsmartcity.proto.EnvironmentServiceGrpc;
import com.londonsmartcity.proto.EnvironmentalEvent;
import com.londonsmartcity.proto.EventSummary;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

/**
 * Client for the Environment Service.
 * This client provides methods to interact with the Environment Service gRPC server.
 * It supports all four types of gRPC communication patterns:
 * - Unary RPC
 * - Server streaming RPC
 * - Client streaming RPC
 * - Bi-directional streaming RPC
 */
public class EnvironmentClient {
    private static final Logger logger = LoggerFactory.getLogger(EnvironmentClient.class);
    private final ManagedChannel channel;
    private final EnvironmentServiceGrpc.EnvironmentServiceBlockingStub blockingStub;
    private final EnvironmentServiceGrpc.EnvironmentServiceStub asyncStub;

    /**
     * Constructs a new EnvironmentClient connected to the specified host and port.
     *
     * @param host The hostname of the Environment Service server
     * @param port The port number of the Environment Service server
     */
    public EnvironmentClient(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port)
            .usePlaintext() // Using plaintext for simplicity. In production, use SSL/TLS
            .build();
        this.blockingStub = EnvironmentServiceGrpc.newBlockingStub(channel);
        this.asyncStub = EnvironmentServiceGrpc.newStub(channel);
    }

    /**
     * Retrieves the current environment status for a specific location.
     * This is a unary RPC call.
     *
     * @param location The location to get environment status for
     * @return EnvironmentResponse containing the status and data
     */
    public EnvironmentResponse getEnvironmentStatus(String location) {
        EnvironmentRequest request = EnvironmentRequest.newBuilder()
            .setLocation(location)
            .build();

        try {
            EnvironmentResponse response = blockingStub.getEnvironmentStatus(request);
            logger.info("Environment status for {}: {}", location, response.getMessage());
            return response;
        } catch (Exception e) {
            logger.error("Error getting environment status", e);
            return EnvironmentResponse.newBuilder()
                .setSuccess(false)
                .setMessage("Error: " + e.getMessage())
                .build();
        }
    }

    /**
     * Streams environment data updates for a specific location.
     * This is a server streaming RPC call.
     * The client will receive updates for 1 minute or until the server completes the stream.
     *
     * @param location The location to stream environment data for
     */
    public void streamEnvironmentData(String location) {
        final CountDownLatch finishLatch = new CountDownLatch(1);
        EnvironmentRequest request = EnvironmentRequest.newBuilder()
            .setLocation(location)
            .build();

        StreamObserver<EnvironmentData> responseObserver = new StreamObserver<EnvironmentData>() {
            @Override
            public void onNext(EnvironmentData data) {
                logger.info("Received environment data update for {}: " +
                    "Air Quality: {}, Noise Level: {}, Temperature: {}, Humidity: {}",
                    data.getLocation(),
                    data.getAirQuality(),
                    data.getNoiseLevel(),
                    data.getTemperature(),
                    data.getHumidity());
            }

            @Override
            public void onError(Throwable t) {
                logger.error("Error in environment data stream", t);
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                logger.info("Environment data streaming completed");
                finishLatch.countDown();
            }
        };

        asyncStub.streamEnvironmentData(request, responseObserver);

        try {
            if (!finishLatch.await(1, TimeUnit.MINUTES)) {
                logger.warn("Environment data streaming can not finish within 1 minute");
            }
        } catch (InterruptedException e) {
            logger.error("Error waiting for environment data stream", e);
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Reports environmental events for a specific location.
     * This is a client streaming RPC call.
     * The client sends multiple events and receives a single response when complete.
     *
     * @param location The location where the events occurred
     */
    public void reportEnvironmentalEvents(String location) {
        final CountDownLatch finishLatch = new CountDownLatch(1);
        StreamObserver<EnvironmentResponse> responseObserver = new StreamObserver<EnvironmentResponse>() {
            @Override
            public void onNext(EnvironmentResponse response) {
                logger.info("Event report response: {}", response.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                logger.error("Error reporting environmental events", t);
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                logger.info("Environmental events reporting completed");
                finishLatch.countDown();
            }
        };

        StreamObserver<EnvironmentalEvent> requestObserver = 
            asyncStub.reportEnvironmentalEvents(responseObserver);

        try {
            // Report some sample events
            requestObserver.onNext(EnvironmentalEvent.newBuilder()
                .setLocation(location)
                .setType("NOISE_POLLUTION")
                .setSeverity(3)
                .setDescription("High noise levels detected")
                .build());

            requestObserver.onNext(EnvironmentalEvent.newBuilder()
                .setLocation(location)
                .setType("AIR_QUALITY")
                .setSeverity(2)
                .setDescription("Moderate air pollution")
                .build());

            requestObserver.onCompleted();

            if (!finishLatch.await(1, TimeUnit.MINUTES)) {
                logger.warn("Environmental events reporting can not finish within 1 minute");
            }
        } catch (InterruptedException e) {
            logger.error("Error reporting environmental events", e);
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Monitors the environment for a specific location and duration.
     * This is a bi-directional streaming RPC call.
     * The client sends monitoring requests and receives periodic summaries.
     *
     * @param location The location to monitor
     * @param duration The duration of monitoring in seconds
     */
    public void monitorEnvironment(String location, int duration) {
        final CountDownLatch finishLatch = new CountDownLatch(1);
        StreamObserver<EventSummary> responseObserver = new StreamObserver<EventSummary>() {
            @Override
            public void onNext(EventSummary summary) {
                logger.info("Environment monitoring summary for {}: " +
                    "Event Count: {}, Average Air Quality: {}, Average Noise Level: {}",
                    summary.getLocation(),
                    summary.getEventCount(),
                    summary.getAverageAirQuality(),
                    summary.getAverageNoiseLevel());
            }

            @Override
            public void onError(Throwable t) {
                logger.error("Error in environment monitoring", t);
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                logger.info("Environment monitoring completed");
                finishLatch.countDown();
            }
        };

        StreamObserver<EnvironmentMonitorRequest> requestObserver = 
            asyncStub.monitorEnvironment(responseObserver);

        try {
            requestObserver.onNext(EnvironmentMonitorRequest.newBuilder()
                .setLocation(location)
                .setDuration(duration)
                .build());

            requestObserver.onCompleted();

            if (!finishLatch.await(duration + 30, TimeUnit.SECONDS)) {
                logger.warn("Environment monitoring can not finish within the expected time");
            }
        } catch (InterruptedException e) {
            logger.error("Error during environment monitoring", e);
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Shuts down the client and releases resources.
     * This method should be called when the client is no longer needed.
     */
    public void shutdown() {
        try {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.error("Error shutting down channel", e);
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Main method to test the Environment Client.
     * Usage: java EnvironmentClient [host [port]]
     *
     * @param args Command line arguments: [host [port]]
     * @throws InterruptedException if the test is interrupted
     */
    public static void main(String[] args) throws InterruptedException {
        String host = args.length > 0 ? args[0] : "localhost";
        int port = args.length > 1 ? Integer.parseInt(args[1]) : 51000;
        final EnvironmentClient client = new EnvironmentClient(host, port);
        try {
            // Test the client
            client.getEnvironmentStatus("Central London");
            client.streamEnvironmentData("Central London");
            client.reportEnvironmentalEvents("Central London");
            client.monitorEnvironment("Central London", 30);
        } finally {
            client.shutdown();
        }
    }
} 