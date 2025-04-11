package com.londonsmartcity.common;

import com.londonsmartcity.discovery.ServiceDiscoveryGrpc;
import com.londonsmartcity.discovery.ServiceRegistration;
import com.londonsmartcity.discovery.RegistrationResponse;
import com.londonsmartcity.discovery.HeartbeatRequest;
import com.londonsmartcity.discovery.HeartbeatResponse;
import com.londonsmartcity.discovery.UnregisterRequest;
import com.londonsmartcity.discovery.UnregisterResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class BaseService {
    private static final Logger logger = LoggerFactory.getLogger(BaseService.class);
    private static final String DISCOVERY_SERVICE_HOST = "localhost";
    private static final int DISCOVERY_SERVICE_PORT = 50051;
    private static final int HEARTBEAT_INTERVAL_SECONDS = 30;

    protected final String serviceId;
    protected final String serviceType;
    protected final String serviceName;
    protected final int port;
    protected final Map<String, String> metadata;
    protected final ManagedChannel discoveryChannel;
    protected final ServiceDiscoveryGrpc.ServiceDiscoveryBlockingStub discoveryStub;
    protected final ScheduledExecutorService scheduler;
    protected String registrationId;

    protected BaseService(String serviceType, String serviceName, int port, Map<String, String> metadata) {
        this.serviceId = UUID.randomUUID().toString();
        this.serviceType = serviceType;
        this.serviceName = serviceName;
        this.port = port;
        this.metadata = metadata;
        
        // Initialize gRPC channel to discovery service
        this.discoveryChannel = ManagedChannelBuilder.forAddress(DISCOVERY_SERVICE_HOST, DISCOVERY_SERVICE_PORT)
                .usePlaintext()
                .build();
        this.discoveryStub = ServiceDiscoveryGrpc.newBlockingStub(discoveryChannel);
        
        // Initialize scheduler for heartbeats
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    public void start() {
        try {
            // Register service
            registerService();
            
            // Start heartbeat
            startHeartbeat();
            
            logger.info("{} service started successfully", serviceName);
        } catch (Exception e) {
            logger.error("Failed to start {} service: {}", serviceName, e.getMessage());
            throw new RuntimeException("Service startup failed", e);
        }
    }

    public void stop() {
        try {
            // Stop heartbeat
            scheduler.shutdown();
            
            // Unregister service
            unregisterService();
            
            // Shutdown discovery channel
            discoveryChannel.shutdown();
            
            logger.info("{} service stopped successfully", serviceName);
        } catch (Exception e) {
            logger.error("Error stopping {} service: {}", serviceName, e.getMessage());
        }
    }

    private void registerService() {
        ServiceRegistration registration = ServiceRegistration.newBuilder()
                .setServiceId(serviceId)
                .setServiceType(serviceType)
                .setServiceName(serviceName)
                .setHost("localhost")
                .setPort(port)
                .putAllMetadata(metadata)
                .build();

        RegistrationResponse response = discoveryStub.registerService(registration);
        if (response.getSuccess()) {
            registrationId = response.getRegistrationId();
            logger.info("{} service registered successfully with ID: {}", serviceName, registrationId);
        } else {
            throw new RuntimeException("Failed to register service: " + response.getMessage());
        }
    }

    private void startHeartbeat() {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                HeartbeatRequest request = HeartbeatRequest.newBuilder()
                        .setServiceId(serviceId)
                        .setRegistrationId(registrationId)
                        .build();

                HeartbeatResponse response = discoveryStub.heartbeat(request);
                if (!response.getSuccess()) {
                    logger.warn("Heartbeat failed: {}", response.getMessage());
                }
            } catch (Exception e) {
                logger.error("Error sending heartbeat: {}", e.getMessage());
            }
        }, HEARTBEAT_INTERVAL_SECONDS, HEARTBEAT_INTERVAL_SECONDS, TimeUnit.SECONDS);
    }

    private void unregisterService() {
        if (registrationId != null) {
            UnregisterRequest request = UnregisterRequest.newBuilder()
                    .setServiceId(serviceId)
                    .setRegistrationId(registrationId)
                    .build();

            UnregisterResponse response = discoveryStub.unregisterService(request);
            if (response.getSuccess()) {
                logger.info("{} service unregistered successfully", serviceName);
            } else {
                logger.warn("Failed to unregister service: {}", response.getMessage());
            }
        }
    }
} 