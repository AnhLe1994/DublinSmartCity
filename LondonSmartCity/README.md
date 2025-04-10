# London Smart City System

A distributed system that simulates and manages various urban services in London using gRPC for communication and a modern GUI for control.

## Features

- **Service Discovery**: Automatic registration and discovery of services
- **Traffic Management**:
  - Real-time traffic monitoring
  - Traffic signal control
  - Incident reporting
  - Congestion level tracking
- **Public Transport**:
  - Real-time arrival information
  - Service status monitoring
  - Disruption reporting
  - Vehicle location tracking
- **Environmental Monitoring**:
  - Air quality monitoring
  - Temperature and humidity tracking
  - Noise level monitoring
  - CO2 level tracking
  - Environmental alerts

## Architecture

The system consists of several microservices:

1. **Service Discovery Server** (Port: 50051)
   - Handles service registration
   - Maintains service registry
   - Provides service discovery

2. **Traffic Service** (Port: 50052)
   - Manages traffic signals
   - Monitors traffic conditions
   - Handles incident reports

3. **Transport Service** (Port: 50053)
   - Tracks public transport vehicles
   - Provides arrival predictions
   - Manages service disruptions

4. **Environment Service** (Port: 50054)
   - Monitors environmental conditions
   - Tracks air quality
   - Manages environmental alerts

## Technologies Used

- Java 8+
- gRPC for service communication
- Protocol Buffers for data serialization
- Swing for GUI
- SLF4J with Logback for logging
- Maven for dependency management

## Getting Started

### Prerequisites

- Java JDK 8 or higher
- Maven 3.6 or higher
- Git

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/LondonSmartCity.git
   cd LondonSmartCity
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   java -jar target/LondonSmartCity-1.0-SNAPSHOT-jar-with-dependencies.jar
   ```

## Usage

1. The system will automatically start all services and the GUI controller.
2. Use the GUI tabs to interact with different services:
   - Traffic Management: Monitor and control traffic conditions
   - Public Transport: Track public transport and manage disruptions
   - Environment: Monitor environmental conditions and manage alerts

## Development

### Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── londonsmartcity/
│   │           ├── discovery/    # Service discovery
│   │           ├── traffic/      # Traffic service
│   │           ├── transport/    # Transport service
│   │           ├── environment/  # Environment service
│   │           └── gui/         # GUI components
│   └── proto/
│       └── *.proto             # Protocol buffer definitions
```

### Adding New Features

1. Define new service methods in the appropriate `.proto` file
2. Generate the gRPC code using Maven
3. Implement the service methods
4. Add corresponding GUI components

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request


## Acknowledgments

- gRPC team for the excellent RPC framework
- Protocol Buffers team for the efficient serialization
- The Java Swing team for the GUI framework 