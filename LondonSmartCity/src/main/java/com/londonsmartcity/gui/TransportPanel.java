package com.londonsmartcity.gui;

import com.londonsmartcity.discovery.ServiceInfo;
import com.londonsmartcity.transport.*;
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

public class TransportPanel extends ServicePanel {
    private static final Logger logger = LoggerFactory.getLogger(TransportPanel.class);

    private final JComboBox<String> transportTypeComboBox;
    private final JComboBox<String> stopComboBox;
    private final JTable arrivalTable;
    private final DefaultTableModel arrivalTableModel;
    private final JTable serviceStatusTable;
    private final DefaultTableModel serviceStatusTableModel;
    private final JTextArea disruptionArea;
    private final JButton reportDisruptionButton;
    private final JPanel mapPanel;
    private final ScheduledExecutorService scheduler;
    private ManagedChannel channel;
    private TransportServiceGrpc.TransportServiceBlockingStub transportStub;
    private String selectedTransportType = "BUS";
    private String selectedStop = "STOP1";
    private boolean isConnected = false;
    private String selectedLine = "LINE1";

    public TransportPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Initialize components
        transportTypeComboBox = new JComboBox<>(new String[]{"BUS", "TUBE", "TRAIN"});
        transportTypeComboBox.addActionListener(e -> {
            selectedTransportType = (String) transportTypeComboBox.getSelectedItem();
            if (isConnected) {
                updateTransportData();
            }
        });

        stopComboBox = new JComboBox<>(new String[]{"STOP1", "STOP2"});
        stopComboBox.addActionListener(e -> {
            selectedStop = (String) stopComboBox.getSelectedItem();
            if (isConnected) {
                updateTransportData();
            }
        });

        // Arrival information table
        String[] arrivalColumnNames = {"Vehicle ID", "Minutes Until Arrival", "Status"};
        arrivalTableModel = new DefaultTableModel(arrivalColumnNames, 0);
        arrivalTable = new JTable(arrivalTableModel);
        JScrollPane arrivalScrollPane = new JScrollPane(arrivalTable);

        // Service status table
        String[] statusColumnNames = {"Line", "Status", "Message"};
        serviceStatusTableModel = new DefaultTableModel(statusColumnNames, 0);
        serviceStatusTable = new JTable(serviceStatusTableModel);
        JScrollPane statusScrollPane = new JScrollPane(serviceStatusTable);

        // Disruption reporting
        disruptionArea = new JTextArea(5, 40);
        disruptionArea.setLineWrap(true);
        disruptionArea.setWrapStyleWord(true);
        JScrollPane disruptionScrollPane = new JScrollPane(disruptionArea);
        reportDisruptionButton = new JButton("Report Disruption");
        reportDisruptionButton.addActionListener(e -> reportDisruption());
        reportDisruptionButton.setEnabled(false);

