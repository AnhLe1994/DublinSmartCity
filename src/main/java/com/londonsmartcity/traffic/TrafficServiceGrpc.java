package com.londonsmartcity.traffic;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Traffic Management Service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: traffic_service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class TrafficServiceGrpc {

  private TrafficServiceGrpc() {}

  public static final String SERVICE_NAME = "com.londonsmartcity.traffic.TrafficService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.londonsmartcity.traffic.TrafficStatusRequest,
      com.londonsmartcity.traffic.TrafficStatusResponse> getGetTrafficStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTrafficStatus",
      requestType = com.londonsmartcity.traffic.TrafficStatusRequest.class,
      responseType = com.londonsmartcity.traffic.TrafficStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.londonsmartcity.traffic.TrafficStatusRequest,
      com.londonsmartcity.traffic.TrafficStatusResponse> getGetTrafficStatusMethod() {
    io.grpc.MethodDescriptor<com.londonsmartcity.traffic.TrafficStatusRequest, com.londonsmartcity.traffic.TrafficStatusResponse> getGetTrafficStatusMethod;
    if ((getGetTrafficStatusMethod = TrafficServiceGrpc.getGetTrafficStatusMethod) == null) {
      synchronized (TrafficServiceGrpc.class) {
        if ((getGetTrafficStatusMethod = TrafficServiceGrpc.getGetTrafficStatusMethod) == null) {
          TrafficServiceGrpc.getGetTrafficStatusMethod = getGetTrafficStatusMethod =
              io.grpc.MethodDescriptor.<com.londonsmartcity.traffic.TrafficStatusRequest, com.londonsmartcity.traffic.TrafficStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetTrafficStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.traffic.TrafficStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.traffic.TrafficStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TrafficServiceMethodDescriptorSupplier("GetTrafficStatus"))
              .build();
        }
      }
    }
    return getGetTrafficStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.londonsmartcity.traffic.SignalTimingRequest,
      com.londonsmartcity.traffic.SignalTimingResponse> getUpdateSignalTimingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateSignalTiming",
      requestType = com.londonsmartcity.traffic.SignalTimingRequest.class,
      responseType = com.londonsmartcity.traffic.SignalTimingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.londonsmartcity.traffic.SignalTimingRequest,
      com.londonsmartcity.traffic.SignalTimingResponse> getUpdateSignalTimingMethod() {
    io.grpc.MethodDescriptor<com.londonsmartcity.traffic.SignalTimingRequest, com.londonsmartcity.traffic.SignalTimingResponse> getUpdateSignalTimingMethod;
    if ((getUpdateSignalTimingMethod = TrafficServiceGrpc.getUpdateSignalTimingMethod) == null) {
      synchronized (TrafficServiceGrpc.class) {
        if ((getUpdateSignalTimingMethod = TrafficServiceGrpc.getUpdateSignalTimingMethod) == null) {
          TrafficServiceGrpc.getUpdateSignalTimingMethod = getUpdateSignalTimingMethod =
              io.grpc.MethodDescriptor.<com.londonsmartcity.traffic.SignalTimingRequest, com.londonsmartcity.traffic.SignalTimingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateSignalTiming"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.traffic.SignalTimingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.traffic.SignalTimingResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TrafficServiceMethodDescriptorSupplier("UpdateSignalTiming"))
              .build();
        }
      }
    }
    return getUpdateSignalTimingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.londonsmartcity.traffic.IncidentReport,
      com.londonsmartcity.traffic.IncidentResponse> getReportIncidentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReportIncident",
      requestType = com.londonsmartcity.traffic.IncidentReport.class,
      responseType = com.londonsmartcity.traffic.IncidentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.londonsmartcity.traffic.IncidentReport,
      com.londonsmartcity.traffic.IncidentResponse> getReportIncidentMethod() {
    io.grpc.MethodDescriptor<com.londonsmartcity.traffic.IncidentReport, com.londonsmartcity.traffic.IncidentResponse> getReportIncidentMethod;
    if ((getReportIncidentMethod = TrafficServiceGrpc.getReportIncidentMethod) == null) {
      synchronized (TrafficServiceGrpc.class) {
        if ((getReportIncidentMethod = TrafficServiceGrpc.getReportIncidentMethod) == null) {
          TrafficServiceGrpc.getReportIncidentMethod = getReportIncidentMethod =
              io.grpc.MethodDescriptor.<com.londonsmartcity.traffic.IncidentReport, com.londonsmartcity.traffic.IncidentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReportIncident"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.traffic.IncidentReport.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.traffic.IncidentResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TrafficServiceMethodDescriptorSupplier("ReportIncident"))
              .build();
        }
      }
    }
    return getReportIncidentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.londonsmartcity.traffic.TrafficUpdateRequest,
      com.londonsmartcity.traffic.TrafficUpdate> getStreamTrafficUpdatesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamTrafficUpdates",
      requestType = com.londonsmartcity.traffic.TrafficUpdateRequest.class,
      responseType = com.londonsmartcity.traffic.TrafficUpdate.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.londonsmartcity.traffic.TrafficUpdateRequest,
      com.londonsmartcity.traffic.TrafficUpdate> getStreamTrafficUpdatesMethod() {
    io.grpc.MethodDescriptor<com.londonsmartcity.traffic.TrafficUpdateRequest, com.londonsmartcity.traffic.TrafficUpdate> getStreamTrafficUpdatesMethod;
    if ((getStreamTrafficUpdatesMethod = TrafficServiceGrpc.getStreamTrafficUpdatesMethod) == null) {
      synchronized (TrafficServiceGrpc.class) {
        if ((getStreamTrafficUpdatesMethod = TrafficServiceGrpc.getStreamTrafficUpdatesMethod) == null) {
          TrafficServiceGrpc.getStreamTrafficUpdatesMethod = getStreamTrafficUpdatesMethod =
              io.grpc.MethodDescriptor.<com.londonsmartcity.traffic.TrafficUpdateRequest, com.londonsmartcity.traffic.TrafficUpdate>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StreamTrafficUpdates"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.traffic.TrafficUpdateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.traffic.TrafficUpdate.getDefaultInstance()))
              .setSchemaDescriptor(new TrafficServiceMethodDescriptorSupplier("StreamTrafficUpdates"))
              .build();
        }
      }
    }
    return getStreamTrafficUpdatesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TrafficServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TrafficServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TrafficServiceStub>() {
        @java.lang.Override
        public TrafficServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TrafficServiceStub(channel, callOptions);
        }
      };
    return TrafficServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TrafficServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TrafficServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TrafficServiceBlockingStub>() {
        @java.lang.Override
        public TrafficServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TrafficServiceBlockingStub(channel, callOptions);
        }
      };
    return TrafficServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TrafficServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TrafficServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TrafficServiceFutureStub>() {
        @java.lang.Override
        public TrafficServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TrafficServiceFutureStub(channel, callOptions);
        }
      };
    return TrafficServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Traffic Management Service
   * </pre>
   */
  public static abstract class TrafficServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Get current traffic status for a specific location
     * </pre>
     */
    public void getTrafficStatus(com.londonsmartcity.traffic.TrafficStatusRequest request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.traffic.TrafficStatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetTrafficStatusMethod(), responseObserver);
    }

    /**
     * <pre>
     * Update traffic signal timing
     * </pre>
     */
    public void updateSignalTiming(com.londonsmartcity.traffic.SignalTimingRequest request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.traffic.SignalTimingResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateSignalTimingMethod(), responseObserver);
    }

    /**
     * <pre>
     * Report traffic incident
     * </pre>
     */
    public void reportIncident(com.londonsmartcity.traffic.IncidentReport request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.traffic.IncidentResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReportIncidentMethod(), responseObserver);
    }

    /**
     * <pre>
     * Stream real-time traffic updates
     * </pre>
     */
    public void streamTrafficUpdates(com.londonsmartcity.traffic.TrafficUpdateRequest request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.traffic.TrafficUpdate> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStreamTrafficUpdatesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetTrafficStatusMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.londonsmartcity.traffic.TrafficStatusRequest,
                com.londonsmartcity.traffic.TrafficStatusResponse>(
                  this, METHODID_GET_TRAFFIC_STATUS)))
          .addMethod(
            getUpdateSignalTimingMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.londonsmartcity.traffic.SignalTimingRequest,
                com.londonsmartcity.traffic.SignalTimingResponse>(
                  this, METHODID_UPDATE_SIGNAL_TIMING)))
          .addMethod(
            getReportIncidentMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.londonsmartcity.traffic.IncidentReport,
                com.londonsmartcity.traffic.IncidentResponse>(
                  this, METHODID_REPORT_INCIDENT)))
          .addMethod(
            getStreamTrafficUpdatesMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                com.londonsmartcity.traffic.TrafficUpdateRequest,
                com.londonsmartcity.traffic.TrafficUpdate>(
                  this, METHODID_STREAM_TRAFFIC_UPDATES)))
          .build();
    }
  }

  /**
   * <pre>
   * Traffic Management Service
   * </pre>
   */
  public static final class TrafficServiceStub extends io.grpc.stub.AbstractAsyncStub<TrafficServiceStub> {
    private TrafficServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TrafficServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TrafficServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Get current traffic status for a specific location
     * </pre>
     */
    public void getTrafficStatus(com.londonsmartcity.traffic.TrafficStatusRequest request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.traffic.TrafficStatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetTrafficStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Update traffic signal timing
     * </pre>
     */
    public void updateSignalTiming(com.londonsmartcity.traffic.SignalTimingRequest request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.traffic.SignalTimingResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateSignalTimingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Report traffic incident
     * </pre>
     */
    public void reportIncident(com.londonsmartcity.traffic.IncidentReport request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.traffic.IncidentResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReportIncidentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Stream real-time traffic updates
     * </pre>
     */
    public void streamTrafficUpdates(com.londonsmartcity.traffic.TrafficUpdateRequest request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.traffic.TrafficUpdate> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getStreamTrafficUpdatesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Traffic Management Service
   * </pre>
   */
  public static final class TrafficServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<TrafficServiceBlockingStub> {
    private TrafficServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TrafficServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TrafficServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Get current traffic status for a specific location
     * </pre>
     */
    public com.londonsmartcity.traffic.TrafficStatusResponse getTrafficStatus(com.londonsmartcity.traffic.TrafficStatusRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetTrafficStatusMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Update traffic signal timing
     * </pre>
     */
    public com.londonsmartcity.traffic.SignalTimingResponse updateSignalTiming(com.londonsmartcity.traffic.SignalTimingRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateSignalTimingMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Report traffic incident
     * </pre>
     */
    public com.londonsmartcity.traffic.IncidentResponse reportIncident(com.londonsmartcity.traffic.IncidentReport request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReportIncidentMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Stream real-time traffic updates
     * </pre>
     */
    public java.util.Iterator<com.londonsmartcity.traffic.TrafficUpdate> streamTrafficUpdates(
        com.londonsmartcity.traffic.TrafficUpdateRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getStreamTrafficUpdatesMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Traffic Management Service
   * </pre>
   */
  public static final class TrafficServiceFutureStub extends io.grpc.stub.AbstractFutureStub<TrafficServiceFutureStub> {
    private TrafficServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TrafficServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TrafficServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Get current traffic status for a specific location
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.londonsmartcity.traffic.TrafficStatusResponse> getTrafficStatus(
        com.londonsmartcity.traffic.TrafficStatusRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetTrafficStatusMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Update traffic signal timing
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.londonsmartcity.traffic.SignalTimingResponse> updateSignalTiming(
        com.londonsmartcity.traffic.SignalTimingRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateSignalTimingMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Report traffic incident
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.londonsmartcity.traffic.IncidentResponse> reportIncident(
        com.londonsmartcity.traffic.IncidentReport request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReportIncidentMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_TRAFFIC_STATUS = 0;
  private static final int METHODID_UPDATE_SIGNAL_TIMING = 1;
  private static final int METHODID_REPORT_INCIDENT = 2;
  private static final int METHODID_STREAM_TRAFFIC_UPDATES = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TrafficServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TrafficServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_TRAFFIC_STATUS:
          serviceImpl.getTrafficStatus((com.londonsmartcity.traffic.TrafficStatusRequest) request,
              (io.grpc.stub.StreamObserver<com.londonsmartcity.traffic.TrafficStatusResponse>) responseObserver);
          break;
        case METHODID_UPDATE_SIGNAL_TIMING:
          serviceImpl.updateSignalTiming((com.londonsmartcity.traffic.SignalTimingRequest) request,
              (io.grpc.stub.StreamObserver<com.londonsmartcity.traffic.SignalTimingResponse>) responseObserver);
          break;
        case METHODID_REPORT_INCIDENT:
          serviceImpl.reportIncident((com.londonsmartcity.traffic.IncidentReport) request,
              (io.grpc.stub.StreamObserver<com.londonsmartcity.traffic.IncidentResponse>) responseObserver);
          break;
        case METHODID_STREAM_TRAFFIC_UPDATES:
          serviceImpl.streamTrafficUpdates((com.londonsmartcity.traffic.TrafficUpdateRequest) request,
              (io.grpc.stub.StreamObserver<com.londonsmartcity.traffic.TrafficUpdate>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class TrafficServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TrafficServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.londonsmartcity.traffic.TrafficServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TrafficService");
    }
  }

  private static final class TrafficServiceFileDescriptorSupplier
      extends TrafficServiceBaseDescriptorSupplier {
    TrafficServiceFileDescriptorSupplier() {}
  }

  private static final class TrafficServiceMethodDescriptorSupplier
      extends TrafficServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TrafficServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (TrafficServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TrafficServiceFileDescriptorSupplier())
              .addMethod(getGetTrafficStatusMethod())
              .addMethod(getUpdateSignalTimingMethod())
              .addMethod(getReportIncidentMethod())
              .addMethod(getStreamTrafficUpdatesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
