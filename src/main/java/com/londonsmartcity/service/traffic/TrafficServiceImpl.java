package com.londonsmartcity.service.traffic;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.londonsmartcity.proto.TrafficData;
import com.londonsmartcity.proto.TrafficIncident;
import com.londonsmartcity.proto.TrafficMonitorRequest;
import com.londonsmartcity.proto.TrafficRequest;
import com.londonsmartcity.proto.TrafficResponse;
import com.londonsmartcity.proto.TrafficServiceGrpc;
import com.londonsmartcity.proto.TrafficSummary;
import com.londonsmartcity.proto.TrafficUpdate;

import io.grpc.stub.StreamObserver;

public class TrafficServiceImpl extends TrafficServiceGrpc.TrafficServiceImplBase {
    private final int port;

    public TrafficServiceImpl(int port) {
        this.port = port;
    }

    @Override
    public void getTrafficStatus(TrafficRequest request, StreamObserver<TrafficResponse> responseObserver) {
        try {
            // Simulate traffic data
            TrafficData data = TrafficData.newBuilder()
                .setLocation(request.getLocation())
                .setCongestionLevel(75)
                .setAverageSpeed(30)
                .setVehicleCount(150)
                .addAllIncidents(Arrays.asList("Minor accident on main road", "Road work in progress"))
                .build();

            TrafficResponse response = TrafficResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Traffic data retrieved successfully")
                .setData(data)
                .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            TrafficResponse response = TrafficResponse.newBuilder()
                .setSuccess(false)
                .setMessage("Error: " + e.getMessage())
                .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void streamTrafficUpdates(TrafficRequest request, StreamObserver<TrafficUpdate> responseObserver) {
        try {
            // Simulate streaming traffic updates
            for (int i = 0; i < 5; i++) {
                TrafficUpdate update = TrafficUpdate.newBuilder()
                    .setLocation(request.getLocation())
                    .setCongestionLevel(70 + i)
                    .setAverageSpeed(35 - i)
                    .setTimestamp(java.time.LocalDateTime.now().toString())
                    .build();
                
                responseObserver.onNext(update);
                TimeUnit.SECONDS.sleep(1);
            }
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public StreamObserver<TrafficIncident> reportTrafficIncidents(StreamObserver<TrafficResponse> responseObserver) {
        return new StreamObserver<TrafficIncident>() {
            @Override
            public void onNext(TrafficIncident incident) {
                // Process the incident
                System.out.println("Received traffic incident: " + incident.getDescription());
            }

            @Override
            public void onError(Throwable t) {
                responseObserver.onError(t);
            }

            @Override
            public void onCompleted() {
                TrafficResponse response = TrafficResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Traffic incidents reported successfully")
                    .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<TrafficMonitorRequest> monitorTraffic(StreamObserver<TrafficSummary> responseObserver) {
        return new StreamObserver<TrafficMonitorRequest>() {
            @Override
            public void onNext(TrafficMonitorRequest request) {
                try {
                    // Simulate monitoring traffic for the requested duration
                    for (int i = 0; i < request.getDuration(); i++) {
                        TrafficSummary summary = TrafficSummary.newBuilder()
                            .setLocation(request.getLocation())
                            .setAverageCongestion(75)
                            .setAverageSpeed(30)
                            .setIncidentCount(2)
                            .build();
                        
                        responseObserver.onNext(summary);
                        TimeUnit.SECONDS.sleep(1);
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