        // Map panel (placeholder for vehicle locations)
        mapPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw a simple map representation
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.BLACK);
                g.drawRect(50, 50, getWidth() - 100, getHeight() - 100);
                g.drawString("Vehicle Locations Map", getWidth() / 2 - 50, 30);
            }
        };
        mapPanel.setPreferredSize(new Dimension(400, 300));
        mapPanel.setBorder(BorderFactory.createTitledBorder("Vehicle Locations"));

        // Layout
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Transport Type: "));
        topPanel.add(transportTypeComboBox);
        topPanel.add(new JLabel("Stop: "));
        topPanel.add(stopComboBox);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        centerPanel.add(arrivalScrollPane);
        centerPanel.add(statusScrollPane);

        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.add(new JLabel("Report Disruption:"), BorderLayout.NORTH);
        bottomPanel.add(disruptionScrollPane, BorderLayout.CENTER);
        bottomPanel.add(reportDisruptionButton, BorderLayout.SOUTH);

        JPanel rightPanel = new JPanel(new BorderLayout(10, 10));
        rightPanel.add(mapPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        add(rightPanel, BorderLayout.EAST);

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
            transportStub = TransportServiceGrpc.newBlockingStub(channel);
            
            // Test connection
            ServiceStatusRequest testRequest = ServiceStatusRequest.newBuilder()
                    .setTransportType(selectedTransportType)
                    .build();
            transportStub.getServiceStatus(testRequest);
            
            // Connection successful
            isConnected = true;
            reportDisruptionButton.setEnabled(true);
            startTransportUpdates();
            logger.info("Connected to transport service at {}:{}", service.getHost(), service.getPort());
        } catch (Exception e) {
            logger.error("Failed to connect to transport service: {}", e.getMessage());
            disconnectService();
        }
    }

    private void disconnectService() {
        isConnected = false;
        reportDisruptionButton.setEnabled(false);
        if (channel != null) {
            channel.shutdown();
            channel = null;
        }
        transportStub = null;
        arrivalTableModel.setRowCount(0);
        serviceStatusTableModel.setRowCount(0);
        logger.info("Disconnected from transport service");
    }

    private void startTransportUpdates() {
        scheduler.scheduleAtFixedRate(this::updateTransportData, 0, 10, TimeUnit.SECONDS);
    }

    private void updateTransportData() {
        if (!isConnected || transportStub == null) {
            return;
        }

        try {
            // Update arrival information
            ArrivalInfoRequest arrivalRequest = ArrivalInfoRequest.newBuilder()
                    .setStopId(selectedStop)
                    .setLineId(selectedLine)
                    .build();

            ArrivalInfoResponse arrivalResponse = transportStub.getArrivalInfo(arrivalRequest);
            updateArrivalTable(arrivalResponse);

            // Update service status
            ServiceStatusRequest statusRequest = ServiceStatusRequest.newBuilder()
                    .setLineId(selectedLine)
                    .build();

            ServiceStatusResponse statusResponse = transportStub.getServiceStatus(statusRequest);
            updateServiceStatusTable(statusResponse);
        } catch (Exception e) {
            logger.error("Error updating transport data: {}", e.getMessage());
            disconnectService();
        }
    }

    private void updateArrivalTable(ArrivalInfoResponse response) {
        SwingUtilities.invokeLater(() -> {
            arrivalTableModel.setRowCount(0);
            response.getArrivalsList().forEach(arrival -> 
                arrivalTableModel.addRow(new Object[]{
                        arrival.getVehicleId(),
                        arrival.getMinutesUntilArrival(),
                        arrival.getStatus()
                })
            );
        });
    }

    private void updateServiceStatusTable(ServiceStatusResponse response) {
        SwingUtilities.invokeLater(() -> {
            serviceStatusTableModel.setRowCount(0);
            serviceStatusTableModel.addRow(new Object[]{
                    response.getLineId(),
                    response.getStatus(),
                    response.getMessage()
            });
        });
    }

    private void reportDisruption() {
        if (!isConnected || transportStub == null) {
            JOptionPane.showMessageDialog(this, "Not connected to transport service");
            return;
        }

        try {
            String description = disruptionArea.getText().trim();
            if (description.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a disruption description");
                return;
            }

            DisruptionReport request = DisruptionReport.newBuilder()
                    .setLineId(selectedLine)
                    .setDescription(description)
                    .setSeverity("MEDIUM")
                    .build();

            DisruptionResponse response = transportStub.reportDisruption(request);
            if (response.getSuccess()) {
                JOptionPane.showMessageDialog(this, "Disruption reported successfully");
                disruptionArea.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to report disruption: " + response.getMessage());
            }
        } catch (Exception e) {
            logger.error("Error reporting disruption: {}", e.getMessage());
            JOptionPane.showMessageDialog(this, "Error reporting disruption: " + e.getMessage());
            disconnectService();
        }
    }

    public void stop() {
        scheduler.shutdown();
        if (channel != null) {
            channel.shutdown();
        }
    }

    @Override
    public String getServiceType() {
        return "TRANSPORT";
    }
} 