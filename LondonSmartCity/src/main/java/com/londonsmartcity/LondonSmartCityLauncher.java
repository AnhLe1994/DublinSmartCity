package com.londonsmartcity;

import com.londonsmartcity.discovery.ServiceDiscoveryServer;
import com.londonsmartcity.environment.EnvironmentService;
import com.londonsmartcity.traffic.TrafficService;
import com.londonsmartcity.transport.TransportService;
import com.londonsmartcity.gui.SmartCityController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class LondonSmartCityLauncher {
    private static final Logger logger = LoggerFactory.getLogger(LondonSmartCityLauncher.class);

    public static void main(String[] args) {
        try {
            // Set the look and feel to the system default
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
            // Create a latch to ensure services are started before the GUI
            CountDownLatch servicesLatch = new CountDownLatch(4);
            
            // Start Service Discovery Server
            new Thread(() -> {
                try {
                    ServiceDiscoveryServer discoveryServer = new ServiceDiscoveryServer();
                    discoveryServer.start();
                    servicesLatch.countDown();
                    logger.info("Service Discovery Server started successfully");
                } catch (Exception e) {
                    logger.error("Failed to start Service Discovery Server: {}", e.getMessage());
                }
            }, "DiscoveryServer-Thread").start();

            // Start Traffic Service
            new Thread(() -> {
                try {
                    TrafficService trafficService = new TrafficService();
                    trafficService.start();
                    servicesLatch.countDown();
                    logger.info("Traffic Service started successfully");
                } catch (Exception e) {
                    logger.error("Failed to start Traffic Service: {}", e.getMessage());
                }
            }, "TrafficService-Thread").start();

            // Start Transport Service
            new Thread(() -> {
                try {
                    TransportService transportService = new TransportService();
                    transportService.start();
                    servicesLatch.countDown();
                    logger.info("Transport Service started successfully");
                } catch (Exception e) {
                    logger.error("Failed to start Transport Service: {}", e.getMessage());
                }
            }, "TransportService-Thread").start();

            // Start Environment Service
            new Thread(() -> {
                try {
                    EnvironmentService environmentService = new EnvironmentService();
                    environmentService.start();
                    servicesLatch.countDown();
                    logger.info("Environment Service started successfully");
                } catch (Exception e) {
                    logger.error("Failed to start Environment Service: {}", e.getMessage());
                }
            }, "EnvironmentService-Thread").start();

            // Wait for services to start (with timeout)
            if (!servicesLatch.await(30, TimeUnit.SECONDS)) {
                logger.warn("Not all services started within timeout period");
            }

            // Start GUI
            SwingUtilities.invokeLater(() -> {
                try {
                    SmartCityController controller = new SmartCityController();
                    controller.setVisible(true);
                    logger.info("Smart City Controller GUI started successfully");
                } catch (Exception e) {
                    logger.error("Failed to start Smart City Controller: {}", e.getMessage());
                    System.exit(1);
                }
            });

            // Add shutdown hook
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                logger.info("Shutting down London Smart City system...");
                // Cleanup code can be added here
            }));

        } catch (Exception e) {
            logger.error("Failed to start London Smart City system: {}", e.getMessage());
            System.exit(1);
        }
    }
} 