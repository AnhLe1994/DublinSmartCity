package com.londonsmartcity.environment;

import com.londonsmartcity.common.BaseService;
import com.londonsmartcity.environment.EnvironmentServiceGrpc.EnvironmentServiceImplBase;
import com.londonsmartcity.environment.EnvironmentServiceProto.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EnvironmentService extends BaseService {
    private static final Logger logger = LoggerFactory.getLogger(EnvironmentService.class);
    private static final int PORT = 50054;

    private final Server server;
    private final Map<String, EnvironmentalData> environmentalData = new HashMap<>();
    private final Map<String, AirQualityAlert> activeAlerts;
    private final ScheduledExecutorService scheduler;
    private final EnvironmentServiceImpl serviceImpl = new EnvironmentServiceImpl();

    public EnvironmentService() {
        super("ENVIRONMENT", "Environmental Monitoring Service", PORT, new HashMap<>());
        server = ServerBuilder.forPort(PORT)
                .addService(serviceImpl)
                .build();
        activeAlerts = new ConcurrentHashMap<>();
        scheduler = Executors.newSingleThreadScheduledExecutor();
        initializeSampleData();
    }

    private void initializeSampleData() {
        // Add sample environmental data
        addEnvironmentalData("LOC1", 15.0, 12.0, 8.0, 6.0, 65.0, 22.5, 60.0, 10.5, "Sunny");
        addEnvironmentalData("LOC2", 18.0, 15.0, 10.0, 7.0, 70.0, 24.0, 65.0, 12.0, "Partly Cloudy");

        // Initialize some sample air quality alerts
        addAirQualityAlert("LOC1", "Elevated PM2.5 levels detected", "HIGH");
    }

    private void addEnvironmentalData(String locationId, double pm25, double pm10, double no2, double o3,
                                    double decibels, double temperature, double humidity, double windSpeed,
                                    String weatherCondition) {
        EnvironmentalData data = EnvironmentalData.newBuilder()
            .setLocationId(locationId)
            .setPm25(pm25)
            .setPm10(pm10)
            .setNo2(no2)
            .setO3(o3)
            .setDecibels(decibels)
            .setTemperature(temperature)
            .setHumidity(humidity)
            .setWindSpeed(windSpeed)
            .setWeatherCondition(weatherCondition)
            .setTimestamp(System.currentTimeMillis())
            .build();
        environmentalData.put(locationId, data);
    }

    private void addAirQualityAlert(String locationId, String description, String severity) {
        AirQualityAlert alert = AirQualityAlert.newBuilder()
                .setLocationId(locationId)
                .setDescription(description)
                .setSeverity(severity)
                .build();
        activeAlerts.put(locationId, alert);
    }

    @Override
    public void start() {
        try {
            server.start();
            logger.info("Environment Service started on port {}", PORT);
            super.start();

            // Schedule periodic updates
            scheduler.scheduleAtFixedRate(() -> {
                try {
                    updateEnvironmentalData();
                } catch (Exception e) {
                    logger.error("Error updating environmental data: {}", e.getMessage());
                }
            }, 0, 5, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error("Failed to start environment service: {}", e.getMessage());
            throw new RuntimeException("Service startup failed", e);
        }
    }

    private void updateEnvironmentalData() {
        environmentalData.forEach((locationId, data) -> {
            EnvironmentalData updatedData = EnvironmentalData.newBuilder(data)
                .setTimestamp(System.currentTimeMillis())
                .build();
            environmentalData.put(locationId, updatedData);

            // Check for alert conditions
            checkAlertConditions(locationId, updatedData);
        });
    }

    private void checkAlertConditions(String locationId, EnvironmentalData data) {
        if (data.getPm25() > 100) {
            AirQualityAlert alert = AirQualityAlert.newBuilder()
                    .setLocationId(locationId)
                    .setDescription("Air quality index exceeded threshold: " + data.getPm25())
                    .setSeverity("HIGH")
                    .build();
            activeAlerts.put(locationId, alert);
        }
    }

    @Override
    public void stop() {
        try {
            super.stop();
            server.shutdown();
            scheduler.shutdown();
            logger.info("Environment Service stopped");
        } catch (Exception e) {
            logger.error("Error stopping environment service: {}", e.getMessage());
        }
    }

    public EnvironmentServiceImpl getServiceImpl() {
        return serviceImpl;
    }

    private class EnvironmentServiceImpl extends EnvironmentServiceImplBase {
        @Override
        public void getAirQuality(AirQualityRequest request, StreamObserver<AirQualityResponse> responseObserver) {
            String locationId = request.getLocationId();
            EnvironmentalData data = environmentalData.get(locationId);
            if (data != null) {
                AirQualityResponse response = AirQualityResponse.newBuilder()
                    .setLocationId(locationId)
                    .setPm25(data.getPm25())
                    .setPm10(data.getPm10())
                    .setNo2(data.getNo2())
                    .setO3(data.getO3())
                    .setAirQualityIndex(calculateAirQualityIndex(data))
                    .setTimestamp(data.getTimestamp())
                    .build();
                responseObserver.onNext(response);
            }
            responseObserver.onCompleted();
        }

        @Override
        public void getNoiseLevel(NoiseLevelRequest request, StreamObserver<NoiseLevelResponse> responseObserver) {
            String locationId = request.getLocationId();
            EnvironmentalData data = environmentalData.get(locationId);
            if (data != null) {
                NoiseLevelResponse response = NoiseLevelResponse.newBuilder()
                    .setLocationId(locationId)
                    .setDecibels(data.getDecibels())
                    .setNoiseLevel(calculateNoiseLevel(data.getDecibels()))
                    .setTimestamp(data.getTimestamp())
                    .build();
                responseObserver.onNext(response);
            }
            responseObserver.onCompleted();
        }

        @Override
        public void getWeather(WeatherRequest request, StreamObserver<WeatherResponse> responseObserver) {
            String locationId = request.getLocationId();
            EnvironmentalData data = environmentalData.get(locationId);
            if (data != null) {
                WeatherResponse response = WeatherResponse.newBuilder()
                    .setLocationId(locationId)
                    .setTemperature(data.getTemperature())
                    .setHumidity(data.getHumidity())
                    .setWindSpeed(data.getWindSpeed())
                    .setWeatherCondition(data.getWeatherCondition())
                    .setTimestamp(data.getTimestamp())
                    .build();
                responseObserver.onNext(response);
            }
            responseObserver.onCompleted();
        }

        @Override
        public void streamEnvironmentalData(EnvironmentalDataRequest request,
                                          StreamObserver<EnvironmentalData> responseObserver) {
            String locationId = request.getLocationId();
            EnvironmentalData data = environmentalData.get(locationId);
            if (data != null) {
                responseObserver.onNext(data);
            }
            responseObserver.onCompleted();
        }

        private String calculateAirQualityIndex(EnvironmentalData data) {
            // Simple AQI calculation based on PM2.5
            double pm25 = data.getPm25();
            if (pm25 <= 12) return "Good";
            if (pm25 <= 35) return "Moderate";
            if (pm25 <= 55) return "Unhealthy for Sensitive Groups";
            if (pm25 <= 150) return "Unhealthy";
            if (pm25 <= 250) return "Very Unhealthy";
            return "Hazardous";
        }

        private String calculateNoiseLevel(double decibels) {
            if (decibels <= 30) return "Quiet";
            if (decibels <= 50) return "Moderate";
            if (decibels <= 70) return "Loud";
            if (decibels <= 90) return "Very Loud";
            return "Extremely Loud";
        }
    }

    public static void main(String[] args) {
        final EnvironmentService service = new EnvironmentService();
        service.start();
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            service.stop();
        }));
    }
} 