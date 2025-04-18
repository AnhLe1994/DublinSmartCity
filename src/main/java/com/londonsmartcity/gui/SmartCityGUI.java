package com.londonsmartcity.gui;

import com.londonsmartcity.service.environment.EnvironmentClient;
import com.londonsmartcity.service.environment.EnvironmentServiceImpl;
import com.londonsmartcity.service.traffic.TrafficClient;
import com.londonsmartcity.service.traffic.TrafficServiceImpl;
import com.londonsmartcity.service.transport.TransportClient;
import com.londonsmartcity.service.transport.TransportServiceImpl;
import com.londonsmartcity.proto.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtils;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;

public class SmartCityGUI extends JFrame {
    private JTextArea outputArea;
    private EnvironmentClient environmentClient;
    private TrafficClient trafficClient;
    private TransportClient transportClient;
    private final ScheduledExecutorService scheduler;
    private final Map<String, TimeSeries> timeSeriesMap;
    private JComboBox<String> locationComboBox;
    private JSpinner updateFrequencySpinner;
    private final JTabbedPane tabbedPane;
    private final List<Process> serverProcesses;
    private final Map<String, JLabel> serviceStatusLabels;
    private final Map<String, JButton> startButtons;
    private final Map<String, JButton> stopButtons;
    private static final Color PRIMARY_COLOR = new Color(0, 123, 255);
    private static final Color SECONDARY_COLOR = new Color(108, 117, 125);
    private static final Color SUCCESS_COLOR = new Color(40, 167, 69);
    private static final Color DANGER_COLOR = new Color(220, 53, 69);
    private static final Color WARNING_COLOR = new Color(255, 193, 7);
    private static final Color INFO_COLOR = new Color(23, 162, 184);
    private static final Color BG_COLOR = new Color(247, 250, 252);
    private static final Color PANEL_BG = new Color(255, 255, 255);
    private static final Color TEXT_PRIMARY = new Color(45, 55, 72);
    private static final Color TEXT_SECONDARY = new Color(113, 128, 150);
    
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 16);
    private static final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 16);
    private static final Font REGULAR_FONT = new Font("Segoe UI", Font.PLAIN, 14);

    private final Map<String, Boolean> serviceRunningStatus;
    private final Map<String, String> serviceLastStatus;
    private final Map<String, javax.swing.Timer> serviceUpdateTimers;
    private final Map<String, Server> grpcServers;
    private final Map<String, Boolean> serverStatus;
    private final ScheduledExecutorService statusChecker;

    public SmartCityGUI(String host, int envPort, int trafficPort, int transportPort) {
        this.serverProcesses = new ArrayList<>();
        this.timeSeriesMap = new HashMap<>();
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.tabbedPane = new JTabbedPane();
        this.serviceStatusLabels = new HashMap<>();
        this.startButtons = new HashMap<>();
        this.stopButtons = new HashMap<>();

        this.serviceRunningStatus = new HashMap<>();
        this.serviceLastStatus = new HashMap<>();
        this.serviceUpdateTimers = new HashMap<>();

        this.grpcServers = new HashMap<>();
        this.serverStatus = new HashMap<>();
        this.statusChecker = Executors.newSingleThreadScheduledExecutor();

        // Initialize service status
        serviceRunningStatus.put("Environment Service", false);
        serviceRunningStatus.put("Traffic Service", false);
        serviceRunningStatus.put("Transport Service", false);

        serviceLastStatus.put("Environment Service", "Not connected");
        serviceLastStatus.put("Traffic Service", "Not connected");
        serviceLastStatus.put("Transport Service", "Not connected");

        // Initialize server status
        serverStatus.put("Environment Service", false);
        serverStatus.put("Traffic Service", false);
        serverStatus.put("Transport Service", false);

        // Set up the frame with minimum size and preferred size
        setTitle("London Smart City Monitor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(1200, 800));
        setLayout(new BorderLayout(10, 10));  // Add gaps between components

        // Add window state listener for maximized/restored state
        addWindowStateListener(new WindowStateListener() {
            public void windowStateChanged(WindowEvent e) {
                if (e.getNewState() == Frame.MAXIMIZED_BOTH) {
                    adjustComponentsForMaximized();
                } else {
                    adjustComponentsForRestored();
                }
            }
        });

        // Add component listener for resize events
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                adjustLayoutForNewSize();
            }
        });

        // Create panels with padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        tabbedPane.addTab("Dashboard", createDashboardPanel());
        tabbedPane.addTab("Charts", createChartsPanel());
        tabbedPane.addTab("Configuration", createConfigurationPanel());
        tabbedPane.addTab("Server Management", createServerManagementPanel());
        
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        add(mainPanel);

        // Initialize time series for charts
        initializeTimeSeries();

        // Start periodic updates
        startPeriodicUpdates();

        // Pack and center on screen
        pack();
        setLocationRelativeTo(null);

        // Setup theme
        setupTheme();

        // Initialize clients with proper error handling
        initializeClients(host, envPort, trafficPort, transportPort);

        // Start status checking
        startStatusChecking();
    }

    private void adjustComponentsForMaximized() {
        // Adjust font sizes and component sizes for maximized state
        updateFontSizes(1.2f);  // Increase fonts by 20%
        revalidate();
    }

    private void adjustComponentsForRestored() {
        // Reset font sizes and component sizes
        updateFontSizes(1.0f);
        revalidate();
    }

    private void adjustLayoutForNewSize() {
        Dimension size = getSize();
        if (size.width < 1000) {
            // Switch to vertical layout for smaller widths
            if (tabbedPane.getTabCount() > 0 && tabbedPane.getSelectedComponent() instanceof JPanel) {
                JPanel selectedPanel = (JPanel) tabbedPane.getSelectedComponent();
                if (selectedPanel.getLayout() instanceof GridLayout) {
                    GridLayout layout = (GridLayout) selectedPanel.getLayout();
                    layout.setRows(4);
                    layout.setColumns(1);
                }
            }
        } else {
            // Switch to grid layout for larger widths
            if (tabbedPane.getTabCount() > 0 && tabbedPane.getSelectedComponent() instanceof JPanel) {
                JPanel selectedPanel = (JPanel) tabbedPane.getSelectedComponent();
                if (selectedPanel.getLayout() instanceof GridLayout) {
                    GridLayout layout = (GridLayout) selectedPanel.getLayout();
                    layout.setRows(2);
                    layout.setColumns(2);
                }
            }
        }
        revalidate();
    }

    private void updateFontSizes(float scaleFactor) {
        UIDefaults defaults = UIManager.getDefaults();
        Enumeration<Object> keys = defaults.keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = defaults.get(key);
            if (value instanceof Font) {
                Font font = (Font) value;
                int newSize = Math.round(font.getSize() * scaleFactor);
                defaults.put(key, font.deriveFont((float) newSize));
            }
        }
    }

    private JPanel createDashboardPanel() {
        JPanel dashboardPanel = new JPanel(new BorderLayout(10, 10));
        dashboardPanel.setBackground(Color.WHITE);
        dashboardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create output area with custom styling
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(REGULAR_FONT);
        outputArea.setMargin(new Insets(5, 5, 5, 5));
        outputArea.setBackground(new Color(248, 249, 250));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(222, 226, 230)));
        dashboardPanel.add(scrollPane, BorderLayout.CENTER);

        // Create control panel with modern styling
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBackground(Color.WHITE);
        controlPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(222, 226, 230)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Location selection with custom styling
        JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        locationPanel.setBackground(Color.WHITE);
        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setFont(REGULAR_FONT);
        String[] locations = {"Hyde Park", "Oxford Street", "Oxford Circus", "London Bridge", "Trafalgar Square"};
        locationComboBox = new JComboBox<>(locations);
        locationComboBox.setFont(REGULAR_FONT);
        locationPanel.add(locationLabel);
        locationPanel.add(locationComboBox);

        // Create action buttons with modern styling
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        buttonPanel.setBackground(Color.WHITE);

        JButton envButton = createStyledButton("Get Environment Status", WARNING_COLOR);
        JButton trafficButton = createStyledButton("Get Traffic Status", PRIMARY_COLOR);
        JButton transportButton = createStyledButton("Get Transport Status", SUCCESS_COLOR);

        envButton.addActionListener(e -> getEnvironmentStatus());
        trafficButton.addActionListener(e -> getTrafficStatus());
        transportButton.addActionListener(e -> getTransportStatus());

        buttonPanel.add(envButton);
        buttonPanel.add(trafficButton);
        buttonPanel.add(transportButton);

        controlPanel.add(locationPanel);
        controlPanel.add(Box.createVerticalStrut(10));
        controlPanel.add(buttonPanel);

        dashboardPanel.add(controlPanel, BorderLayout.NORTH);

        return dashboardPanel;
    }

    private JPanel createServiceCard(String serviceName, Color color) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(PANEL_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(226, 232, 240)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        // Service title
        JLabel titleLabel = new JLabel(serviceName);
        titleLabel.setFont(HEADER_FONT);
        titleLabel.setForeground(TEXT_PRIMARY);

        // Status indicator
        JLabel statusLabel = new JLabel("Stopped");
        statusLabel.setFont(REGULAR_FONT);
        statusLabel.setForeground(DANGER_COLOR);
        serviceStatusLabels.put(serviceName, statusLabel);

        // Control buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        buttonPanel.setBackground(PANEL_BG);
        
        JButton startButton = createStyledButton("Start", SUCCESS_COLOR);
        JButton stopButton = createStyledButton("Stop", DANGER_COLOR);
        stopButton.setEnabled(false);

        startButton.addActionListener(e -> {
            startService(serviceName);
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
        });

        stopButton.addActionListener(e -> {
            stopService(serviceName);
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        });

        startButtons.put(serviceName, startButton);
        stopButtons.put(serviceName, stopButton);

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);

        // Assemble card
        JPanel infoPanel = new JPanel(new BorderLayout(5, 5));
        infoPanel.setBackground(PANEL_BG);
        infoPanel.add(titleLabel, BorderLayout.NORTH);
        infoPanel.add(statusLabel, BorderLayout.CENTER);

        card.add(infoPanel, BorderLayout.CENTER);
        card.add(buttonPanel, BorderLayout.SOUTH);

        return card;
    }

    private int getServicePort(String serviceName) {
        switch (serviceName) {
            case "Environment Service": return 50051;
            case "Traffic Service": return 50052;
            case "Transport Service": return 50053;
            default: return 50051;
        }
    }

    // Modern scroll bar UI
    private class ModernScrollBarUI extends BasicScrollBarUI {
        private final Color THUMB_COLOR = SECONDARY_COLOR;
        private final Color TRACK_COLOR = BG_COLOR;

        @Override
        protected void configureScrollBarColors() {
            thumbColor = THUMB_COLOR;
            trackColor = TRACK_COLOR;
        }

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createZeroButton();
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createZeroButton();
        }

        private JButton createZeroButton() {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(0, 0));
            return button;
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(THUMB_COLOR);
            g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10);
            g2.dispose();
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(TRACK_COLOR);
            g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
            g2.dispose();
        }
    }

    // Update createStyledButton method
    private JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(REGULAR_FONT);
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(backgroundColor.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(backgroundColor);
            }
        });

        return button;
    }

    private JLabel createStatusIndicator(String text, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(REGULAR_FONT);
        label.setForeground(color);
        label.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        label.setOpaque(true);
        label.setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), 30));
        return label;
    }

    private JPanel createChartsPanel() {
        JPanel chartsPanel = new JPanel(new GridLayout(2, 2, 10, 10));  // Add gaps between charts
        chartsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create charts with improved styling
        JFreeChart airQualityChart = createChart("Air Quality", "Air Quality Index");
        JFreeChart trafficChart = createChart("Traffic Flow", "Vehicles per Minute");
        JFreeChart transportChart = createChart("Transport Status", "Service Status (%)");
        JFreeChart noiseChart = createChart("Noise Level", "Decibels");

        // Create chart panels with enhanced features
        ChartPanel airQualityChartPanel = createEnhancedChartPanel(airQualityChart);
        ChartPanel trafficChartPanel = createEnhancedChartPanel(trafficChart);
        ChartPanel transportChartPanel = createEnhancedChartPanel(transportChart);
        ChartPanel noiseChartPanel = createEnhancedChartPanel(noiseChart);

        // Add charts to panel
        chartsPanel.add(airQualityChartPanel);
        chartsPanel.add(trafficChartPanel);
        chartsPanel.add(transportChartPanel);
        chartsPanel.add(noiseChartPanel);

        return chartsPanel;
    }

    private ChartPanel createEnhancedChartPanel(JFreeChart chart) {
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setPopupMenu(createChartPopupMenu(chartPanel));
        chartPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(5, 5, 5, 5),
            BorderFactory.createLineBorder(Color.LIGHT_GRAY)
        ));
        return chartPanel;
    }

    private JPopupMenu createChartPopupMenu(ChartPanel chartPanel) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem zoomIn = new JMenuItem("Zoom In");
        JMenuItem zoomOut = new JMenuItem("Zoom Out");
        JMenuItem resetZoom = new JMenuItem("Reset Zoom");
        JMenuItem saveAs = new JMenuItem("Save As...");

        zoomIn.addActionListener(e -> chartPanel.zoomInBoth(0.5, 0.5));
        zoomOut.addActionListener(e -> chartPanel.zoomOutBoth(0.5, 0.5));
        resetZoom.addActionListener(e -> chartPanel.restoreAutoBounds());
        saveAs.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    ChartUtils.saveChartAsPNG(
                        fileChooser.getSelectedFile(),
                        chartPanel.getChart(),
                        chartPanel.getWidth(),
                        chartPanel.getHeight()
                    );
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this,
                        "Error saving chart: " + ex.getMessage(),
                        "Save Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        popupMenu.add(zoomIn);
        popupMenu.add(zoomOut);
        popupMenu.add(resetZoom);
        popupMenu.addSeparator();
        popupMenu.add(saveAs);

        return popupMenu;
    }

    private JPanel createConfigurationPanel() {
        JPanel configPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        configPanel.setBorder(new TitledBorder("Configuration Settings"));

        // Update frequency
        SpinnerNumberModel frequencyModel = new SpinnerNumberModel(30, 5, 300, 5);
        updateFrequencySpinner = new JSpinner(frequencyModel);
        updateFrequencySpinner.addChangeListener(e -> {
            int newFrequency = (int) updateFrequencySpinner.getValue();
            scheduler.shutdown();
            scheduler.scheduleAtFixedRate(this::updateAllServices, 0, newFrequency, TimeUnit.SECONDS);
        });

        configPanel.add(new JLabel("Update Frequency (seconds):"));
        configPanel.add(updateFrequencySpinner);

        // Historical data retention
        JSpinner historySpinner = new JSpinner(new SpinnerNumberModel(24, 1, 168, 1));
        configPanel.add(new JLabel("Historical Data Retention (hours):"));
        configPanel.add(historySpinner);

        // Save configuration button
        JButton saveButton = new JButton("Save Configuration");
        saveButton.addActionListener(e -> saveConfiguration());
        configPanel.add(saveButton);

        return configPanel;
    }

    private JPanel createServerManagementPanel() {
        JPanel serverPanel = new JPanel(new BorderLayout(10, 10));
        serverPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create status panel
        JPanel statusPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        // Environment Server
        JLabel envStatus = new JLabel("Environment Server: Stopped");
        envStatus.setForeground(DANGER_COLOR);
        JButton startEnvServer = createStyledButton("Start Environment Server", SUCCESS_COLOR);
        JButton stopEnvServer = createStyledButton("Stop Environment Server", DANGER_COLOR);
        stopEnvServer.setEnabled(false);
        
        startEnvServer.addActionListener(e -> {
            appendOutput("Attempting to start Environment Server...");
            startServer("Environment Service", 50051);
        });
        stopEnvServer.addActionListener(e -> {
            appendOutput("Attempting to stop Environment Server...");
            stopServer("Environment Service");
        });
        
        statusPanel.add(envStatus);
        statusPanel.add(startEnvServer);
        statusPanel.add(stopEnvServer);

        // Traffic Server
        JLabel trafficStatus = new JLabel("Traffic Server: Stopped");
        trafficStatus.setForeground(DANGER_COLOR);
        JButton startTrafficServer = createStyledButton("Start Traffic Server", SUCCESS_COLOR);
        JButton stopTrafficServer = createStyledButton("Stop Traffic Server", DANGER_COLOR);
        stopTrafficServer.setEnabled(false);
        
        startTrafficServer.addActionListener(e -> {
            appendOutput("Attempting to start Traffic Server...");
            startServer("Traffic Service", 50052);
        });
        stopTrafficServer.addActionListener(e -> {
            appendOutput("Attempting to stop Traffic Server...");
            stopServer("Traffic Service");
        });
        
        statusPanel.add(trafficStatus);
        statusPanel.add(startTrafficServer);
        statusPanel.add(stopTrafficServer);

        // Transport Server
        JLabel transportStatus = new JLabel("Transport Server: Stopped");
        transportStatus.setForeground(DANGER_COLOR);
        JButton startTransportServer = createStyledButton("Start Transport Server", SUCCESS_COLOR);
        JButton stopTransportServer = createStyledButton("Stop Transport Server", DANGER_COLOR);
        stopTransportServer.setEnabled(false);
        
        startTransportServer.addActionListener(e -> {
            appendOutput("Attempting to start Transport Server...");
            startServer("Transport Service", 50053);
        });
        stopTransportServer.addActionListener(e -> {
            appendOutput("Attempting to stop Transport Server...");
            stopServer("Transport Service");
        });
        
        statusPanel.add(transportStatus);
        statusPanel.add(startTransportServer);
        statusPanel.add(stopTransportServer);

        // Store references for status updates
        serviceStatusLabels.put("Environment Service", envStatus);
        serviceStatusLabels.put("Traffic Service", trafficStatus);
        serviceStatusLabels.put("Transport Service", transportStatus);

        startButtons.put("Environment Service", startEnvServer);
        startButtons.put("Traffic Service", startTrafficServer);
        startButtons.put("Transport Service", startTransportServer);

        stopButtons.put("Environment Service", stopEnvServer);
        stopButtons.put("Traffic Service", stopTrafficServer);
        stopButtons.put("Transport Service", stopTransportServer);

        // Create server log panel
        JPanel logPanel = new JPanel(new BorderLayout());
        logPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Server Logs"));
        JTextArea serverLogArea = new JTextArea();
        serverLogArea.setEditable(false);
        serverLogArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane logScrollPane = new JScrollPane(serverLogArea);
        logPanel.add(logScrollPane, BorderLayout.CENTER);

        // Create control buttons panel
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JButton clearLogsButton = createStyledButton("Clear Logs", SECONDARY_COLOR);
        JButton refreshStatusButton = createStyledButton("Refresh Status", PRIMARY_COLOR);
        JButton startAllButton = createStyledButton("Start All Servers", SUCCESS_COLOR);
        JButton stopAllButton = createStyledButton("Stop All Servers", DANGER_COLOR);

        clearLogsButton.addActionListener(e -> {
            serverLogArea.setText("");
            appendOutput("Logs cleared");
        });

        refreshStatusButton.addActionListener(e -> {
            appendOutput("Refreshing server status...");
            updateServerStatus(envStatus, trafficStatus, transportStatus);
        });

        startAllButton.addActionListener(e -> {
            appendOutput("Starting all servers...");
            startServer("Environment Service", 50051);
            startServer("Traffic Service", 50052);
            startServer("Transport Service", 50053);
        });

        stopAllButton.addActionListener(e -> {
            appendOutput("Stopping all servers...");
            stopServer("Environment Service");
            stopServer("Traffic Service");
            stopServer("Transport Service");
        });

        controlPanel.add(clearLogsButton);
        controlPanel.add(refreshStatusButton);
        controlPanel.add(startAllButton);
        controlPanel.add(stopAllButton);

        // Add all panels to main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.add(statusPanel, BorderLayout.NORTH);
        mainPanel.add(logPanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        serverPanel.add(mainPanel);

        return serverPanel;
    }

    private void updateServerStatus(JLabel envStatus, JLabel trafficStatus, JLabel transportStatus) {
        // Check if servers are running and update status labels
        boolean envRunning = isServerRunning("Environment Service");
        boolean trafficRunning = isServerRunning("Traffic Service");
        boolean transportRunning = isServerRunning("Transport Service");

        updateStatusLabel(envStatus, envRunning);
        updateStatusLabel(trafficStatus, trafficRunning);
        updateStatusLabel(transportStatus, transportRunning);
    }

    private boolean isServerRunning(String serviceName) {
        for (Map.Entry<String, Server> entry : grpcServers.entrySet()) {
            if (entry.getKey().equals(serviceName) && entry.getValue().isTerminated()) {
                return false;
            }
        }
        return true;
    }

    private void updateStatusLabel(JLabel label, boolean isRunning) {
        String serviceName = label.getText().split(":")[0];
        if (isRunning) {
            label.setText(serviceName + ": Running");
            label.setForeground(SUCCESS_COLOR);
        } else {
            label.setText(serviceName + ": Stopped");
            label.setForeground(DANGER_COLOR);
        }
    }

    private void startServer(String serviceName, int port) {
        try {
            // First check if port is already in use
            if (isPortInUse(port)) {
                throw new IOException("Port " + port + " is already in use");
            }

            Server server = null;
            switch (serviceName) {
                case "Environment Service":
                    server = ServerBuilder.forPort(port)
                            .addService(new EnvironmentServiceImpl(port))
                            .build()
                            .start();
                    break;
                case "Traffic Service":
                    server = ServerBuilder.forPort(port)
                            .addService(new TrafficServiceImpl(port))
                            .build()
                            .start();
                    break;
                case "Transport Service":
                    server = ServerBuilder.forPort(port)
                            .addService(new TransportServiceImpl(port))
                            .build()
                            .start();
                    break;
            }

            if (server != null) {
                final Server finalServer = server;
                grpcServers.put(serviceName, finalServer);
                
                // Add shutdown hook
                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    try {
                        finalServer.shutdown();
                        if (!finalServer.awaitTermination(5, TimeUnit.SECONDS)) {
                            finalServer.shutdownNow();
                        }
                    } catch (Exception e) {
                        finalServer.shutdownNow();
                    }
                }));

                appendOutput(serviceName + " started successfully on port " + port);
                updateServiceStatus(serviceName, "Running");
                serverStatus.put(serviceName, true);
                startButtons.get(serviceName).setEnabled(false);
                stopButtons.get(serviceName).setEnabled(true);
            }
        } catch (Exception e) {
            String errorMsg = "Error starting " + serviceName + ": " + e.getMessage();
            appendOutput(errorMsg);
            updateServiceStatus(serviceName, "Error: " + e.getMessage());
            serverStatus.put(serviceName, false);
            
            // Reset the server entry if it exists
            if (grpcServers.containsKey(serviceName)) {
                try {
                    grpcServers.get(serviceName).shutdown();
                } catch (Exception ex) {
                    // Ignore shutdown errors
                }
                grpcServers.remove(serviceName);
            }
        }
    }

    private boolean isPortInUse(int port) {
        try (java.net.ServerSocket serverSocket = new java.net.ServerSocket(port)) {
            // Port is available
            return false;
        } catch (IOException e) {
            // Port is in use
            return true;
        }
    }

    private void stopServer(String serviceName) {
        try {
            io.grpc.Server server = grpcServers.get(serviceName);
            if (server != null) {
                // Update status before attempting shutdown
                updateServiceStatus(serviceName, "Stopping...");
                
                // Attempt graceful shutdown first
                server.shutdown();
                boolean terminated = server.awaitTermination(5, TimeUnit.SECONDS);
                
                if (!terminated) {
                    appendOutput(serviceName + " did not terminate gracefully, forcing shutdown...");
                    server.shutdownNow();
                    // Wait one more second for forced shutdown
                    terminated = server.awaitTermination(1, TimeUnit.SECONDS);
                }

                // Cleanup resources
                grpcServers.remove(serviceName);
                resetClient(serviceName);
                
                // Cancel any existing update timers for this service
                if (serviceUpdateTimers.containsKey(serviceName)) {
                    serviceUpdateTimers.get(serviceName).stop();
                    serviceUpdateTimers.remove(serviceName);
                }
                
                // Update status and UI
                String status = terminated ? "Stopped successfully" : "Forced shutdown";
                appendOutput(serviceName + " " + status);
                updateServiceStatus(serviceName, "Stopped");
                serverStatus.put(serviceName, false);
                serviceRunningStatus.put(serviceName, false);
                
                // Update buttons
                SwingUtilities.invokeLater(() -> {
                    startButtons.get(serviceName).setEnabled(true);
                    stopButtons.get(serviceName).setEnabled(false);
                });
            } else {
                appendOutput(serviceName + " was not running");
                updateServiceStatus(serviceName, "Not running");
            }
        } catch (Exception e) {
            String errorMsg = "Error stopping " + serviceName + ": " + e.getMessage();
            appendOutput(errorMsg);
            updateServiceStatus(serviceName, "Error: " + e.getMessage());
            
            // Emergency cleanup
            grpcServers.remove(serviceName);
            resetClient(serviceName);
            serviceRunningStatus.put(serviceName, false);
            serverStatus.put(serviceName, false);
            
            // Update buttons on EDT
            SwingUtilities.invokeLater(() -> {
                startButtons.get(serviceName).setEnabled(true);
                stopButtons.get(serviceName).setEnabled(false);
            });
            
            // Log the full stack trace
            e.printStackTrace();
        }
    }

    private JFreeChart createChart(String title, String yAxisLabel) {
        TimeSeries series = new TimeSeries(title);
        timeSeriesMap.put(title, series);
        TimeSeriesCollection dataset = new TimeSeriesCollection(series);
        
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            title,
            "Time",
            yAxisLabel,
            dataset,
            true,
            true,
            false
        );

        // Customize chart appearance
        chart.setBackgroundPaint(Color.WHITE);
        chart.getPlot().setBackgroundPaint(new Color(248, 249, 250));
        chart.getPlot().setOutlinePaint(new Color(222, 226, 230));
        chart.getTitle().setFont(TITLE_FONT);
        
        return chart;
    }

    private void initializeTimeSeries() {
        timeSeriesMap.put("Air Quality", new TimeSeries("Air Quality"));
        timeSeriesMap.put("Traffic Flow", new TimeSeries("Traffic Flow"));
        timeSeriesMap.put("Transport Status", new TimeSeries("Transport Status"));
        timeSeriesMap.put("Noise Level", new TimeSeries("Noise Level"));
    }

    private void updateAllServices() {
        String location = (String) locationComboBox.getSelectedItem();
        try {
            getEnvironmentStatus();
            getTrafficStatus();
            getTransportStatus();
            updateCharts();
            appendOutput("\n--- End of Update Cycle ---\n");
        } catch (Exception e) {
            appendOutput("Error in periodic update: " + e.getMessage());
        }
    }

    private void updateCharts() {
        Second now = new Second(new Date());
        // Update charts with new data
        timeSeriesMap.get("Air Quality").add(now, Math.random() * 100);
        timeSeriesMap.get("Traffic Flow").add(now, Math.random() * 1000);
        timeSeriesMap.get("Transport Status").add(now, Math.random() * 100);
        timeSeriesMap.get("Noise Level").add(now, Math.random() * 100);
    }

    private void saveConfiguration() {
        int frequency = (int) updateFrequencySpinner.getValue();
        appendOutput("Configuration saved: Update frequency = " + frequency + " seconds");
    }

    private void getEnvironmentStatus() {
        try {
            String location = (String) locationComboBox.getSelectedItem();
            if (location == null || location.isEmpty()) {
                appendOutput("Please select a location first.");
                return;
            }

            if (environmentClient == null) {
                appendOutput("Environment service is not available. Please start the service first.");
                return;
            }

            EnvironmentResponse response = environmentClient.getEnvironmentStatus(location);
            if (response.getSuccess()) {
                EnvironmentData data = response.getData();
                appendOutput(String.format(
                    "Environment Status for %s:\n" +
                    "Air Quality: %d\n" +
                    "Noise Level: %d\n" +
                    "Temperature: %.1f°C\n" +
                    "Humidity: %d%%",
                    location,
                    data.getAirQuality(),
                    data.getNoiseLevel(),
                    data.getTemperature(),
                    data.getHumidity()
                ));
                updateChart("Air Quality", data.getAirQuality());
                updateChart("Noise Level", data.getNoiseLevel());
            } else {
                appendOutput("Error getting environment status: " + response.getMessage());
            }
        } catch (Exception e) {
            appendOutput("Error getting environment status: " + e.getMessage());
            appendOutput("Please ensure the Environment service is running.");
        }
    }

    private void getTrafficStatus() {
        try {
            String location = (String) locationComboBox.getSelectedItem();
            if (location == null || location.isEmpty()) {
                appendOutput("Please select a location first.");
                return;
            }

            if (trafficClient == null) {
                appendOutput("Traffic service is not available. Please start the service first.");
                return;
            }

            TrafficResponse response = trafficClient.getTrafficStatus(location);
            if (response.getSuccess()) {
                TrafficData data = response.getData();
                appendOutput(String.format(
                    "Traffic Status for %s:\n" +
                    "Congestion Level: %d%%\n" +
                    "Average Speed: %d km/h\n" +
                    "Vehicle Count: %d\n" +
                    "Incidents: %d",
                    location,
                    data.getCongestionLevel(),
                    data.getAverageSpeed(),
                    data.getVehicleCount(),
                    data.getIncidentsCount()
                ));
                updateChart("Traffic Flow", data.getVehicleCount());
            } else {
                appendOutput("Error getting traffic status: " + response.getMessage());
            }
        } catch (Exception e) {
            appendOutput("Error getting traffic status: " + e.getMessage());
            appendOutput("Please ensure the Traffic service is running.");
        }
    }

    private void getTransportStatus() {
        try {
            String location = (String) locationComboBox.getSelectedItem();
            if (location == null || location.isEmpty()) {
                appendOutput("Please select a location first.");
                return;
            }

            if (transportClient == null) {
                appendOutput("Transport service is not available. Please start the service first.");
                return;
            }

            TransportResponse response = transportClient.getTransportStatus(location, "tube");
            if (response.getSuccess()) {
                TransportData data = response.getData();
                appendOutput(String.format(
                    "Transport Status for %s:\n" +
                    "Service Status: %d%%\n" +
                    "Delay Minutes: %d\n" +
                    "Next Arrival: %s\n" +
                    "Issues: %d",
                    location,
                    data.getServiceStatus(),
                    data.getDelayMinutes(),
                    data.getNextArrival(),
                    data.getIssuesCount()
                ));
                updateChart("Transport Status", data.getServiceStatus());
            } else {
                appendOutput("Error getting transport status: " + response.getMessage());
            }
        } catch (Exception e) {
            appendOutput("Error getting transport status: " + e.getMessage());
            appendOutput("Please ensure the Transport service is running.");
        }
    }

    private void appendOutput(String text) {
        SwingUtilities.invokeLater(() -> {
            outputArea.append(text + "\n");
            outputArea.setCaretPosition(outputArea.getDocument().getLength());
            
            // Fix Timer ambiguity by using javax.swing.Timer
            javax.swing.Timer timer = new javax.swing.Timer(50, new ActionListener() {
                float alpha = 0.0f;
                @Override
                public void actionPerformed(ActionEvent e) {
                    alpha += 0.1f;
                    if (alpha >= 1.0f) {
                        alpha = 1.0f;
                        ((javax.swing.Timer)e.getSource()).stop();
                    }
                    outputArea.setForeground(new Color(0, 0, 0, alpha));
                }
            });
            timer.start();
        });
    }

    @Override
    public void dispose() {
        try {
            // Shutdown all servers
            for (Map.Entry<String, Server> entry : grpcServers.entrySet()) {
                try {
                    Server server = entry.getValue();
                    server.shutdown();
                    if (!server.awaitTermination(5, TimeUnit.SECONDS)) {
                        server.shutdownNow();
                    }
                } catch (Exception e) {
                    appendOutput("Error shutting down " + entry.getKey() + ": " + e.getMessage());
                }
            }
            
            // Shutdown all clients
            try {
                if (environmentClient != null) environmentClient.shutdown();
                if (trafficClient != null) trafficClient.shutdown();
                if (transportClient != null) transportClient.shutdown();
            } catch (Exception e) {
                appendOutput("Error shutting down clients: " + e.getMessage());
            }
            
            // Shutdown status checker
            statusChecker.shutdown();
            try {
                if (!statusChecker.awaitTermination(5, TimeUnit.SECONDS)) {
                    statusChecker.shutdownNow();
                }
            } catch (InterruptedException e) {
                statusChecker.shutdownNow();
            }
        } catch (Exception e) {
            appendOutput("Error during shutdown: " + e.getMessage());
        }
        
        super.dispose();
    }

    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "localhost";
        int envPort = args.length > 1 ? Integer.parseInt(args[1]) : 50051;
        int trafficPort = args.length > 2 ? Integer.parseInt(args[2]) : 50052;
        int transportPort = args.length > 3 ? Integer.parseInt(args[3]) : 50053;

        SwingUtilities.invokeLater(() -> {
            SmartCityGUI gui = new SmartCityGUI(host, envPort, trafficPort, transportPort);
            gui.setVisible(true);
        });
    }

    private void setupTheme() {
        try {
            // Set system look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
            // Customize UI components
            UIManager.put("Panel.background", Color.WHITE);
            UIManager.put("TabbedPane.background", Color.WHITE);
            UIManager.put("TabbedPane.selected", new Color(240, 240, 240));
            UIManager.put("TabbedPane.font", REGULAR_FONT);
            UIManager.put("Button.font", REGULAR_FONT);
            UIManager.put("Label.font", REGULAR_FONT);
            UIManager.put("TextField.font", REGULAR_FONT);
            UIManager.put("TextArea.font", REGULAR_FONT);
            UIManager.put("ComboBox.font", REGULAR_FONT);
            UIManager.put("Button.background", PRIMARY_COLOR);
            UIManager.put("Button.foreground", Color.WHITE);
            UIManager.put("Button.select", PRIMARY_COLOR.darker());
            UIManager.put("Button.focus", new Color(0, 0, 0, 0));
            
            // Update all components
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startPeriodicUpdates() {
        scheduler.scheduleAtFixedRate(this::updateAllServices, 0, 30, TimeUnit.SECONDS);
    }

    private void startService(String serviceName) {
        try {
            switch (serviceName) {
                case "Environment Service":
                    if (environmentClient == null) {
                        throw new IllegalStateException("Environment client not initialized");
                    }
                    break;
                case "Traffic Service":
                    if (trafficClient == null) {
                        throw new IllegalStateException("Traffic client not initialized");
                    }
                    break;
                case "Transport Service":
                    if (transportClient == null) {
                        throw new IllegalStateException("Transport client not initialized");
                    }
                    break;
            }
            serviceRunningStatus.put(serviceName, true);
            startServiceUpdateTimer(serviceName);
            updateServiceStatus(serviceName, "Running");
        } catch (Exception e) {
            appendOutput("Error starting " + serviceName + ": " + e.getMessage());
            updateServiceStatus(serviceName, "Error: " + e.getMessage());
            serviceRunningStatus.put(serviceName, false);
        }
    }

    private void stopService(String serviceName) {
        try {
            switch (serviceName) {
                case "Environment Service":
                    environmentClient.shutdown();
                    break;
                case "Traffic Service":
                    trafficClient.shutdown();
                    break;
                case "Transport Service":
                    transportClient.shutdown();
                    break;
            }
            serviceRunningStatus.put(serviceName, false);
            stopServiceUpdateTimer(serviceName);
            updateServiceStatus(serviceName, "Stopped");
        } catch (Exception e) {
            appendOutput("Error stopping " + serviceName + ": " + e.getMessage());
            updateServiceStatus(serviceName, "Error: " + e.getMessage());
        }
    }

    private void startServiceUpdateTimer(String serviceName) {
        javax.swing.Timer timer = new javax.swing.Timer(5000, e -> updateServiceData(serviceName));
        timer.start();
        serviceUpdateTimers.put(serviceName, timer);
    }

    private void stopServiceUpdateTimer(String serviceName) {
        javax.swing.Timer timer = serviceUpdateTimers.remove(serviceName);
        if (timer != null) {
            timer.stop();
        }
    }

    private void updateServiceData(String serviceName) {
        if (!serviceRunningStatus.get(serviceName)) {
            return;
        }

        try {
            String location = (String) locationComboBox.getSelectedItem();
            if (location == null || location.isEmpty()) {
                return;
            }

            switch (serviceName) {
                case "Environment Service":
                    if (environmentClient != null) {
                        EnvironmentResponse envResponse = environmentClient.getEnvironmentStatus(location);
                        if (envResponse.getSuccess()) {
                            EnvironmentData envData = envResponse.getData();
                            updateServiceStatus(serviceName, String.format(
                                "Air Quality: %d | Temp: %.1f°C | Humidity: %d%%",
                                envData.getAirQuality(),
                                envData.getTemperature(),
                                envData.getHumidity()
                            ));
                            updateChart("Air Quality", envData.getAirQuality());
                            updateChart("Noise Level", envData.getNoiseLevel());
                        }
                    }
                    break;
                case "Traffic Service":
                    if (trafficClient != null) {
                        TrafficResponse trafficResponse = trafficClient.getTrafficStatus(location);
                        if (trafficResponse.getSuccess()) {
                            TrafficData trafficData = trafficResponse.getData();
                            updateServiceStatus(serviceName, String.format(
                                "Congestion: %d%% | Speed: %d km/h | Vehicles: %d",
                                trafficData.getCongestionLevel(),
                                trafficData.getAverageSpeed(),
                                trafficData.getVehicleCount()
                            ));
                            updateChart("Traffic Flow", trafficData.getVehicleCount());
                        }
                    }
                    break;
                case "Transport Service":
                    if (transportClient != null) {
                        TransportResponse transportResponse = transportClient.getTransportStatus(location, "tube");
                        if (transportResponse.getSuccess()) {
                            TransportData transportData = transportResponse.getData();
                            updateServiceStatus(serviceName, String.format(
                                "Status: %d%% | Delay: %d min | Next: %s",
                                transportData.getServiceStatus(),
                                transportData.getDelayMinutes(),
                                transportData.getNextArrival()
                            ));
                            updateChart("Transport Status", transportData.getServiceStatus());
                        }
                    }
                    break;
            }
        } catch (Exception e) {
            appendOutput("Error updating " + serviceName + " data: " + e.getMessage());
            updateServiceStatus(serviceName, "Error: " + e.getMessage());
        }
    }

    private void updateServiceStatus(String serviceName, String status) {
        serviceLastStatus.put(serviceName, status);
        JLabel statusLabel = serviceStatusLabels.get(serviceName);
        if (statusLabel != null) {
            statusLabel.setText(serviceName + ": " + status);
            statusLabel.setForeground(status.contains("Error") ? DANGER_COLOR : 
                                   status.contains("Running") ? SUCCESS_COLOR : 
                                   status.contains("Stopped") ? DANGER_COLOR : Color.BLACK);
        }
    }

    private void updateChart(String chartName, double value) {
        TimeSeries series = timeSeriesMap.get(chartName);
        if (series != null) {
            series.add(new Second(new Date()), value);
        }
    }

    private void initializeClients(String host, int envPort, int trafficPort, int transportPort) {
        try {
            // Initialize Environment Client
            try {
                this.environmentClient = new EnvironmentClient(host, envPort);
                appendOutput("Environment client initialized successfully");
            } catch (Exception e) {
                appendOutput("Failed to initialize Environment client: " + e.getMessage());
                this.environmentClient = null;
            }

            // Initialize Traffic Client
            try {
                this.trafficClient = new TrafficClient(host, trafficPort);
                appendOutput("Traffic client initialized successfully");
            } catch (Exception e) {
                appendOutput("Failed to initialize Traffic client: " + e.getMessage());
                this.trafficClient = null;
            }

            // Initialize Transport Client
            try {
                this.transportClient = new TransportClient(host, transportPort);
                appendOutput("Transport client initialized successfully");
            } catch (Exception e) {
                appendOutput("Failed to initialize Transport client: " + e.getMessage());
                this.transportClient = null;
            }
        } catch (Exception e) {
            appendOutput("Error during client initialization: " + e.getMessage());
        }
    }

    private void startStatusChecking() {
        statusChecker.scheduleAtFixedRate(() -> {
            try {
                checkServiceStatus("Environment Service", 50051);
                checkServiceStatus("Traffic Service", 50052);
                checkServiceStatus("Transport Service", 50053);
            } catch (Exception e) {
                appendOutput("Error checking service status: " + e.getMessage());
            }
        }, 0, 5, TimeUnit.SECONDS);
    }
    
    private void checkServiceStatus(String serviceName, int port) {
        try {
            switch (serviceName) {
                case "Environment Service":
                    if (environmentClient == null) {
                        environmentClient = new EnvironmentClient("localhost", port);
                        environmentClient.getEnvironmentStatus("test");
                        updateServiceStatus(serviceName, "Running");
                        serverStatus.put(serviceName, true);
                    }
                    break;
                case "Traffic Service":
                    if (trafficClient == null) {
                        trafficClient = new TrafficClient("localhost", port);
                        trafficClient.getTrafficStatus("test");
                        updateServiceStatus(serviceName, "Running");
                        serverStatus.put(serviceName, true);
                    }
                    break;
                case "Transport Service":
                    if (transportClient == null) {
                        transportClient = new TransportClient("localhost", port);
                        transportClient.getTransportStatus("test", "tube");
                        updateServiceStatus(serviceName, "Running");
                        serverStatus.put(serviceName, true);
                    }
                    break;
            }
        } catch (Exception e) {
            updateServiceStatus(serviceName, "Stopped");
            serverStatus.put(serviceName, false);
            resetClient(serviceName);
        }
    }
    
    private void resetClient(String serviceName) {
        try {
            switch (serviceName) {
                case "Environment Service":
                    if (environmentClient != null) {
                        environmentClient.shutdown();
                        environmentClient = null;
                    }
                    break;
                case "Traffic Service":
                    if (trafficClient != null) {
                        trafficClient.shutdown();
                        trafficClient = null;
                    }
                    break;
                case "Transport Service":
                    if (transportClient != null) {
                        transportClient.shutdown();
                        transportClient = null;
                    }
                    break;
            }
        } catch (Exception e) {
            appendOutput("Error resetting client for " + serviceName + ": " + e.getMessage());
        }
    }
} 