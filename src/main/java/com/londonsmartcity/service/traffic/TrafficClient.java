package com.londonsmartcity.service.traffic;

import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.londonsmartcity.proto.TrafficIncident;
import com.londonsmartcity.proto.TrafficMonitorRequest;
import com.londonsmartcity.proto.TrafficRequest;
import com.londonsmartcity.proto.TrafficResponse;
import com.londonsmartcity.proto.TrafficServiceGrpc;
import com.londonsmartcity.proto.TrafficSummary;
import com.londonsmartcity.proto.TrafficUpdate;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

/**
 * Client for the Traffic Service.
 * This client provides methods to interact with the Traffic Service gRPC server.
 * It supports all four types of gRPC communication patterns:
 * - Unary RPC
 * - Server streaming RPC
 * - Client streaming RPC
 * - Bi-directional streaming RPC
 */
public class TrafficClient {
    private static final Logger logger = LoggerFactory.getLogger(TrafficClient.class);
    private final ManagedChannel channel;
    private final TrafficServiceGrpc.TrafficServiceBlockingStub blockingStub;
    private final TrafficServiceGrpc.TrafficServiceStub asyncStub;

    /**
     * Constructs a new TrafficClient connected to the specified host and port.
     *
     * @param host The hostname of the Traffic Service server
     * @param port The port number of the Traffic Service server
     */
    public TrafficClient(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port)
            .usePlaintext() // Using plaintext for simplicity. In production, use SSL/TLS
            .build();
        this.blockingStub = TrafficServiceGrpc.newBlockingStub(channel);
        this.asyncStub = TrafficServiceGrpc.newStub(channel);
    }

    /**
     * Retrieves the current traffic status for a specific location.
     * This is a unary RPC call.
     *
     * @param location The location to get traffic status for
     * @return TrafficResponse containing the status and data
     */
    public TrafficResponse getTrafficStatus(String location) {
        TrafficRequest request = TrafficRequest.newBuilder()
            .setLocation(location)
            .build();

        try {
            TrafficResponse response = blockingStub.getTrafficStatus(request);
            logger.info("Traffic status for {}: {}", location, response.getMessage());
            return response;
        } catch (Exception e) {
            logger.error("Error getting traffic status", e);
            return TrafficResponse.newBuilder()
                .setSuccess(false)
                .setMessage("Error: " + e.getMessage())
                .build();
        }
    }

    /**
     * Streams traffic updates for a specific location.
     * This is a server streaming RPC call.
     * The client will receive updates for 1 minute or until the server completes the stream.
     *
     * @param location The location to stream traffic updates for
     */
    public void streamTrafficUpdates(String location) {
        final CountDownLatch finishLatch = new CountDownLatch(1);
        TrafficRequest request = TrafficRequest.newBuilder()
            .setLocation(location)
            .build();

        StreamObserver<TrafficUpdate> responseObserver = new StreamObserver<TrafficUpdate>() {
            @Override
            public void onNext(TrafficUpdate update) {
                logger.info("Received traffic update for {}: " +
                    "Congestion Level: {}, Average Speed: {}, Timestamp: {}",
                    update.getLocation(),
                    update.getCongestionLevel(),
                    update.getAverageSpeed(),
                    update.getTimestamp());
            }

            @Override
            public void onError(Throwable t) {
                logger.error("Error in traffic updates stream", t);
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                logger.info("Traffic updates streaming completed");
                finishLatch.countDown();
            }
        };

        asyncStub.streamTrafficUpdates(request, responseObserver);

        try {
            if (!finishLatch.await(1, TimeUnit.MINUTES)) {
                logger.warn("Traffic updates streaming can not finish within 1 minute");
            }
        } catch (InterruptedException e) {
            logger.error("Error waiting for traffic updates stream", e);
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Reports traffic incidents for a specific location.
     * This is a client streaming RPC call.
     * The client sends multiple incidents and receives a single response when complete.
     *
     * @param location The location where the incidents occurred
     */
    public void reportTrafficIncidents(String location) {
        final CountDownLatch finishLatch = new CountDownLatch(1);
        StreamObserver<TrafficResponse> responseObserver = new StreamObserver<TrafficResponse>() {
            @Override
            public void onNext(TrafficResponse response) {
                logger.info("Incident report response: {}", response.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                logger.error("Error reporting traffic incidents", t);
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                logger.info("Traffic incidents reporting completed");
                finishLatch.countDown();
            }
        };

        StreamObserver<TrafficIncident> requestObserver = 
            asyncStub.reportTrafficIncidents(responseObserver);

        try {
            // Report some sample incidents
            requestObserver.onNext(TrafficIncident.newBuilder()
                .setLocation(location)
                .setType("ACCIDENT")
                .setSeverity(3)
                .setDescription("Two-car collision")
                .setTimestamp(Instant.now().toString())
                .build());

            requestObserver.onNext(TrafficIncident.newBuilder()
                .setLocation(location)
                .setType("CONSTRUCTION")
                .setSeverity(2)
                .setDescription("Road maintenance work")
                .setTimestamp(Instant.now().toString())
                .build());

            requestObserver.onCompleted();

            if (!finishLatch.await(1, TimeUnit.MINUTES)) {
                logger.warn("Traffic incidents reporting can not finish within 1 minute");
            }
        } catch (InterruptedException e) {
            logger.error("Error reporting traffic incidents", e);
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Monitors traffic for a specific location and duration.
     * This is a bi-directional streaming RPC call.
     * The client sends monitoring requests and receives periodic summaries.
     *
     * @param location The location to monitor
     * @param duration The duration of monitoring in seconds
     */
    public void monitorTraffic(String location, int duration) {
        final CountDownLatch finishLatch = new CountDownLatch(1);
        StreamObserver<TrafficSummary> responseObserver = new StreamObserver<TrafficSummary>() {
            @Override
            public void onNext(TrafficSummary summary) {
                logger.info("Traffic monitoring summary for {}: " +
                    "Incident Count: {}, Average Congestion: {}, Average Speed: {}, Timestamp: {}",
                    summary.getLocation(),
                    summary.getIncidentCount(),
                    summary.getAverageCongestion(),
                    summary.getAverageSpeed(),
                    summary.getTimestamp());
            }

            @Override
            public void onError(Throwable t) {
                logger.error("Error in traffic monitoring", t);
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                logger.info("Traffic monitoring completed");
                finishLatch.countDown();
            }
        };

        StreamObserver<TrafficMonitorRequest> requestObserver = 
            asyncStub.monitorTraffic(responseObserver);

        try {
            requestObserver.onNext(TrafficMonitorRequest.newBuilder()
                .setLocation(location)
                .setDuration(duration)
                .build());

            requestObserver.onCompleted();

            if (!finishLatch.await(duration + 30, TimeUnit.SECONDS)) {
                logger.warn("Traffic monitoring can not finish within the expected time");
            }
        } catch (InterruptedException e) {
            logger.error("Error during traffic monitoring", e);
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
     * Main method to test the Traffic Client.
     * Usage: java TrafficClient [host [port]]
     *
     * @param args Command line arguments: [host [port]]
     * @throws InterruptedException if the test is interrupted
     */
    public static void main(String[] args) throws InterruptedException {
        String host = args.length > 0 ? args[0] : "localhost";
        int port = args.length > 1 ? Integer.parseInt(args[1]) : 52000;
        final TrafficClient client = new TrafficClient(host, port);
        try {
            // Test the client
            client.getTrafficStatus("Central London");
            client.streamTrafficUpdates("Central London");
            client.reportTrafficIncidents("Central London");
            client.monitorTraffic("Central London", 30);
        } finally {
            client.shutdown();
        }
    }
} 