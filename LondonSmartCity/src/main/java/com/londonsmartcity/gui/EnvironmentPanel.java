package com.londonsmartcity.gui;

import com.londonsmartcity.discovery.ServiceInfo;
import com.londonsmartcity.environment.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Date;

public class EnvironmentPanel extends JPanel {
    private static final Logger logger = LoggerFactory.getLogger(EnvironmentPanel.class);

    private final JComboBox<String> locationComboBox;
    private final JTable environmentalDataTable;
    private final DefaultTableModel environmentalDataTableModel;
    private final JTextArea alertArea;
    private final JButton reportAlertButton;
    private final JPanel chartPanel;
    private final ScheduledExecutorService scheduler;
    private ManagedChannel channel;
    private EnvironmentServiceGrpc.EnvironmentServiceBlockingStub environmentStub;
    private String selectedLocation = "STATION1";
    private boolean isConnected = false;

    public EnvironmentPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Initialize components
        locationComboBox = new JComboBox<>(new String[]{"STATION1", "STATION2", "STATION3"});
        locationComboBox.addActionListener(e -> {
            selectedLocation = (String) locationComboBox.getSelectedItem();
            if (isConnected) {
                updateEnvironmentalData();
            }
        });

        // Environmental data table
        String[] columnNames = {"Location", "PM2.5", "PM10", "NO2", "O3", "Noise (dB)", "Temperature (°C)", "Humidity (%)", "Wind Speed (m/s)", "Weather", "Timestamp"};
        environmentalDataTableModel = new DefaultTableModel(columnNames, 0);
        environmentalDataTable = new JTable(environmentalDataTableModel);
        JScrollPane tableScrollPane = new JScrollPane(environmentalDataTable);

        // Alert reporting
        alertArea = new JTextArea(5, 40);
        alertArea.setLineWrap(true);
        alertArea.setWrapStyleWord(true);
        JScrollPane alertScrollPane = new JScrollPane(alertArea);
        reportAlertButton = new JButton("Report Alert");
        reportAlertButton.addActionListener(e -> reportAlert());
        reportAlertButton.setEnabled(false);

        // Chart panel (placeholder for historical data visualization)
        chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw a simple chart representation
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.BLACK);
                g.drawRect(50, 50, getWidth() - 100, getHeight() - 100);
                g.drawString("Environmental Data History", getWidth() / 2 - 80, 30);
            }
        };
        chartPanel.setPreferredSize(new Dimension(400, 300));
        chartPanel.setBorder(BorderFactory.createTitledBorder("Historical Data"));

        // Layout
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JLabel("Select Location: "), BorderLayout.WEST);
        topPanel.add(locationComboBox, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.add(tableScrollPane, BorderLayout.CENTER);
        centerPanel.add(chartPanel, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.add(new JLabel("Report Alert:"), BorderLayout.NORTH);
        bottomPanel.add(alertScrollPane, BorderLayout.CENTER);
        bottomPanel.add(reportAlertButton, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Initialize scheduler for updates
        scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    public void updateServices(List<ServiceInfo> services) {
        if (services == null || services.isEmpty()) {
            disconnectService();
            return;
        }

        ServiceInfo service = services.get(0);
        try {
            if (channel != null) {
                channel.shutdown();
            }
            
            channel = ManagedChannelBuilder.forAddress(service.getHost(), service.getPort())
                    .usePlaintext()
                    .build();
            environmentStub = EnvironmentServiceGrpc.newBlockingStub(channel);
            
            // Test connection
            AirQualityRequest testRequest = AirQualityRequest.newBuilder()
                    .setLocationId(selectedLocation)
                    .build();
            environmentStub.getAirQuality(testRequest);
            
            // Connection successful
            isConnected = true;
            reportAlertButton.setEnabled(true);
            startEnvironmentUpdates();
            logger.info("Connected to environment service at {}:{}", service.getHost(), service.getPort());
        } catch (Exception e) {
            logger.error("Failed to connect to environment service: {}", e.getMessage());
            disconnectService();
        }
    }

    private void disconnectService() {
        isConnected = false;
        reportAlertButton.setEnabled(false);
        if (channel != null) {
            channel.shutdown();
            channel = null;
        }
        environmentStub = null;
        environmentalDataTableModel.setRowCount(0);
        logger.info("Disconnected from environment service");
    }

    private void startEnvironmentUpdates() {
        scheduler.scheduleAtFixedRate(this::updateEnvironmentalData, 0, 5, TimeUnit.SECONDS);
    }

    private void updateEnvironmentalData() {
        if (!isConnected || environmentStub == null) {
            return;
        }

        try {
            EnvironmentalDataRequest request = EnvironmentalDataRequest.newBuilder()
                    .setLocationId(selectedLocation)
                    .build();

            EnvironmentalData response = environmentStub.streamEnvironmentalData(request).next();
            updateEnvironmentalDataTable(response);
        } catch (Exception e) {
            logger.error("Error getting environmental data: {}", e.getMessage());
            disconnectService();
        }
    }

    private void updateEnvironmentalDataTable(EnvironmentalData response) {
        SwingUtilities.invokeLater(() -> {
            environmentalDataTableModel.setRowCount(0);
            environmentalDataTableModel.addRow(new Object[]{
                    response.getLocationId(),
                    String.format("%.1f", response.getPm25()),
                    String.format("%.1f", response.getPm10()),
                    String.format("%.1f", response.getNo2()),
                    String.format("%.1f", response.getO3()),
                    String.format("%.1f", response.getDecibels()),
                    String.format("%.1f", response.getTemperature()),
                    String.format("%.1f", response.getHumidity()),
                    String.format("%.1f", response.getWindSpeed()),
                    response.getWeatherCondition(),
                    new Date(response.getTimestamp())
            });
        });
    }

    private void reportAlert() {
        if (!isConnected || environmentStub == null) {
            JOptionPane.showMessageDialog(this, "Not connected to environment service");
            return;
        }

        try {
            String description = alertArea.getText().trim();
            if (description.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter an alert description");
                return;
            }

            AirQualityAlert request = AirQualityAlert.newBuilder()
                    .setLocationId(selectedLocation)
                    .setDescription(description)
                    .setSeverity("HIGH")
                    .build();

            AlertResponse response = environmentStub.reportAirQualityAlert(request);
            if (response.getSuccess()) {
                JOptionPane.showMessageDialog(this, "Alert reported successfully");
                alertArea.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to report alert: " + response.getMessage());
            }
        } catch (Exception e) {
            logger.error("Error reporting alert: {}", e.getMessage());
            JOptionPane.showMessageDialog(this, "Error reporting alert: " + e.getMessage());
            disconnectService();
        }
    }

    public void stop() {
        scheduler.shutdown();
        if (channel != null) {
            channel.shutdown();
        }
    }
} 