package com.londonsmartcity.transport;

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

public class TransportService extends BaseService {
    private static final Logger logger = LoggerFactory.getLogger(TransportService.class);
    private static final int PORT = 50053;

    private final Server server;
    private final Map<String, ArrivalInfoResponse> arrivalInfoMap;
    private final Map<String, ServiceStatusResponse> lineStatusMap;
    private final Map<String, VehicleLocation> vehicleLocations;
    private final ScheduledExecutorService scheduler;

    public TransportService() {
        super("TRANSPORT", "Public Transport Service", PORT, new HashMap<>());
        server = ServerBuilder.forPort(PORT)
                .addService(new TransportServiceImpl())
                .build();
        arrivalInfoMap = new ConcurrentHashMap<>();
        lineStatusMap = new ConcurrentHashMap<>();
        vehicleLocations = new ConcurrentHashMap<>();
        scheduler = Executors.newSingleThreadScheduledExecutor();
        initializeSampleData();
    }

    private void initializeSampleData() {
        // Initialize some sample arrival information
        addArrivalInfo("STOP1", "LINE1", "BUS1", 5, "ON_TIME");
        addArrivalInfo("STOP1", "LINE1", "BUS2", 12, "DELAYED");
        addArrivalInfo("STOP2", "LINE2", "BUS3", 3, "ON_TIME");

        // Initialize some sample line statuses
        addLineStatus("LINE1", "GOOD_SERVICE", "All services running normally");
        addLineStatus("LINE2", "MINOR_DELAYS", "Minor delays due to signal failure");
        addLineStatus("LINE3", "SEVERE_DELAYS", "Severe delays due to track maintenance");

        // Initialize some sample vehicle locations
        addVehicleLocation("BUS1", "LINE1", 51.5074, -0.1278, 30);
        addVehicleLocation("BUS2", "LINE1", 51.5174, -0.1378, 25);
        addVehicleLocation("BUS3", "LINE2", 51.4974, -0.1178, 35);
    }

    private void addArrivalInfo(String stopId, String lineId, String vehicleId, int minutesUntilArrival, String status) {
        ArrivalInfo arrival = ArrivalInfo.newBuilder()
                .setVehicleId(vehicleId)
                .setMinutesUntilArrival(minutesUntilArrival)
                .setStatus(status)
                .build();

        ArrivalInfoResponse response = arrivalInfoMap.getOrDefault(stopId + ":" + lineId, 
                ArrivalInfoResponse.newBuilder()
                        .setStopId(stopId)
                        .setLineId(lineId)
                        .build());

        ArrivalInfoResponse updatedResponse = ArrivalInfoResponse.newBuilder(response)
                .addArrivals(arrival)
                .build();

        arrivalInfoMap.put(stopId + ":" + lineId, updatedResponse);
    }

    private void addLineStatus(String lineId, String status, String message) {
        ServiceStatusResponse response = ServiceStatusResponse.newBuilder()
                .setLineId(lineId)
                .setStatus(status)
                .setMessage(message)
                .build();
        lineStatusMap.put(lineId, response);
    }

    private void addVehicleLocation(String vehicleId, String lineId, double latitude, double longitude, int speed) {
        VehicleLocation location = VehicleLocation.newBuilder()
                .setVehicleId(vehicleId)
                .setLineId(lineId)
                .setLatitude(latitude)
                .setLongitude(longitude)
                .setSpeed(speed)
                .setTimestamp(System.currentTimeMillis())
                .build();
        vehicleLocations.put(vehicleId, location);
    }

    @Override
    public void start() {
        try {
            server.start();
            logger.info("Transport Service started on port {}", PORT);
            super.start();

            // Schedule periodic updates
            scheduler.scheduleAtFixedRate(() -> {
                try {
                    updateVehicleLocations();
                } catch (Exception e) {
                    logger.error("Error updating vehicle locations: {}", e.getMessage());
                }
            }, 0, 1, TimeUnit.MINUTES);
        } catch (Exception e) {
            logger.error("Failed to start transport service: {}", e.getMessage());
            throw new RuntimeException("Service startup failed", e);
        }
    }

    private void updateVehicleLocations() {
        vehicleLocations.forEach((vehicleId, location) -> {
            // Simulate vehicle movement
            double newLatitude = location.getLatitude() + (Math.random() * 0.001 - 0.0005);
            double newLongitude = location.getLongitude() + (Math.random() * 0.001 - 0.0005);
            int newSpeed = (int) Math.max(0, Math.min(80, location.getSpeed() + (Math.random() * 20 - 10)));

            VehicleLocation updatedLocation = VehicleLocation.newBuilder(location)
                    .setLatitude(newLatitude)
                    .setLongitude(newLongitude)
                    .setSpeed(newSpeed)
                    .setTimestamp(System.currentTimeMillis())
                    .build();

            vehicleLocations.put(vehicleId, updatedLocation);
        });
    }

    @Override
    public void stop() {
        try {
            super.stop();
            server.shutdown();
            scheduler.shutdown();
            logger.info("Transport Service stopped");
        } catch (Exception e) {
            logger.error("Error stopping transport service: {}", e.getMessage());
        }
    }

    private class TransportServiceImpl extends TransportServiceGrpc.TransportServiceImplBase {
        @Override
        public void getArrivalInfo(ArrivalInfoRequest request, StreamObserver<ArrivalInfoResponse> responseObserver) {
            ArrivalInfoResponse response = arrivalInfoMap.get(request.getStopId() + ":" + request.getLineId());
            if (response != null) {
                responseObserver.onNext(response);
            }
            responseObserver.onCompleted();
        }

        @Override
        public void getServiceStatus(ServiceStatusRequest request, StreamObserver<ServiceStatusResponse> responseObserver) {
            ServiceStatusResponse response = lineStatusMap.get(request.getLineId());
            if (response != null) {
                responseObserver.onNext(response);
            }
            responseObserver.onCompleted();
        }

        @Override
        public void reportDisruption(DisruptionReport request, StreamObserver<DisruptionResponse> responseObserver) {
            try {
                // In a real implementation, we would update the line status
                responseObserver.onNext(DisruptionResponse.newBuilder()
                        .setSuccess(true)
                        .setMessage("Disruption reported successfully")
                        .build());
            } catch (Exception e) {
                responseObserver.onNext(DisruptionResponse.newBuilder()
                        .setSuccess(false)
                        .setMessage("Error reporting disruption: " + e.getMessage())
                        .build());
            }
            responseObserver.onCompleted();
        }

        @Override
        public void streamVehicleLocations(VehicleLocationRequest request, StreamObserver<VehicleLocation> responseObserver) {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    vehicleLocations.values().stream()
                            .filter(location -> location.getLineId().equals(request.getLineId()))
                            .forEach(responseObserver::onNext);
                    Thread.sleep(5000); // Update every 5 seconds
                }
            } catch (Exception e) {
                logger.error("Error streaming vehicle locations: {}", e.getMessage());
                responseObserver.onError(e);
            }
        }
    }

    public static void main(String[] args) {
        final TransportService service = new TransportService();
        service.start();
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            service.stop();
        }));
    }
} 