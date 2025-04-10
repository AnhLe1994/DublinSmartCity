# London Smart City: Distributed Traffic Management System

## Table of Contents
1. [Project Overview](#project-overview)
2. [Application Domain](#application-domain)
3. [Services Overview](#services-overview)
4. [System Architecture](#system-architecture)
5. [Technical Implementation](#technical-implementation)
6. [Contribution to Smart City](#contribution-to-smart-city)
7. [Future Enhancements](#future-enhancements)

## Project Overview
The London Smart City project aims to develop a comprehensive distributed system for managing urban traffic in London. This system integrates multiple services to provide real-time traffic monitoring, environmental impact assessment, and public transportation management. The project focuses on creating a scalable, reliable, and efficient distributed system that can handle the complex requirements of modern urban traffic management.

## Application Domain
The application domain is urban traffic management, specifically focusing on:
- Real-time traffic monitoring and control
- Environmental impact assessment
- Public transportation management
- Emergency response coordination
- Traffic data analytics

## Services Overview

### 1. Traffic Management Service
The Traffic Management Service is responsible for monitoring and controlling traffic flow across the city. It provides real-time traffic status updates, manages traffic signals, and handles incident reporting.

**Key Functionalities:**
- Real-time traffic status monitoring
- Traffic signal timing control
- Incident reporting and management
- Traffic data streaming
- Traffic pattern analysis

**Service Definition:**
```proto
service TrafficService {
  rpc GetTrafficStatus (TrafficStatusRequest) returns (TrafficStatusResponse) {}
  rpc UpdateSignalTiming (SignalTimingRequest) returns (SignalTimingResponse) {}
  rpc ReportIncident (IncidentReport) returns (IncidentResponse) {}
  rpc StreamTrafficUpdates (TrafficUpdateRequest) returns (stream TrafficUpdate) {}
}
```

**Message Definitions:**
```proto
message TrafficStatusRequest {
  string location = 1;
}

message TrafficStatusResponse {
  string location = 1;
  int32 congestion_level = 2;
  int32 average_speed = 3;
  string status = 4;
  int64 timestamp = 5;
}

message SignalTimingRequest {
  string location = 1;
  int32 green_duration = 2;
  int32 yellow_duration = 3;
  int32 red_duration = 4;
}

message IncidentReport {
  string location = 1;
  string description = 2;
  string severity = 3;
}
```

### 2. Environmental Monitoring Service
The Environmental Monitoring Service tracks and analyzes environmental conditions affected by traffic, providing crucial data for decision-making and public health.

**Key Functionalities:**
- Air quality monitoring
- Noise level measurement
- Weather condition tracking
- Environmental alerts
- Historical data analysis

**Service Definition:**
```proto
service EnvironmentService {
  rpc GetAirQuality (AirQualityRequest) returns (AirQualityResponse) {}
  rpc GetNoiseLevel (NoiseLevelRequest) returns (NoiseLevelResponse) {}
  rpc GetWeather (WeatherRequest) returns (WeatherResponse) {}
  rpc StreamEnvironmentalData (StreamRequest) returns (stream EnvironmentalData) {}
}
```

**Message Definitions:**
```proto
message AirQualityRequest {
  string location = 1;
}

message AirQualityResponse {
  string location = 1;
  int32 pm25 = 2;
  int32 pm10 = 3;
  int32 no2 = 4;
  int32 o3 = 5;
  int64 timestamp = 6;
}

message EnvironmentalData {
  string location = 1;
  int32 air_quality_index = 2;
  int32 noise_level = 3;
  int32 temperature = 4;
  int32 humidity = 5;
  int64 timestamp = 6;
}
```

### 3. Public Transport Service
The Public Transport Service manages and coordinates public transportation systems, providing real-time information and updates to commuters.

**Key Functionalities:**
- Real-time vehicle tracking
- Schedule management
- Service status updates
- Disruption reporting
- Route optimization

**Service Definition:**
```proto
service TransportService {
  rpc GetVehicleLocation (VehicleLocationRequest) returns (VehicleLocation) {}
  rpc GetServiceStatus (ServiceStatusRequest) returns (ServiceStatusResponse) {}
  rpc ReportDisruption (DisruptionReport) returns (DisruptionResponse) {}
  rpc StreamVehicleLocations (VehicleLocationRequest) returns (stream VehicleLocation) {}
}
```

**Message Definitions:**
```proto
message VehicleLocationRequest {
  string vehicle_id = 1;
  string route_id = 2;
}

message VehicleLocation {
  string vehicle_id = 1;
  string route_id = 2;
  double latitude = 3;
  double longitude = 4;
  int32 speed = 5;
  int64 timestamp = 6;
}

message ServiceStatusResponse {
  string route_id = 1;
  string status = 2;
  int32 delay_minutes = 3;
  string message = 4;
}
```

## System Architecture
The system follows a distributed architecture with:
- **Service Discovery**: Using a naming service for service registration and discovery
- **Load Balancing**: Distributed across multiple servers
- **Fault Tolerance**: Redundant services and error handling
- **Scalability**: Horizontal scaling capability
- **Security**: Authentication and authorization mechanisms

## Microservices Architecture
The London Smart City system is built using a microservices architecture, which provides several key benefits:

### 1. Service Independence
Each service in the system:
- Runs as a separate process
- Has its own database/storage
- Can be deployed independently
- Can be scaled independently
- Can use different technologies if needed

### 2. Communication Between Services
Services communicate using:
- gRPC for efficient service-to-service communication
- Protocol Buffers for data serialization
- Service discovery for locating other services
- Asynchronous messaging for event-driven communication

### 3. Key Microservices

#### Traffic Management Service
- **Purpose**: Manages traffic flow and incidents
- **Responsibilities**:
  - Real-time traffic monitoring
  - Signal timing control
  - Incident management
  - Traffic data streaming
- **Technologies**: Java, gRPC, Protocol Buffers

#### Environmental Monitoring Service
- **Purpose**: Tracks environmental conditions
- **Responsibilities**:
  - Air quality monitoring
  - Noise level measurement
  - Weather tracking
  - Environmental alerts
- **Technologies**: Java, gRPC, Protocol Buffers

#### Public Transport Service
- **Purpose**: Manages public transportation
- **Responsibilities**:
  - Vehicle tracking
  - Schedule management
  - Service status updates
  - Disruption handling
- **Technologies**: Java, gRPC, Protocol Buffers

### 4. Benefits of Microservices Architecture

#### Scalability
- Each service can be scaled independently based on demand
- Traffic service can handle more requests during peak hours
- Environmental service can scale based on monitoring needs

#### Resilience
- Failure in one service doesn't affect others
- Services can be restarted independently
- Redundancy can be implemented per service

#### Maintainability
- Smaller, focused codebases
- Easier to understand and modify
- Independent deployment cycles
- Technology flexibility per service

#### Development Efficiency
- Teams can work on different services simultaneously
- Faster development cycles
- Easier testing and deployment
- Better resource utilization

### 5. Challenges and Solutions

#### Service Discovery
- **Challenge**: Services need to find each other
- **Solution**: Implemented naming service for service registration and discovery

#### Data Consistency
- **Challenge**: Maintaining data across services
- **Solution**: Event-driven architecture and eventual consistency

#### Monitoring
- **Challenge**: Tracking multiple services
- **Solution**: Centralized logging and metrics collection

#### Security
- **Challenge**: Securing inter-service communication
- **Solution**: gRPC with TLS and authentication

## Technical Implementation
- **Protocol**: gRPC for efficient service communication
- **Language**: Java for service implementation
- **GUI**: Java Swing for client interface
- **Data Storage**: In-memory with persistence capabilities
- **Monitoring**: Integrated logging and metrics collection

## Contribution to Smart City
The services collectively contribute to:
1. **Traffic Optimization**: Reducing congestion and improving flow
2. **Environmental Protection**: Monitoring and mitigating pollution
3. **Public Safety**: Quick response to incidents and emergencies
4. **Resource Efficiency**: Optimizing public transport routes
5. **Data Analytics**: Providing insights for urban planning
6. **Public Information**: Real-time updates for commuters

## Future Enhancements
- Integration with emergency services
- Machine learning for traffic prediction
- Advanced analytics dashboard
- Mobile application support
- IoT device integration

## Implementation Details

### Traffic Service Implementation
The Traffic Service is implemented in Java and provides the following features:
- Real-time traffic status updates
- Signal timing control
- Incident management
- Data streaming capabilities

Key components:
- `TrafficService.java`: Main service implementation
- `TrafficPanel.java`: GUI client interface
- `TrafficServiceProto.java`: Generated protocol buffer classes

### Error Handling
The system implements comprehensive error handling:
- gRPC error propagation
- Client-side error display
- Connection state management
- Automatic reconnection attempts

### Service Discovery
Services register with the naming service using:
- Service type identification
- Port configuration
- Heartbeat management
- Automatic service discovery

## Building and Running
To build and run the project:
1. Ensure Java 11+ is installed
2. Install Maven
3. Run `mvn clean install`
4. Start the services
5. Launch the GUI client

## Dependencies
- gRPC
- SLF4J
- Java Swing
- Maven
- Protocol Buffers

## Project Ownership
This is a personal project developed as part of the Distributed Systems course. All rights reserved by the author. 