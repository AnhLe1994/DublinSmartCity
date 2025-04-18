package com.londonsmartcity.service.environment;

import com.londonsmartcity.proto.*;
import io.grpc.stub.StreamObserver;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class EnvironmentServiceImpl extends EnvironmentServiceGrpc.EnvironmentServiceImplBase {
    private final int port;
    private final Random random = new Random();

    public EnvironmentServiceImpl(int port) {
        this.port = port;
    }

    @Override
    public void getEnvironmentStatus(EnvironmentRequest request, StreamObserver<EnvironmentResponse> responseObserver) {
        try {
            // Simulate environment data
            EnvironmentData data = EnvironmentData.newBuilder()
                .setLocation(request.getLocation())
                .setAirQuality(random.nextInt(101))
                .setNoiseLevel(random.nextInt(101))
                .setTemperature(15 + random.nextFloat() * 15) // 15-30°C
                .setHumidity(random.nextInt(101))
                .build();

            EnvironmentResponse response = EnvironmentResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Environment data retrieved successfully")
                .setData(data)
                .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            EnvironmentResponse response = EnvironmentResponse.newBuilder()
                .setSuccess(false)
                .setMessage("Error: " + e.getMessage())
                .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void streamEnvironmentData(EnvironmentRequest request, StreamObserver<EnvironmentData> responseObserver) {
        try {
            // Stream environment data every 2 seconds
            for (int i = 0; i < 10; i++) {
                EnvironmentData data = EnvironmentData.newBuilder()
                    .setLocation(request.getLocation())
                    .setAirQuality(random.nextInt(101))
                    .setNoiseLevel(random.nextInt(101))
                    .setTemperature(15 + random.nextFloat() * 15)
                    .setHumidity(random.nextInt(101))
                    .build();

                responseObserver.onNext(data);
                TimeUnit.SECONDS.sleep(2);
            }
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public StreamObserver<EnvironmentalEvent> reportEnvironmentalEvents(StreamObserver<EnvironmentResponse> responseObserver) {
        return new StreamObserver<EnvironmentalEvent>() {
            @Override
            public void onNext(EnvironmentalEvent event) {
                // Process the event
                System.out.println("Received environmental event: " + event.getType() + " at " + event.getLocation());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error in environmental event stream: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                EnvironmentResponse response = EnvironmentResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Environmental events processed successfully")
                    .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<EnvironmentMonitorRequest> monitorEnvironment(StreamObserver<EventSummary> responseObserver) {
        return new StreamObserver<EnvironmentMonitorRequest>() {
            @Override
            public void onNext(EnvironmentMonitorRequest request) {
                // Process monitoring request and send summary
                EventSummary summary = EventSummary.newBuilder()
                    .setLocation(request.getLocation())
                    .setEventCount(random.nextInt(10))
                    .setAverageAirQuality(random.nextInt(101))
                    .setAverageNoiseLevel(random.nextInt(101))
                    .build();
                responseObserver.onNext(summary);
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error in environment monitoring: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
} 