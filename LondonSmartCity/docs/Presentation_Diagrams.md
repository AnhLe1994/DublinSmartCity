# Presentation Diagrams for London Smart City Project

This document provides descriptions for creating the key diagrams needed for your presentation.

## 1. System Architecture Diagram (Slide 4)

**Diagram Type**: Component/Architecture diagram

**Components to Include**:
- **Service Discovery Server** (center)
- **Traffic Management Service** (left)
- **Environmental Monitoring Service** (right)
- **Public Transport Service** (bottom)
- **Client Application** (top)

**Connections**:
- All services connect to Service Discovery Server with bidirectional arrows labeled "Registration/Discovery"
- Client connects to Service Discovery Server with arrow labeled "Service Lookup"
- Client connects to all services with dotted arrows labeled "gRPC"
- Include database icons connected to each service

**Style**:
- Use blue boxes for services
- Green box for Service Discovery
- Gray box for client
- Yellow cylinders for databases

## 2. Service Discovery Flowchart (Slide 5)

**Diagram Type**: Sequence diagram

**Sequence**:
1. Service starts up
2. Service registers with Discovery Server
3. Service begins sending heartbeats
4. Client requests service by type
5. Discovery Server returns service info
6. Client connects directly to service
7. Service eventually shuts down and unregisters

**Include**:
- Timestamps on left
- Clear arrows between components
- Brief descriptions of each message
- Highlight heartbeat mechanism

## 3. gRPC Communication Diagram (Slide 10)

**Diagram Type**: Flow diagram

**Components**:
- Client Application
- Generated Client Stub
- Network Layer (with Protocol Buffers serialization)
- Generated Server Stub
- Server Implementation

**Flows to Show**:
1. Method call from client to stub
2. Serialization of request
3. Network transmission
4. Deserialization on server
5. Server processing
6. Response path back to client

**Highlight**:
- Protocol Buffer message format
- Binary transmission
- Strong typing throughout the pipeline
- Streaming capability

## 4. Error Handling Flowchart (Slide 13)

**Diagram Type**: Decision tree

**Scenarios to Include**:
- Service unavailable
- Timeout
- Invalid request
- Internal server error
- Network partition

**For Each Scenario Show**:
- Detection mechanism
- Client-side handling
- Recovery strategy
- User feedback

**Design**:
- Use red for errors
- Green for successful recovery paths
- Yellow for degraded service states

## 5. Scalability Diagram (Slide 14)

**Diagram Type**: Architecture diagram with scaling

**Components**:
- Multiple instances of each service
- Service Discovery Server (possibly clustered)
- Load balancers
- Client applications

**Show**:
- How new service instances register
- How clients discover the appropriate instance
- Load distribution across instances
- Independent scaling of different services

**Include**:
- Metrics that trigger scaling
- Benefits (throughput, reliability, etc.)

## 6. Dashboard Mockup (Slide 12)

**Diagram Type**: UI mockup

**Elements to Include**:
- Navigation tabs for different services
- Traffic status map
- Environmental readings with gauges
- Public transport tracking section
- Alert notifications area
- Control panels for each service

**Design Tips**:
- Use clean, modern UI elements
- Include realistic data values
- Show multiple panels/views
- Highlight real-time update features

## 7. Key Code Structure Diagram

**Diagram Type**: UML or simplified class diagram

**Show**:
- Key classes and their relationships
- Important methods
- Inheritance from BaseService
- Interface implementations
- Protocol buffer generated classes

**Highlight**:
- Service implementation classes
- gRPC service classes
- Data models
- Connection to service discovery

## How to Create These Diagrams

1. **Software Options**:
   - Draw.io (free, web-based)
   - Lucidchart (free tier available)
   - Microsoft Visio (if available)
   - PowerPoint's own drawing tools (simpler diagrams)

2. **Color Scheme**:
   - Primary: #2D6EAD (blue)
   - Secondary: #41AD49 (green)
   - Accent: #F3BD48 (yellow)
   - Background: White or very light gray
   - Text: Dark gray or black

3. **Export Format**:
   - PNG with transparent background
   - At least 1200px width for good resolution
   - Consider creating both light and dark versions if your presentation might be shown in different lighting conditions 