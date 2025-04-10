package com.londonsmartcity.traffic;

import java.util.ArrayList;
import java.util.List;

public class TrafficStatus {
    private final String locationId;
    private final int congestionLevel;
    private final int averageSpeed;
    private final String status;
    private final List<String> activeIncidents;

    public TrafficStatus(String locationId, int congestionLevel, int averageSpeed, String status) {
        this.locationId = locationId;
        this.congestionLevel = congestionLevel;
        this.averageSpeed = averageSpeed;
        this.status = status;
        this.activeIncidents = new ArrayList<>();
    }

    public String getLocationId() {
        return locationId;
    }

    public int getCongestionLevel() {
        return congestionLevel;
    }

    public int getAverageSpeed() {
        return averageSpeed;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getActiveIncidents() {
        return activeIncidents;
    }

    public void addIncident(String incident) {
        activeIncidents.add(incident);
    }
} 