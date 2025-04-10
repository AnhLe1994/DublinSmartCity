package com.londonsmartcity;

import com.londonsmartcity.gui.SmartCityController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class LondonSmartCityLauncher {
    private static final Logger logger = LoggerFactory.getLogger(LondonSmartCityLauncher.class);
    private static ServiceManager serviceManager;

    public static void main(String[] args) {
        try {
            // Set the look and feel to the system default
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
            // Initialize and start services
            serviceManager = new ServiceManager();
            serviceManager.startAllServices();

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
                if (serviceManager != null) {
                    serviceManager.stopAllServices();
                }
            }));

        } catch (Exception e) {
            logger.error("Failed to start London Smart City system: {}", e.getMessage());
            System.exit(1);
        }
    }
} 