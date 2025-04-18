package com.londonsmartcity.service.transport;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.londonsmartcity.proto.TransportIssue;
import com.londonsmartcity.proto.TransportMonitorRequest;
import com.londonsmartcity.proto.TransportRequest;
import com.londonsmartcity.proto.TransportResponse;
import com.londonsmartcity.proto.TransportServiceGrpc;
import com.londonsmartcity.proto.TransportSummary;
import com.londonsmartcity.proto.TransportUpdate;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

/**
 * Client for the Transport Service.
 * This client provides methods to interact with the Transport Service gRPC server.
 * It supports all four types of gRPC communication patterns:
 * - Unary RPC
 * - Server streaming RPC
 * - Client streaming RPC
 * - Bi-directional streaming RPC
 */
public class TransportClient {
    private static final Logger logger = LoggerFactory.getLogger(TransportClient.class);
    private final ManagedChannel channel;
    private final TransportServiceGrpc.TransportServiceBlockingStub blockingStub;
    private final TransportServiceGrpc.TransportServiceStub asyncStub;

    /**
     * Constructs a new TransportClient connected to the specified host and port.
     *
     * @param host The hostname of the Transport Service server
     * @param port The port number of the Transport Service server
     */
    public TransportClient(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port)
            .usePlaintext() // Using plaintext for simplicity. In production, use SSL/TLS
            .build();
        this.blockingStub = TransportServiceGrpc.newBlockingStub(channel);
        this.asyncStub = TransportServiceGrpc.newStub(channel);
    }

    /**
     * Retrieves the current transport status for a specific location and transport type.
     * This is a unary RPC call.
     *
     * @param location The location to get transport status for
     * @param transportType The type of transport (e.g., "bus", "tube", "train")
     * @return TransportResponse containing the status and data
     */
    public TransportResponse getTransportStatus(String location, String transportType) {
        TransportRequest request = TransportRequest.newBuilder()
            .setLocation(location)
            .setTransportType(transportType)
            .build();

        try {
            TransportResponse response = blockingStub.getTransportStatus(request);
            logger.info("Transport status for {}: {}", location, response.getMessage());
            return response;
        } catch (Exception e) {
            logger.error("Error getting transport status", e);
            return TransportResponse.newBuilder()
                .setSuccess(false)
                .setMessage("Error: " + e.getMessage())
                .build();
        }
    }

    /**
     * Streams transport updates for a specific location and transport type.
     * This is a server streaming RPC call.
     * The client will receive updates until the server completes the stream.
     *
     * @param location The location to stream transport updates for
     * @param transportType The type of transport (e.g., "bus", "tube", "train")
     */
    public void streamTransportUpdates(String location, String transportType) {
        TransportRequest request = TransportRequest.newBuilder()
            .setLocation(location)
            .setTransportType(transportType)
            .build();

        asyncStub.streamTransportUpdates(request, new StreamObserver<TransportUpdate>() {
            @Override
            public void onNext(TransportUpdate update) {
                logger.info("Received transport update for {} at {}: Status={}%, Delay={}min, Next arrival={}",
                    update.getTransportType(),
                    update.getLocation(),
                    update.getServiceStatus(),
                    update.getDelayMinutes(),
                    update.getNextArrival());
            }

            @Override
            public void onError(Throwable t) {
                logger.error("Error in transport updates stream", t);
            }

            @Override
            public void onCompleted() {
                logger.info("Transport updates stream completed");
            }
        });
    }

    /**
     * Reports a transport issue for a specific location and transport type.
     * This is a client streaming RPC call.
     * The client sends an issue and receives a response when complete.
     *
     * @param location The location where the issue occurred
     * @param transportType The type of transport (e.g., "bus", "tube", "train")
     * @param issueType The type of issue (e.g., "DELAY", "CANCELLATION")
     * @param description A description of the issue
     */
    public void reportTransportIssues(String location, String transportType, String issueType, String description) {
        final CountDownLatch finishLatch = new CountDownLatch(1);
        StreamObserver<TransportResponse> responseObserver = new StreamObserver<TransportResponse>() {
            @Override
            public void onNext(TransportResponse response) {
                logger.info("Received response for transport issue: {}", response.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                logger.error("Error reporting transport issue", t);
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                logger.info("Transport issue reporting completed");
                finishLatch.countDown();
            }
        };

        StreamObserver<TransportIssue> requestObserver = asyncStub.reportTransportIssues(responseObserver);
        try {
            TransportIssue issue = TransportIssue.newBuilder()
                .setLocation(location)
                .setTransportType(transportType)
                .setType(issueType)
                .setDescription(description)
                .build();
            requestObserver.onNext(issue);
            requestObserver.onCompleted();
            finishLatch.await(1, TimeUnit.MINUTES);
        } catch (Exception e) {
            logger.error("Error sending transport issue", e);
            requestObserver.onError(e);
        }
    }

    /**
     * Monitors transport for a specific location, transport type, and duration.
     * This is a bi-directional streaming RPC call.
     * The client sends monitoring requests and receives periodic summaries.
     *
     * @param location The location to monitor
     * @param transportType The type of transport (e.g., "bus", "tube", "train")
     * @param duration The duration of monitoring in seconds
     */
    public void monitorTransport(String location, String transportType, int duration) {
        final CountDownLatch finishLatch = new CountDownLatch(1);
        StreamObserver<TransportSummary> responseObserver = new StreamObserver<TransportSummary>() {
            @Override
            public void onNext(TransportSummary summary) {
                logger.info("Transport monitoring summary for {} at {}: Issues={}, Avg Delay={}min, Avg Status={}%",
                    summary.getTransportType(),
                    summary.getLocation(),
                    summary.getIssueCount(),
                    summary.getAverageDelay(),
                    summary.getAverageServiceStatus());
            }

            @Override
            public void onError(Throwable t) {
                logger.error("Error in transport monitoring", t);
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                logger.info("Transport monitoring completed");
                finishLatch.countDown();
            }
        };

        StreamObserver<TransportMonitorRequest> requestObserver = asyncStub.monitorTransport(responseObserver);
        try {
            TransportMonitorRequest request = TransportMonitorRequest.newBuilder()
                .setLocation(location)
                .setTransportType(transportType)
                .setDuration(duration)
                .build();
            requestObserver.onNext(request);
            requestObserver.onCompleted();
            finishLatch.await(duration + 5, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error("Error in transport monitoring request", e);
            requestObserver.onError(e);
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
     * Main method to test the Transport Client.
     * Usage: java TransportClient [host [port]]
     *
     * @param args Command line arguments: [host [port]]
     * @throws InterruptedException if the test is interrupted
     */
    public static void main(String[] args) throws InterruptedException {
        String host = args.length > 0 ? args[0] : "localhost";
        int port = args.length > 1 ? Integer.parseInt(args[1]) : 53000;
        final TransportClient client = new TransportClient(host, port);
        try {
            String location = "Central London";
            String transportType = "tube";
            
            // Test the client
            client.getTransportStatus(location, transportType);
            client.streamTransportUpdates(location, transportType);
            client.reportTransportIssues(location, transportType, "DELAY", "Signal failure on Central line");
            client.monitorTransport(location, transportType, 30);
        } finally {
            client.shutdown();
        }
    }
} 