package com.londonsmartcity.discovery;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ServiceDiscoveryServer extends ServiceDiscoveryGrpc.ServiceDiscoveryImplBase {
    private static final Logger logger = LoggerFactory.getLogger(ServiceDiscoveryServer.class);
    private static final int PORT = 50051;
    private static final int CLEANUP_INTERVAL_SECONDS = 60;
    private static final int SERVICE_TIMEOUT_SECONDS = 90;

    private final Map<String, ServiceInfo> services = new ConcurrentHashMap<>();
    private final Map<String, String> registrationIds = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private Server server;

    public void start() throws Exception {
        server = ServerBuilder.forPort(PORT)
                .addService(this)
                .build()
                .start();

        // Start cleanup task
        scheduler.scheduleAtFixedRate(this::cleanupStaleServices, 
                CLEANUP_INTERVAL_SECONDS, CLEANUP_INTERVAL_SECONDS, TimeUnit.SECONDS);

        logger.info("Service Discovery Server started on port {}", PORT);

        // Add shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Shutting down Service Discovery Server");
            ServiceDiscoveryServer.this.stop();
        }));
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
            scheduler.shutdown();
            logger.info("Service Discovery Server stopped");
        }
    }

    private void cleanupStaleServices() {
        long currentTime = System.currentTimeMillis();
        services.entrySet().removeIf(entry -> {
            ServiceInfo service = entry.getValue();
            long lastHeartbeat = Long.parseLong(service.getLastHeartbeat());
            if (currentTime - lastHeartbeat > SERVICE_TIMEOUT_SECONDS * 1000) {
                logger.info("Removing stale service: {}", service.getServiceName());
                registrationIds.remove(service.getServiceId());
                return true;
            }
            return false;
        });
    }

    @Override
    public void registerService(ServiceRegistration request, StreamObserver<RegistrationResponse> responseObserver) {
        try {
            String registrationId = UUID.randomUUID().toString();
            ServiceInfo serviceInfo = ServiceInfo.newBuilder()
                    .setServiceId(request.getServiceId())
                    .setServiceType(request.getServiceType())
                    .setServiceName(request.getServiceName())
                    .setHost(request.getHost())
                    .setPort(request.getPort())
                    .putAllMetadata(request.getMetadataMap())
                    .setLastHeartbeat(String.valueOf(System.currentTimeMillis()))
                    .build();

            services.put(request.getServiceId(), serviceInfo);
            registrationIds.put(request.getServiceId(), registrationId);

            RegistrationResponse response = RegistrationResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Service registered successfully")
                    .setRegistrationId(registrationId)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

            logger.info("Service registered: {} ({})", request.getServiceName(), request.getServiceId());
        } catch (Exception e) {
            logger.error("Error registering service: {}", e.getMessage());
            responseObserver.onError(e);
        }
    }

    @Override
    public void discoverServices(ServiceDiscoveryRequest request, StreamObserver<ServiceDiscoveryResponse> responseObserver) {
        try {
            ServiceDiscoveryResponse.Builder responseBuilder = ServiceDiscoveryResponse.newBuilder();
            
            services.values().stream()
                    .filter(service -> service.getServiceType().equals(request.getServiceType()))
                    .forEach(responseBuilder::addServices);

            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            logger.error("Error discovering services: {}", e.getMessage());
            responseObserver.onError(e);
        }
    }

    @Override
    public void heartbeat(HeartbeatRequest request, StreamObserver<HeartbeatResponse> responseObserver) {
        try {
            ServiceInfo service = services.get(request.getServiceId());
            if (service != null && request.getRegistrationId().equals(registrationIds.get(request.getServiceId()))) {
                ServiceInfo updatedService = ServiceInfo.newBuilder(service)
                        .setLastHeartbeat(String.valueOf(System.currentTimeMillis()))
                        .build();
                services.put(request.getServiceId(), updatedService);

                HeartbeatResponse response = HeartbeatResponse.newBuilder()
                        .setSuccess(true)
                        .setMessage("Heartbeat received")
                        .build();

                responseObserver.onNext(response);
                responseObserver.onCompleted();
            } else {
                HeartbeatResponse response = HeartbeatResponse.newBuilder()
                        .setSuccess(false)
                        .setMessage("Invalid service or registration ID")
                        .build();

                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        } catch (Exception e) {
            logger.error("Error processing heartbeat: {}", e.getMessage());
            responseObserver.onError(e);
        }
    }

    @Override
    public void unregisterService(UnregisterRequest request, StreamObserver<UnregisterResponse> responseObserver) {
        try {
            if (request.getRegistrationId().equals(registrationIds.get(request.getServiceId()))) {
                services.remove(request.getServiceId());
                registrationIds.remove(request.getServiceId());

                UnregisterResponse response = UnregisterResponse.newBuilder()
                        .setSuccess(true)
                        .setMessage("Service unregistered successfully")
                        .build();

                responseObserver.onNext(response);
                responseObserver.onCompleted();

                logger.info("Service unregistered: {}", request.getServiceId());
            } else {
                UnregisterResponse response = UnregisterResponse.newBuilder()
                        .setSuccess(false)
                        .setMessage("Invalid registration ID")
                        .build();

                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        } catch (Exception e) {
            logger.error("Error unregistering service: {}", e.getMessage());
            responseObserver.onError(e);
        }
    }

    public static void main(String[] args) throws Exception {
        final ServiceDiscoveryServer server = new ServiceDiscoveryServer();
        server.start();
    }
} 