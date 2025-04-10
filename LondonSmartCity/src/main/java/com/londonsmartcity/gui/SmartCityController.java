package com.londonsmartcity.gui;

import com.londonsmartcity.discovery.ServiceDiscoveryGrpc;
import com.londonsmartcity.discovery.ServiceDiscoveryRequest;
import com.londonsmartcity.discovery.ServiceInfo;
import com.londonsmartcity.discovery.ServiceDiscoveryResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SmartCityController extends JFrame {
    private static final Logger logger = LoggerFactory.getLogger(SmartCityController.class);
    private static final String DISCOVERY_SERVICE_HOST = "localhost";
    private static final int DISCOVERY_SERVICE_PORT = 50051;
    private static final int UPDATE_INTERVAL_SECONDS = 30;

    private final JTabbedPane tabbedPane;
    private final ServicePanel[] servicePanels;
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
        
        // Initialize service panels
        servicePanels = new ServicePanel[] {
            new TrafficPanel(),
            new TransportPanel(),
            new EnvironmentPanel()
        };
        
        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Traffic Management", servicePanels[0]);
        tabbedPane.addTab("Public Transport", servicePanels[1]);
        tabbedPane.addTab("Environment", servicePanels[2]);
        
        // Set up the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        add(tabbedPane);
        
        // Start service discovery
        startServiceDiscovery();
    }

    private void startServiceDiscovery() {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                // Discover services for each panel
                for (int i = 0; i < servicePanels.length; i++) {
                    String serviceType = servicePanels[i].getServiceType();
                    ServiceDiscoveryRequest request = ServiceDiscoveryRequest.newBuilder()
                            .setServiceType(serviceType)
                            .build();
                    ServiceDiscoveryResponse response = discoveryStub.discoverServices(request);
                    updateServicePanel(i, response.getServicesList());
                }
            } catch (Exception e) {
                logger.error("Error discovering services: {}", e.getMessage());
            }
        }, 0, UPDATE_INTERVAL_SECONDS, TimeUnit.SECONDS);
    }

    private void updateServicePanel(int panelIndex, List<ServiceInfo> services) {
        SwingUtilities.invokeLater(() -> servicePanels[panelIndex].updateServices(services));
    }

    @Override
    public void dispose() {
        scheduler.shutdown();
        discoveryChannel.shutdown();
        super.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                SmartCityController controller = new SmartCityController();
                controller.setVisible(true);
            } catch (Exception e) {
                logger.error("Error starting Smart City Controller: {}", e.getMessage());
            }
        });
    }
} 