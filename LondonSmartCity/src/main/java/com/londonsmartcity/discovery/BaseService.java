package com.londonsmartcity.discovery;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public abstract class BaseService {
    private static final Logger logger = LoggerFactory.getLogger(BaseService.class);
    protected static final String DISCOVERY_HOST = "localhost";
    protected static final int DISCOVERY_PORT = 50051;
    
    protected final String serviceName;
    protected final String serviceType;
    protected final int servicePort;
    protected ManagedChannel discoveryChannel;
    protected String serviceId;
    protected String registrationId;
    
    protected BaseService(String serviceName, String serviceType, int servicePort) {
        this.serviceName = serviceName;
        this.serviceType = serviceType;
        this.servicePort = servicePort;
        this.serviceId = serviceType + "-" + System.currentTimeMillis();
        this.discoveryChannel = ManagedChannelBuilder.forAddress(DISCOVERY_HOST, DISCOVERY_PORT)
                .usePlaintext()
                .build();
    }
    
    protected void registerService() {
        try {
            ServiceDiscoveryGrpc.ServiceDiscoveryBlockingStub stub = ServiceDiscoveryGrpc.newBlockingStub(discoveryChannel);
            ServiceRegistration registration = ServiceRegistration.newBuilder()
                    .setServiceId(serviceId)
                    .setServiceType(serviceType)
                    .setServiceName(serviceName)
                    .setHost("localhost")
                    .setPort(servicePort)
                    .build();
            
            RegistrationResponse response = stub.registerService(registration);
            if (response.getSuccess()) {
                registrationId = response.getRegistrationId();
                logger.info("Service {} registered successfully with ID {}", serviceName, registrationId);
            } else {
                logger.error("Failed to register service {}: {}", serviceName, response.getMessage());
            }
        } catch (Exception e) {
            logger.error("Failed to register service {}: {}", serviceName, e.getMessage());
        }
    }
    
    protected void unregisterService() {
        try {
            ServiceDiscoveryGrpc.ServiceDiscoveryBlockingStub stub = ServiceDiscoveryGrpc.newBlockingStub(discoveryChannel);
            UnregisterRequest request = UnregisterRequest.newBuilder()
                    .setServiceId(serviceId)
                    .setRegistrationId(registrationId)
                    .build();
            
            UnregisterResponse response = stub.unregisterService(request);
            if (response.getSuccess()) {
                logger.info("Service {} unregistered successfully", serviceName);
            } else {
                logger.error("Failed to unregister service {}: {}", serviceName, response.getMessage());
            }
        } catch (Exception e) {
            logger.error("Failed to unregister service {}: {}", serviceName, e.getMessage());
        }
    }
    
    protected void shutdown() {
        try {
            if (discoveryChannel != null) {
                discoveryChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            logger.error("Error shutting down discovery channel: {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
} 