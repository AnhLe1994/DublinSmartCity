package com.londonsmartcity;

import com.londonsmartcity.discovery.ServiceDiscoveryServer;
import com.londonsmartcity.environment.EnvironmentService;
import com.londonsmartcity.traffic.TrafficService;
import com.londonsmartcity.transport.TransportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ServiceManager {
    private static final Logger logger = LoggerFactory.getLogger(ServiceManager.class);
    private final List<Service> services;
    private final CountDownLatch servicesLatch;

    public ServiceManager() {
        this.services = new ArrayList<>();
        this.servicesLatch = new CountDownLatch(4);
    }

    public void startAllServices() throws InterruptedException {
        // Initialize all services
        services.add(new Service("Discovery", () -> {
            ServiceDiscoveryServer discoveryServer = new ServiceDiscoveryServer();
            discoveryServer.start();
            return discoveryServer;
        }));
        
        services.add(new Service("Traffic", () -> {
            TrafficService trafficService = new TrafficService();
            trafficService.start();
            return trafficService;
        }));
        
        services.add(new Service("Transport", () -> {
            TransportService transportService = new TransportService();
            transportService.start();
            return transportService;
        }));
        
        services.add(new Service("Environment", () -> {
            EnvironmentService environmentService = new EnvironmentService();
            environmentService.start();
            return environmentService;
        }));

        // Start all services in parallel
        for (Service service : services) {
            new Thread(() -> {
                try {
                    service.start();
                    servicesLatch.countDown();
                    logger.info("{} Service started successfully", service.getName());
                } catch (Exception e) {
                    logger.error("Failed to start {} Service: {}", service.getName(), e.getMessage());
                }
            }, service.getName() + "-Thread").start();
        }

        // Wait for services to start
        if (!servicesLatch.await(30, TimeUnit.SECONDS)) {
            logger.warn("Not all services started within timeout period");
        }
    }

    public void stopAllServices() {
        for (Service service : services) {
            try {
                service.stop();
                logger.info("{} Service stopped successfully", service.getName());
            } catch (Exception e) {
                logger.error("Failed to stop {} Service: {}", service.getName(), e.getMessage());
            }
        }
    }

    private static class Service {
        private final String name;
        private final ServiceStarter starter;
        private Object serviceInstance;

        public Service(String name, ServiceStarter starter) {
            this.name = name;
            this.starter = starter;
        }

        public String getName() {
            return name;
        }

        public void start() throws Exception {
            serviceInstance = starter.start();
        }

        public void stop() throws Exception {
            if (serviceInstance != null) {
                // Add stop logic here based on service type
                if (serviceInstance instanceof AutoCloseable) {
                    ((AutoCloseable) serviceInstance).close();
                }
            }
        }
    }

    @FunctionalInterface
    private interface ServiceStarter {
        Object start() throws Exception;
    }
} 