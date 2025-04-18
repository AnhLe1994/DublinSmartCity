package com.londonsmartcity.service.transport;

import java.util.Arrays;

import com.londonsmartcity.proto.TransportData;
import com.londonsmartcity.proto.TransportIssue;
import com.londonsmartcity.proto.TransportMonitorRequest;
import com.londonsmartcity.proto.TransportRequest;
import com.londonsmartcity.proto.TransportResponse;
import com.londonsmartcity.proto.TransportServiceGrpc;
import com.londonsmartcity.proto.TransportSummary;
import com.londonsmartcity.proto.TransportUpdate;

import io.grpc.stub.StreamObserver;

public class TransportServiceImpl extends TransportServiceGrpc.TransportServiceImplBase {
    private final int port;

    public TransportServiceImpl(int port) {
        this.port = port;
    }

    @Override
    public void getTransportStatus(TransportRequest request, StreamObserver<TransportResponse> responseObserver) {
        try {
            // Simulate transport data
            TransportData data = TransportData.newBuilder()
                .setLocation(request.getLocation())
                .setTransportType(request.getTransportType())
                .setServiceStatus(90)
                .setDelayMinutes(5)
                .setNextArrival("10:30 AM")
                .addAllIssues(Arrays.asList("Minor delay on Northern Line"))
                .build();

            TransportResponse response = TransportResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Transport data retrieved successfully")
                .setData(data)
                .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            TransportResponse response = TransportResponse.newBuilder()
                .setSuccess(false)
                .setMessage("Error: " + e.getMessage())
                .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void streamTransportUpdates(TransportRequest request, StreamObserver<TransportUpdate> responseObserver) {
        try {
            // Simulate streaming updates
            for (int i = 0; i < 5; i++) {
                TransportUpdate update = TransportUpdate.newBuilder()
                    .setLocation(request.getLocation())
                    .setTransportType(request.getTransportType())
                    .setServiceStatus(90 - i * 5)
                    .setDelayMinutes(i)
                    .setNextArrival(String.format("%d:%02d AM", 10 + i/2, (i%2)*30))
                    .build();
                
                responseObserver.onNext(update);
                Thread.sleep(1000); // Simulate delay between updates
            }
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public StreamObserver<TransportIssue> reportTransportIssues(StreamObserver<TransportResponse> responseObserver) {
        return new StreamObserver<TransportIssue>() {
            @Override
            public void onNext(TransportIssue issue) {
                // Process the reported issue
                TransportResponse response = TransportResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Issue reported successfully: " + issue.getDescription())
                    .build();
                responseObserver.onNext(response);
            }

            @Override
            public void onError(Throwable t) {
                responseObserver.onError(t);
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<TransportMonitorRequest> monitorTransport(StreamObserver<TransportSummary> responseObserver) {
        return new StreamObserver<TransportMonitorRequest>() {
            @Override
            public void onNext(TransportMonitorRequest request) {
                try {
                    // Simulate monitoring updates
                    for (int i = 0; i < request.getDuration(); i++) {
                        TransportSummary summary = TransportSummary.newBuilder()
                            .setLocation(request.getLocation())
                            .setTransportType(request.getTransportType())
                            .setAverageServiceStatus(85)
                            .setAverageDelay(3)
                            .setIssueCount(1)
                            .setTimestamp(java.time.LocalDateTime.now().toString())
                            .build();
                        
                        responseObserver.onNext(summary);
                        Thread.sleep(1000); // Simulate delay between updates
                    }
                } catch (Exception e) {
                    responseObserver.onError(e);
                }
            }

            @Override
            public void onError(Throwable t) {
                responseObserver.onError(t);
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
} 