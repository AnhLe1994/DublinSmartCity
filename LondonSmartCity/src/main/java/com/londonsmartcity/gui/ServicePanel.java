package com.londonsmartcity.gui;

import com.londonsmartcity.discovery.ServiceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class ServicePanel extends JPanel {
    protected static final Logger logger = LoggerFactory.getLogger(ServicePanel.class);
    protected final JList<ServiceInfo> serviceList;
    protected final DefaultListModel<ServiceInfo> listModel;

    public ServicePanel() {
        setLayout(new BorderLayout());
        
        // Initialize service list
        listModel = new DefaultListModel<>();
        serviceList = new JList<>(listModel);
        serviceList.setCellRenderer(new ServiceListRenderer());
        
        // Add components
        add(new JScrollPane(serviceList), BorderLayout.CENTER);
    }

    public abstract String getServiceType();

    public void updateServices(List<ServiceInfo> services) {
        listModel.clear();
        for (ServiceInfo service : services) {
            listModel.addElement(service);
        }
    }

    private static class ServiceListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                    boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof ServiceInfo) {
                ServiceInfo service = (ServiceInfo) value;
                setText(String.format("%s - %s:%d", 
                    service.getName(), 
                    service.getHost(), 
                    service.getPort()));
            }
            return this;
        }
    }
} 