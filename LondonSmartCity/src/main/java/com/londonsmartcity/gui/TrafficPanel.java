package com.londonsmartcity.gui;

import com.londonsmartcity.discovery.ServiceInfo;
import com.londonsmartcity.traffic.*;
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

public class TrafficPanel extends ServicePanel {
    private static final Logger logger = LoggerFactory.getLogger(TrafficPanel.class);

    private final JComboBox<String> locationComboBox;
    private final JTable trafficTable;
    private final DefaultTableModel trafficTableModel;
    private final JTextArea incidentArea;
    private final JButton reportIncidentButton;
    private final JPanel signalControlPanel;
    private final JSpinner greenDurationSpinner;
    private final JSpinner yellowDurationSpinner;
    private final JSpinner redDurationSpinner;
    private final JButton updateSignalButton;
    private final ScheduledExecutorService scheduler;
    private ManagedChannel channel;
    private TrafficServiceGrpc.TrafficServiceBlockingStub trafficStub;
    private String selectedLocation = "JUNCTION1";
    private boolean isConnected = false;

    public TrafficPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Initialize components
        locationComboBox = new JComboBox<>(new String[]{"JUNCTION1", "JUNCTION2", "JUNCTION3"});
        locationComboBox.addActionListener(e -> {
            selectedLocation = (String) locationComboBox.getSelectedItem();
            if (isConnected) {
                updateTrafficData();
            }
        });

        // Traffic status table
        String[] columnNames = {"Location", "Congestion", "Speed (km/h)", "Status", "Timestamp"};
        trafficTableModel = new DefaultTableModel(columnNames, 0);
        trafficTable = new JTable(trafficTableModel);
        JScrollPane tableScrollPane = new JScrollPane(trafficTable);

        // Incident reporting
        incidentArea = new JTextArea(5, 40);
        incidentArea.setLineWrap(true);
        incidentArea.setWrapStyleWord(true);
        JScrollPane incidentScrollPane = new JScrollPane(incidentArea);
        reportIncidentButton = new JButton("Report Incident");
        reportIncidentButton.addActionListener(e -> reportIncident());
        reportIncidentButton.setEnabled(false);

        // Signal control
        signalControlPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        signalControlPanel.setBorder(BorderFactory.createTitledBorder("Signal Timing Control"));

        greenDurationSpinner = new JSpinner(new SpinnerNumberModel(30, 10, 120, 5));
        yellowDurationSpinner = new JSpinner(new SpinnerNumberModel(3, 2, 10, 1));
        redDurationSpinner = new JSpinner(new SpinnerNumberModel(30, 10, 120, 5));

        signalControlPanel.add(new JLabel("Green Duration (s):"));
        signalControlPanel.add(greenDurationSpinner);
        signalControlPanel.add(new JLabel("Yellow Duration (s):"));
        signalControlPanel.add(yellowDurationSpinner);
        signalControlPanel.add(new JLabel("Red Duration (s):"));
        signalControlPanel.add(redDurationSpinner);

        updateSignalButton = new JButton("Update Signal Timing");
        updateSignalButton.addActionListener(e -> updateSignalTiming());
        updateSignalButton.setEnabled(false);
        signalControlPanel.add(updateSignalButton);

