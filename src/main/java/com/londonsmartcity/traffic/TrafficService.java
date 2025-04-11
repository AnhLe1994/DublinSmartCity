package com.londonsmartcity.traffic;

import com.londonsmartcity.common.BaseService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TrafficService extends BaseService {
    private static final Logger logger = LoggerFactory.getLogger(TrafficService.class);
    private static final int PORT = 50052;

    private final Server server;
    private final Map<String, TrafficStatusResponse> trafficStatusMap;
    private final Map<String, SignalTimingRequest> signalTimingMap;
    private final Map<String, IncidentReport> activeIncidents;
    private final ScheduledExecutorService scheduler;

    public TrafficService() {
        super("TRAFFIC", "Traffic Management Service", PORT, new HashMap<String, String>());
        server = ServerBuilder.forPort(PORT)
                .addService(new TrafficServiceImpl())
                .build();
        trafficStatusMap = new ConcurrentHashMap<>();
        signalTimingMap = new ConcurrentHashMap<>();
        activeIncidents = new ConcurrentHashMap<>();
        scheduler = Executors.newSingleThreadScheduledExecutor();
        initializeSampleData();
    }

    private void initializeSampleData() {
        // Initialize some sample traffic monitoring points
        addTrafficStatus("JUNCTION1", 2, 40, "FLOWING");
        addTrafficStatus("JUNCTION2", 3, 30, "MODERATE");
        addTrafficStatus("JUNCTION3", 4, 20, "CONGESTED");

        // Initialize some sample signal timings
        addSignalTiming("JUNCTION1", 30, 5, 25);
        addSignalTiming("JUNCTION2", 25, 5, 30);
        addSignalTiming("JUNCTION3", 35, 5, 20);
    }

    private void addTrafficStatus(String location, int congestionLevel, int averageSpeed, String status) {
        TrafficStatusResponse data = TrafficStatusResponse.newBuilder()
                .setLocation(location)
                .setCongestionLevel(congestionLevel)
                .setAverageSpeed(averageSpeed)
                .setStatus(status)
                .setTimestamp(System.currentTimeMillis())
                .build();
        trafficStatusMap.put(location, data);
    }

    private void addSignalTiming(String location, int greenDuration, int yellowDuration, int redDuration) {
        SignalTimingRequest timing = SignalTimingRequest.newBuilder()
                .setLocation(location)
                .setGreenDuration(greenDuration)
                .setYellowDuration(yellowDuration)
                .setRedDuration(redDuration)
                .build();
        signalTimingMap.put(location, timing);
    }

    @Override
    public void start() {
        try {
            server.start();
            logger.info("Traffic service started on port {}", PORT);

            // Start periodic traffic status updates
            scheduler.scheduleAtFixedRate(() -> {
                try {
                    updateTrafficStatus();
                } catch (Exception e) {
                    logger.error("Error updating traffic status: {}", e.getMessage());
                }
            }, 0, 1, TimeUnit.MINUTES);

            super.start();
        } catch (Exception e) {
            logger.error("Failed to start traffic service: {}", e.getMessage());
            throw new RuntimeException("Failed to start traffic service", e);
        }
    }

    @Override
    public void stop() {
        if (server != null) {
            server.shutdown();
        }
        scheduler.shutdown();
        super.stop();
        logger.info("Traffic service stopped");
    }

    private void updateTrafficStatus() {
        trafficStatusMap.forEach((location, status) -> {
            // Simulate traffic changes
            int newCongestion = Math.max(1, Math.min(5, status.getCongestionLevel() + (int)(Math.random() * 3 - 1)));
            int newSpeed = Math.max(0, Math.min(60, status.getAverageSpeed() + (int)(Math.random() * 10 - 5)));
            String newStatus = calculateStatus(newCongestion);

            TrafficStatusResponse updatedStatus = TrafficStatusResponse.newBuilder()
                    .setLocation(location)
                    .setCongestionLevel(newCongestion)
                    .setAverageSpeed(newSpeed)
                    .setStatus(newStatus)
                    .setTimestamp(System.currentTimeMillis())
                    .build();
            trafficStatusMap.put(location, updatedStatus);
        });
    }

    private String calculateStatus(int congestionLevel) {
        if (congestionLevel <= 2) return "FLOWING";
        if (congestionLevel <= 4) return "MODERATE";
        return "CONGESTED";
    }

    private class TrafficServiceImpl extends TrafficServiceGrpc.TrafficServiceImplBase {
        @Override
        public void getTrafficStatus(TrafficStatusRequest request, StreamObserver<TrafficStatusResponse> responseObserver) {
            try {
                TrafficStatusResponse status = trafficStatusMap.get(request.getLocation());
                if (status != null) {
                    responseObserver.onNext(status);
                    responseObserver.onCompleted();
                } else {
                    responseObserver.onError(new RuntimeException("Location not found: " + request.getLocation()));
                }
            } catch (Exception e) {
                logger.error("Error getting traffic status: {}", e.getMessage());
                responseObserver.onError(e);
            }
        }

        @Override
        public void updateSignalTiming(SignalTimingRequest request, StreamObserver<SignalTimingResponse> responseObserver) {
            try {
                signalTimingMap.put(request.getLocation(), request);
                SignalTimingResponse response = SignalTimingResponse.newBuilder()
                        .setSuccess(true)
                        .setMessage("Signal timing updated successfully for location: " + request.getLocation())
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            } catch (Exception e) {
                logger.error("Error updating signal timing: {}", e.getMessage());
                SignalTimingResponse response = SignalTimingResponse.newBuilder()
                        .setSuccess(false)
                        .setMessage("Failed to update signal timing: " + e.getMessage())
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        }

        @Override
        public void reportIncident(IncidentReport request, StreamObserver<IncidentResponse> responseObserver) {
            try {
                String location = request.getLocation();
                activeIncidents.put(location, request);
                
                // Update traffic status for the incident location
                TrafficStatusResponse currentStatus = trafficStatusMap.get(location);
                if (currentStatus != null) {
                    TrafficStatusResponse updatedStatus = TrafficStatusResponse.newBuilder(currentStatus)
                            .setCongestionLevel(5)  // Maximum congestion for incidents
                            .setStatus("INCIDENT")
                            .setTimestamp(System.currentTimeMillis())
                            .build();
                    trafficStatusMap.put(location, updatedStatus);
                }

                IncidentResponse response = IncidentResponse.newBuilder()
                        .setSuccess(true)
                        .setMessage("Incident reported successfully at location: " + location)
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            } catch (Exception e) {
                logger.error("Error reporting incident: {}", e.getMessage());
                IncidentResponse response = IncidentResponse.newBuilder()
                        .setSuccess(false)
                        .setMessage("Failed to report incident: " + e.getMessage())
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        }

        @Override
        public void streamTrafficUpdates(TrafficUpdateRequest request, StreamObserver<TrafficUpdate> responseObserver) {
            String location = request.getLocation();
            int updateInterval = Math.max(1000, request.getUpdateInterval() * 1000); // Minimum 1 second interval
            
            Thread streamThread = new Thread(() -> {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        TrafficStatusResponse status = trafficStatusMap.get(location);
                        if (status != null) {
                            TrafficUpdate update = TrafficUpdate.newBuilder()
                                    .setLocation(status.getLocation())
                                    .setCongestionLevel(status.getCongestionLevel())
                                    .setAverageSpeed(status.getAverageSpeed())
                                    .setStatus(status.getStatus())
                                    .setTimestamp(System.currentTimeMillis())
                                    .build();
                            responseObserver.onNext(update);
                        } else {
                            responseObserver.onError(new RuntimeException("Location not found: " + location));
                            return;
                        }
                        Thread.sleep(updateInterval);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                    logger.error("Error streaming traffic updates: {}", e.getMessage());
                    responseObserver.onError(e);
                }
            });
            
            streamThread.start();
        }
    }

    public static void main(String[] args) {
        final TrafficService service = new TrafficService();
        service.start();
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            service.stop();
        }));
    }
} 