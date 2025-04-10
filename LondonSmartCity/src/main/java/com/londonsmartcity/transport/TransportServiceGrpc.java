package com.londonsmartcity.transport;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Public Transport Service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: transport_service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class TransportServiceGrpc {

  private TransportServiceGrpc() {}

  public static final String SERVICE_NAME = "com.londonsmartcity.transport.TransportService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.londonsmartcity.transport.ArrivalInfoRequest,
      com.londonsmartcity.transport.ArrivalInfoResponse> getGetArrivalInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetArrivalInfo",
      requestType = com.londonsmartcity.transport.ArrivalInfoRequest.class,
      responseType = com.londonsmartcity.transport.ArrivalInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.londonsmartcity.transport.ArrivalInfoRequest,
      com.londonsmartcity.transport.ArrivalInfoResponse> getGetArrivalInfoMethod() {
    io.grpc.MethodDescriptor<com.londonsmartcity.transport.ArrivalInfoRequest, com.londonsmartcity.transport.ArrivalInfoResponse> getGetArrivalInfoMethod;
    if ((getGetArrivalInfoMethod = TransportServiceGrpc.getGetArrivalInfoMethod) == null) {
      synchronized (TransportServiceGrpc.class) {
        if ((getGetArrivalInfoMethod = TransportServiceGrpc.getGetArrivalInfoMethod) == null) {
          TransportServiceGrpc.getGetArrivalInfoMethod = getGetArrivalInfoMethod =
              io.grpc.MethodDescriptor.<com.londonsmartcity.transport.ArrivalInfoRequest, com.londonsmartcity.transport.ArrivalInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetArrivalInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.transport.ArrivalInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.transport.ArrivalInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TransportServiceMethodDescriptorSupplier("GetArrivalInfo"))
              .build();
        }
      }
    }
    return getGetArrivalInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.londonsmartcity.transport.ServiceStatusRequest,
      com.londonsmartcity.transport.ServiceStatusResponse> getGetServiceStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetServiceStatus",
      requestType = com.londonsmartcity.transport.ServiceStatusRequest.class,
      responseType = com.londonsmartcity.transport.ServiceStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.londonsmartcity.transport.ServiceStatusRequest,
      com.londonsmartcity.transport.ServiceStatusResponse> getGetServiceStatusMethod() {
    io.grpc.MethodDescriptor<com.londonsmartcity.transport.ServiceStatusRequest, com.londonsmartcity.transport.ServiceStatusResponse> getGetServiceStatusMethod;
    if ((getGetServiceStatusMethod = TransportServiceGrpc.getGetServiceStatusMethod) == null) {
      synchronized (TransportServiceGrpc.class) {
        if ((getGetServiceStatusMethod = TransportServiceGrpc.getGetServiceStatusMethod) == null) {
          TransportServiceGrpc.getGetServiceStatusMethod = getGetServiceStatusMethod =
              io.grpc.MethodDescriptor.<com.londonsmartcity.transport.ServiceStatusRequest, com.londonsmartcity.transport.ServiceStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetServiceStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.transport.ServiceStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.transport.ServiceStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TransportServiceMethodDescriptorSupplier("GetServiceStatus"))
              .build();
        }
      }
    }
    return getGetServiceStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.londonsmartcity.transport.DisruptionReport,
      com.londonsmartcity.transport.DisruptionResponse> getReportDisruptionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReportDisruption",
      requestType = com.londonsmartcity.transport.DisruptionReport.class,
      responseType = com.londonsmartcity.transport.DisruptionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.londonsmartcity.transport.DisruptionReport,
      com.londonsmartcity.transport.DisruptionResponse> getReportDisruptionMethod() {
    io.grpc.MethodDescriptor<com.londonsmartcity.transport.DisruptionReport, com.londonsmartcity.transport.DisruptionResponse> getReportDisruptionMethod;
    if ((getReportDisruptionMethod = TransportServiceGrpc.getReportDisruptionMethod) == null) {
      synchronized (TransportServiceGrpc.class) {
        if ((getReportDisruptionMethod = TransportServiceGrpc.getReportDisruptionMethod) == null) {
          TransportServiceGrpc.getReportDisruptionMethod = getReportDisruptionMethod =
              io.grpc.MethodDescriptor.<com.londonsmartcity.transport.DisruptionReport, com.londonsmartcity.transport.DisruptionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReportDisruption"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.transport.DisruptionReport.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.transport.DisruptionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TransportServiceMethodDescriptorSupplier("ReportDisruption"))
              .build();
        }
      }
    }
    return getReportDisruptionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.londonsmartcity.transport.VehicleLocationRequest,
      com.londonsmartcity.transport.VehicleLocation> getStreamVehicleLocationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamVehicleLocations",
      requestType = com.londonsmartcity.transport.VehicleLocationRequest.class,
      responseType = com.londonsmartcity.transport.VehicleLocation.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.londonsmartcity.transport.VehicleLocationRequest,
      com.londonsmartcity.transport.VehicleLocation> getStreamVehicleLocationsMethod() {
    io.grpc.MethodDescriptor<com.londonsmartcity.transport.VehicleLocationRequest, com.londonsmartcity.transport.VehicleLocation> getStreamVehicleLocationsMethod;
    if ((getStreamVehicleLocationsMethod = TransportServiceGrpc.getStreamVehicleLocationsMethod) == null) {
      synchronized (TransportServiceGrpc.class) {
        if ((getStreamVehicleLocationsMethod = TransportServiceGrpc.getStreamVehicleLocationsMethod) == null) {
          TransportServiceGrpc.getStreamVehicleLocationsMethod = getStreamVehicleLocationsMethod =
              io.grpc.MethodDescriptor.<com.londonsmartcity.transport.VehicleLocationRequest, com.londonsmartcity.transport.VehicleLocation>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StreamVehicleLocations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.transport.VehicleLocationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.transport.VehicleLocation.getDefaultInstance()))
              .setSchemaDescriptor(new TransportServiceMethodDescriptorSupplier("StreamVehicleLocations"))
              .build();
        }
      }
    }
    return getStreamVehicleLocationsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TransportServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TransportServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TransportServiceStub>() {
        @java.lang.Override
        public TransportServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TransportServiceStub(channel, callOptions);
        }
      };
    return TransportServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TransportServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TransportServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TransportServiceBlockingStub>() {
        @java.lang.Override
        public TransportServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TransportServiceBlockingStub(channel, callOptions);
        }
      };
    return TransportServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TransportServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TransportServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TransportServiceFutureStub>() {
        @java.lang.Override
        public TransportServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TransportServiceFutureStub(channel, callOptions);
        }
      };
    return TransportServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Public Transport Service
   * </pre>
   */
  public static abstract class TransportServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Get real-time bus/tube arrival information
     * </pre>
     */
    public void getArrivalInfo(com.londonsmartcity.transport.ArrivalInfoRequest request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.transport.ArrivalInfoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetArrivalInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get service status for all lines
     * </pre>
     */
    public void getServiceStatus(com.londonsmartcity.transport.ServiceStatusRequest request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.transport.ServiceStatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetServiceStatusMethod(), responseObserver);
    }

    /**
     * <pre>
     * Report service disruption
     * </pre>
     */
    public void reportDisruption(com.londonsmartcity.transport.DisruptionReport request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.transport.DisruptionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReportDisruptionMethod(), responseObserver);
    }

    /**
     * <pre>
     * Stream real-time vehicle locations
     * </pre>
     */
    public void streamVehicleLocations(com.londonsmartcity.transport.VehicleLocationRequest request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.transport.VehicleLocation> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStreamVehicleLocationsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetArrivalInfoMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.londonsmartcity.transport.ArrivalInfoRequest,
                com.londonsmartcity.transport.ArrivalInfoResponse>(
                  this, METHODID_GET_ARRIVAL_INFO)))
          .addMethod(
            getGetServiceStatusMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.londonsmartcity.transport.ServiceStatusRequest,
                com.londonsmartcity.transport.ServiceStatusResponse>(
                  this, METHODID_GET_SERVICE_STATUS)))
          .addMethod(
            getReportDisruptionMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.londonsmartcity.transport.DisruptionReport,
                com.londonsmartcity.transport.DisruptionResponse>(
                  this, METHODID_REPORT_DISRUPTION)))
          .addMethod(
            getStreamVehicleLocationsMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                com.londonsmartcity.transport.VehicleLocationRequest,
                com.londonsmartcity.transport.VehicleLocation>(
                  this, METHODID_STREAM_VEHICLE_LOCATIONS)))
          .build();
    }
  }

  /**
   * <pre>
   * Public Transport Service
   * </pre>
   */
  public static final class TransportServiceStub extends io.grpc.stub.AbstractAsyncStub<TransportServiceStub> {
    private TransportServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransportServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TransportServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Get real-time bus/tube arrival information
     * </pre>
     */
    public void getArrivalInfo(com.londonsmartcity.transport.ArrivalInfoRequest request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.transport.ArrivalInfoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetArrivalInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get service status for all lines
     * </pre>
     */
    public void getServiceStatus(com.londonsmartcity.transport.ServiceStatusRequest request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.transport.ServiceStatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetServiceStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Report service disruption
     * </pre>
     */
    public void reportDisruption(com.londonsmartcity.transport.DisruptionReport request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.transport.DisruptionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReportDisruptionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Stream real-time vehicle locations
     * </pre>
     */
    public void streamVehicleLocations(com.londonsmartcity.transport.VehicleLocationRequest request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.transport.VehicleLocation> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getStreamVehicleLocationsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Public Transport Service
   * </pre>
   */
  public static final class TransportServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<TransportServiceBlockingStub> {
    private TransportServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransportServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TransportServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Get real-time bus/tube arrival information
     * </pre>
     */
    public com.londonsmartcity.transport.ArrivalInfoResponse getArrivalInfo(com.londonsmartcity.transport.ArrivalInfoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetArrivalInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get service status for all lines
     * </pre>
     */
    public com.londonsmartcity.transport.ServiceStatusResponse getServiceStatus(com.londonsmartcity.transport.ServiceStatusRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetServiceStatusMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Report service disruption
     * </pre>
     */
    public com.londonsmartcity.transport.DisruptionResponse reportDisruption(com.londonsmartcity.transport.DisruptionReport request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReportDisruptionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Stream real-time vehicle locations
     * </pre>
     */
    public java.util.Iterator<com.londonsmartcity.transport.VehicleLocation> streamVehicleLocations(
        com.londonsmartcity.transport.VehicleLocationRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getStreamVehicleLocationsMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Public Transport Service
   * </pre>
   */
  public static final class TransportServiceFutureStub extends io.grpc.stub.AbstractFutureStub<TransportServiceFutureStub> {
    private TransportServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransportServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TransportServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Get real-time bus/tube arrival information
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.londonsmartcity.transport.ArrivalInfoResponse> getArrivalInfo(
        com.londonsmartcity.transport.ArrivalInfoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetArrivalInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get service status for all lines
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.londonsmartcity.transport.ServiceStatusResponse> getServiceStatus(
        com.londonsmartcity.transport.ServiceStatusRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetServiceStatusMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Report service disruption
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.londonsmartcity.transport.DisruptionResponse> reportDisruption(
        com.londonsmartcity.transport.DisruptionReport request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReportDisruptionMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_ARRIVAL_INFO = 0;
  private static final int METHODID_GET_SERVICE_STATUS = 1;
  private static final int METHODID_REPORT_DISRUPTION = 2;
  private static final int METHODID_STREAM_VEHICLE_LOCATIONS = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TransportServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TransportServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_ARRIVAL_INFO:
          serviceImpl.getArrivalInfo((com.londonsmartcity.transport.ArrivalInfoRequest) request,
              (io.grpc.stub.StreamObserver<com.londonsmartcity.transport.ArrivalInfoResponse>) responseObserver);
          break;
        case METHODID_GET_SERVICE_STATUS:
          serviceImpl.getServiceStatus((com.londonsmartcity.transport.ServiceStatusRequest) request,
              (io.grpc.stub.StreamObserver<com.londonsmartcity.transport.ServiceStatusResponse>) responseObserver);
          break;
        case METHODID_REPORT_DISRUPTION:
          serviceImpl.reportDisruption((com.londonsmartcity.transport.DisruptionReport) request,
              (io.grpc.stub.StreamObserver<com.londonsmartcity.transport.DisruptionResponse>) responseObserver);
          break;
        case METHODID_STREAM_VEHICLE_LOCATIONS:
          serviceImpl.streamVehicleLocations((com.londonsmartcity.transport.VehicleLocationRequest) request,
              (io.grpc.stub.StreamObserver<com.londonsmartcity.transport.VehicleLocation>) responseObserver);
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

  private static abstract class TransportServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TransportServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.londonsmartcity.transport.TransportServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TransportService");
    }
  }

  private static final class TransportServiceFileDescriptorSupplier
      extends TransportServiceBaseDescriptorSupplier {
    TransportServiceFileDescriptorSupplier() {}
  }

  private static final class TransportServiceMethodDescriptorSupplier
      extends TransportServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TransportServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (TransportServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TransportServiceFileDescriptorSupplier())
              .addMethod(getGetArrivalInfoMethod())
              .addMethod(getGetServiceStatusMethod())
              .addMethod(getReportDisruptionMethod())
              .addMethod(getStreamVehicleLocationsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
