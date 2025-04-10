package com.londonsmartcity.gui;

import com.londonsmartcity.discovery.ServiceDiscoveryGrpc;
import com.londonsmartcity.discovery.ServiceDiscoveryRequest;
import com.londonsmartcity.discovery.ServiceInfo;
import com.londonsmartcity.discovery.ServiceDiscoveryResponse;
import com.londonsmartcity.traffic.*;
import com.londonsmartcity.transport.*;
import com.londonsmartcity.environment.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SmartCityController extends JFrame {
    private static final Logger logger = LoggerFactory.getLogger(SmartCityController.class);
    private static final String DISCOVERY_SERVICE_HOST = "localhost";
    private static final int DISCOVERY_SERVICE_PORT = 50051;

    private final JTabbedPane tabbedPane;
    private final TrafficPanel trafficPanel;
    private final TransportPanel transportPanel;
    private final EnvironmentPanel environmentPanel;
    private final ScheduledExecutorService scheduler;
    private final ManagedChannel discoveryChannel;
    private final ServiceDiscoveryGrpc.ServiceDiscoveryBlockingStub discoveryStub;

    public SmartCityController() {
        super("London Smart City Controller");
        
        // Initialize gRPC channel to discovery service
        discoveryChannel = ManagedChannelBuilder.forAddress(DISCOVERY_SERVICE_HOST, DISCOVERY_SERVICE_PORT)
                .usePlaintext()
                .build();
        discoveryStub = ServiceDiscoveryGrpc.newBlockingStub(discoveryChannel);
        
        // Initialize scheduler for periodic updates
        scheduler = Executors.newScheduledThreadPool(1);
        
        // Initialize panels
        trafficPanel = new TrafficPanel();
        transportPanel = new TransportPanel();
        environmentPanel = new EnvironmentPanel();
        
        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Traffic Management", trafficPanel);
        tabbedPane.addTab("Public Transport", transportPanel);
        tabbedPane.addTab("Environment", environmentPanel);
        
        // Set up the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        
        // Add components
        add(tabbedPane);
        
        // Start service discovery
        startServiceDiscovery();
    }

    private void startServiceDiscovery() {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                // Discover traffic services
                ServiceDiscoveryRequest trafficRequest = ServiceDiscoveryRequest.newBuilder()
                        .setServiceType("TRAFFIC")
                        .build();
                ServiceDiscoveryResponse trafficResponse = discoveryStub.discoverServices(trafficRequest);
                updateTrafficServices(trafficResponse.getServicesList());

                // Discover transport services
                ServiceDiscoveryRequest transportRequest = ServiceDiscoveryRequest.newBuilder()
                        .setServiceType("TRANSPORT")
                        .build();
                ServiceDiscoveryResponse transportResponse = discoveryStub.discoverServices(transportRequest);
                updateTransportServices(transportResponse.getServicesList());

                // Discover environment services
                ServiceDiscoveryRequest environmentRequest = ServiceDiscoveryRequest.newBuilder()
                        .setServiceType("ENVIRONMENT")
                        .build();
                ServiceDiscoveryResponse environmentResponse = discoveryStub.discoverServices(environmentRequest);
                updateEnvironmentServices(environmentResponse.getServicesList());
            } catch (Exception e) {
                logger.error("Error discovering services: {}", e.getMessage());
            }
        }, 0, 30, TimeUnit.SECONDS);
    }

    private void updateTrafficServices(List<ServiceInfo> services) {
        SwingUtilities.invokeLater(() -> trafficPanel.updateServices(services));
    }

    private void updateTransportServices(List<ServiceInfo> services) {
        SwingUtilities.invokeLater(() -> transportPanel.updateServices(services));
    }

    private void updateEnvironmentServices(List<ServiceInfo> services) {
        SwingUtilities.invokeLater(() -> environmentPanel.updateServices(services));
    }

    public void stop() {
        scheduler.shutdown();
        discoveryChannel.shutdown();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                logger.error("Error setting look and feel: {}", e.getMessage());
            }

            SmartCityController controller = new SmartCityController();
            controller.setVisible(true);
        });
    }
} 