        // Layout
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JLabel("Select Location: "), BorderLayout.WEST);
        topPanel.add(locationComboBox, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.add(tableScrollPane, BorderLayout.CENTER);
        centerPanel.add(signalControlPanel, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.add(new JLabel("Report Incident:"), BorderLayout.NORTH);
        bottomPanel.add(incidentScrollPane, BorderLayout.CENTER);
        bottomPanel.add(reportIncidentButton, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Initialize scheduler for updates
        scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public String getServiceType() {
        return "TRAFFIC";
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
            trafficStub = TrafficServiceGrpc.newBlockingStub(channel);
            
            // Test connection
            TrafficStatusRequest testRequest = TrafficStatusRequest.newBuilder()
                    .setLocation(selectedLocation)
                    .build();
            trafficStub.getTrafficStatus(testRequest);
            
            // Connection successful
            isConnected = true;
            updateSignalButton.setEnabled(true);
            reportIncidentButton.setEnabled(true);
            startTrafficUpdates();
            logger.info("Connected to traffic service at {}:{}", service.getHost(), service.getPort());
        } catch (Exception e) {
            logger.error("Failed to connect to traffic service: {}", e.getMessage());
            disconnectService();
        }
    }

    private void disconnectService() {
        isConnected = false;
        updateSignalButton.setEnabled(false);
        reportIncidentButton.setEnabled(false);
        if (channel != null) {
            channel.shutdown();
            channel = null;
        }
        trafficStub = null;
        trafficTableModel.setRowCount(0);
        logger.info("Disconnected from traffic service");
    }

    private void startTrafficUpdates() {
        scheduler.scheduleAtFixedRate(this::updateTrafficData, 0, 5, TimeUnit.SECONDS);
    }

    private void updateTrafficData() {
        if (!isConnected || trafficStub == null) {
            return;
        }

        try {
            TrafficStatusRequest request = TrafficStatusRequest.newBuilder()
                    .setLocation(selectedLocation)
                    .build();

            TrafficStatusResponse response = trafficStub.getTrafficStatus(request);
            updateTrafficTable(response);
        } catch (Exception e) {
            logger.error("Error getting traffic status: {}", e.getMessage());
            disconnectService();
        }
    }

    private void updateTrafficTable(TrafficStatusResponse response) {
        SwingUtilities.invokeLater(() -> {
            trafficTableModel.setRowCount(0);
            trafficTableModel.addRow(new Object[]{
                    response.getLocation(),
                    response.getCongestionLevel(),
                    response.getAverageSpeed(),
                    response.getStatus(),
                    new java.util.Date(response.getTimestamp())
            });
        });
    }

    private void reportIncident() {
        if (!isConnected || trafficStub == null) {
            JOptionPane.showMessageDialog(this, "Not connected to traffic service");
            return;
        }

        try {
            String description = incidentArea.getText().trim();
            if (description.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter an incident description");
                return;
            }

            IncidentReport request = IncidentReport.newBuilder()
                    .setLocation(selectedLocation)
                    .setDescription(description)
                    .setSeverity("HIGH")
                    .build();

            IncidentResponse response = trafficStub.reportIncident(request);
            if (response.getSuccess()) {
                JOptionPane.showMessageDialog(this, "Incident reported successfully");
                incidentArea.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to report incident: " + response.getMessage());
            }
        } catch (Exception e) {
            logger.error("Error reporting incident: {}", e.getMessage());
            JOptionPane.showMessageDialog(this, "Error reporting incident: " + e.getMessage());
            disconnectService();
        }
    }

    private void updateSignalTiming() {
        if (!isConnected || trafficStub == null) {
            JOptionPane.showMessageDialog(this, "Not connected to traffic service");
            return;
        }

        try {
            int greenDuration = Integer.parseInt(greenDurationSpinner.getValue().toString());
            int yellowDuration = Integer.parseInt(yellowDurationSpinner.getValue().toString());
            int redDuration = Integer.parseInt(redDurationSpinner.getValue().toString());

            SignalTimingRequest request = SignalTimingRequest.newBuilder()
                    .setLocation(selectedLocation)
                    .setGreenDuration(greenDuration)
                    .setYellowDuration(yellowDuration)
                    .setRedDuration(redDuration)
                    .build();

            SignalTimingResponse response = trafficStub.updateSignalTiming(request);
            if (response.getSuccess()) {
                JOptionPane.showMessageDialog(this, "Signal timing updated successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update signal timing: " + response.getMessage());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for durations");
        } catch (Exception e) {
            logger.error("Error updating signal timing: {}", e.getMessage());
            JOptionPane.showMessageDialog(this, "Error updating signal timing: " + e.getMessage());
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