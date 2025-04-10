package com.londonsmartcity.discovery;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Service Discovery Service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: service_discovery.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ServiceDiscoveryGrpc {

  private ServiceDiscoveryGrpc() {}

  public static final String SERVICE_NAME = "discovery.ServiceDiscovery";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.londonsmartcity.discovery.ServiceRegistration,
      com.londonsmartcity.discovery.RegistrationResponse> getRegisterServiceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisterService",
      requestType = com.londonsmartcity.discovery.ServiceRegistration.class,
      responseType = com.londonsmartcity.discovery.RegistrationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.londonsmartcity.discovery.ServiceRegistration,
      com.londonsmartcity.discovery.RegistrationResponse> getRegisterServiceMethod() {
    io.grpc.MethodDescriptor<com.londonsmartcity.discovery.ServiceRegistration, com.londonsmartcity.discovery.RegistrationResponse> getRegisterServiceMethod;
    if ((getRegisterServiceMethod = ServiceDiscoveryGrpc.getRegisterServiceMethod) == null) {
      synchronized (ServiceDiscoveryGrpc.class) {
        if ((getRegisterServiceMethod = ServiceDiscoveryGrpc.getRegisterServiceMethod) == null) {
          ServiceDiscoveryGrpc.getRegisterServiceMethod = getRegisterServiceMethod =
              io.grpc.MethodDescriptor.<com.londonsmartcity.discovery.ServiceRegistration, com.londonsmartcity.discovery.RegistrationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisterService"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.discovery.ServiceRegistration.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.discovery.RegistrationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServiceDiscoveryMethodDescriptorSupplier("RegisterService"))
              .build();
        }
      }
    }
    return getRegisterServiceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.londonsmartcity.discovery.ServiceDiscoveryRequest,
      com.londonsmartcity.discovery.ServiceDiscoveryResponse> getDiscoverServicesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DiscoverServices",
      requestType = com.londonsmartcity.discovery.ServiceDiscoveryRequest.class,
      responseType = com.londonsmartcity.discovery.ServiceDiscoveryResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.londonsmartcity.discovery.ServiceDiscoveryRequest,
      com.londonsmartcity.discovery.ServiceDiscoveryResponse> getDiscoverServicesMethod() {
    io.grpc.MethodDescriptor<com.londonsmartcity.discovery.ServiceDiscoveryRequest, com.londonsmartcity.discovery.ServiceDiscoveryResponse> getDiscoverServicesMethod;
    if ((getDiscoverServicesMethod = ServiceDiscoveryGrpc.getDiscoverServicesMethod) == null) {
      synchronized (ServiceDiscoveryGrpc.class) {
        if ((getDiscoverServicesMethod = ServiceDiscoveryGrpc.getDiscoverServicesMethod) == null) {
          ServiceDiscoveryGrpc.getDiscoverServicesMethod = getDiscoverServicesMethod =
              io.grpc.MethodDescriptor.<com.londonsmartcity.discovery.ServiceDiscoveryRequest, com.londonsmartcity.discovery.ServiceDiscoveryResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DiscoverServices"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.discovery.ServiceDiscoveryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.discovery.ServiceDiscoveryResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServiceDiscoveryMethodDescriptorSupplier("DiscoverServices"))
              .build();
        }
      }
    }
    return getDiscoverServicesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.londonsmartcity.discovery.HeartbeatRequest,
      com.londonsmartcity.discovery.HeartbeatResponse> getHeartbeatMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Heartbeat",
      requestType = com.londonsmartcity.discovery.HeartbeatRequest.class,
      responseType = com.londonsmartcity.discovery.HeartbeatResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.londonsmartcity.discovery.HeartbeatRequest,
      com.londonsmartcity.discovery.HeartbeatResponse> getHeartbeatMethod() {
    io.grpc.MethodDescriptor<com.londonsmartcity.discovery.HeartbeatRequest, com.londonsmartcity.discovery.HeartbeatResponse> getHeartbeatMethod;
    if ((getHeartbeatMethod = ServiceDiscoveryGrpc.getHeartbeatMethod) == null) {
      synchronized (ServiceDiscoveryGrpc.class) {
        if ((getHeartbeatMethod = ServiceDiscoveryGrpc.getHeartbeatMethod) == null) {
          ServiceDiscoveryGrpc.getHeartbeatMethod = getHeartbeatMethod =
              io.grpc.MethodDescriptor.<com.londonsmartcity.discovery.HeartbeatRequest, com.londonsmartcity.discovery.HeartbeatResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Heartbeat"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.discovery.HeartbeatRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.discovery.HeartbeatResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServiceDiscoveryMethodDescriptorSupplier("Heartbeat"))
              .build();
        }
      }
    }
    return getHeartbeatMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.londonsmartcity.discovery.UnregisterRequest,
      com.londonsmartcity.discovery.UnregisterResponse> getUnregisterServiceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnregisterService",
      requestType = com.londonsmartcity.discovery.UnregisterRequest.class,
      responseType = com.londonsmartcity.discovery.UnregisterResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.londonsmartcity.discovery.UnregisterRequest,
      com.londonsmartcity.discovery.UnregisterResponse> getUnregisterServiceMethod() {
    io.grpc.MethodDescriptor<com.londonsmartcity.discovery.UnregisterRequest, com.londonsmartcity.discovery.UnregisterResponse> getUnregisterServiceMethod;
    if ((getUnregisterServiceMethod = ServiceDiscoveryGrpc.getUnregisterServiceMethod) == null) {
      synchronized (ServiceDiscoveryGrpc.class) {
        if ((getUnregisterServiceMethod = ServiceDiscoveryGrpc.getUnregisterServiceMethod) == null) {
          ServiceDiscoveryGrpc.getUnregisterServiceMethod = getUnregisterServiceMethod =
              io.grpc.MethodDescriptor.<com.londonsmartcity.discovery.UnregisterRequest, com.londonsmartcity.discovery.UnregisterResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnregisterService"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.discovery.UnregisterRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.discovery.UnregisterResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServiceDiscoveryMethodDescriptorSupplier("UnregisterService"))
              .build();
        }
      }
    }
    return getUnregisterServiceMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ServiceDiscoveryStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ServiceDiscoveryStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ServiceDiscoveryStub>() {
        @java.lang.Override
        public ServiceDiscoveryStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ServiceDiscoveryStub(channel, callOptions);
        }
      };
    return ServiceDiscoveryStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ServiceDiscoveryBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ServiceDiscoveryBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ServiceDiscoveryBlockingStub>() {
        @java.lang.Override
        public ServiceDiscoveryBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ServiceDiscoveryBlockingStub(channel, callOptions);
        }
      };
    return ServiceDiscoveryBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ServiceDiscoveryFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ServiceDiscoveryFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ServiceDiscoveryFutureStub>() {
        @java.lang.Override
        public ServiceDiscoveryFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ServiceDiscoveryFutureStub(channel, callOptions);
        }
      };
    return ServiceDiscoveryFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Service Discovery Service
   * </pre>
   */
  public static abstract class ServiceDiscoveryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Register a new service
     * </pre>
     */
    public void registerService(com.londonsmartcity.discovery.ServiceRegistration request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.discovery.RegistrationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRegisterServiceMethod(), responseObserver);
    }

    /**
     * <pre>
     * Discover services by type
     * </pre>
     */
    public void discoverServices(com.londonsmartcity.discovery.ServiceDiscoveryRequest request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.discovery.ServiceDiscoveryResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDiscoverServicesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Heartbeat to keep service registration alive
     * </pre>
     */
    public void heartbeat(com.londonsmartcity.discovery.HeartbeatRequest request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.discovery.HeartbeatResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getHeartbeatMethod(), responseObserver);
    }

    /**
     * <pre>
     * Unregister a service
     * </pre>
     */
    public void unregisterService(com.londonsmartcity.discovery.UnregisterRequest request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.discovery.UnregisterResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUnregisterServiceMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterServiceMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.londonsmartcity.discovery.ServiceRegistration,
                com.londonsmartcity.discovery.RegistrationResponse>(
                  this, METHODID_REGISTER_SERVICE)))
          .addMethod(
            getDiscoverServicesMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.londonsmartcity.discovery.ServiceDiscoveryRequest,
                com.londonsmartcity.discovery.ServiceDiscoveryResponse>(
                  this, METHODID_DISCOVER_SERVICES)))
          .addMethod(
            getHeartbeatMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.londonsmartcity.discovery.HeartbeatRequest,
                com.londonsmartcity.discovery.HeartbeatResponse>(
                  this, METHODID_HEARTBEAT)))
          .addMethod(
            getUnregisterServiceMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.londonsmartcity.discovery.UnregisterRequest,
                com.londonsmartcity.discovery.UnregisterResponse>(
                  this, METHODID_UNREGISTER_SERVICE)))
          .build();
    }
  }

  /**
   * <pre>
   * Service Discovery Service
   * </pre>
   */
  public static final class ServiceDiscoveryStub extends io.grpc.stub.AbstractAsyncStub<ServiceDiscoveryStub> {
    private ServiceDiscoveryStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServiceDiscoveryStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ServiceDiscoveryStub(channel, callOptions);
    }

    /**
     * <pre>
     * Register a new service
     * </pre>
     */
    public void registerService(com.londonsmartcity.discovery.ServiceRegistration request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.discovery.RegistrationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRegisterServiceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Discover services by type
     * </pre>
     */
    public void discoverServices(com.londonsmartcity.discovery.ServiceDiscoveryRequest request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.discovery.ServiceDiscoveryResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDiscoverServicesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Heartbeat to keep service registration alive
     * </pre>
     */
    public void heartbeat(com.londonsmartcity.discovery.HeartbeatRequest request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.discovery.HeartbeatResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getHeartbeatMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Unregister a service
     * </pre>
     */
    public void unregisterService(com.londonsmartcity.discovery.UnregisterRequest request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.discovery.UnregisterResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUnregisterServiceMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Service Discovery Service
   * </pre>
   */
  public static final class ServiceDiscoveryBlockingStub extends io.grpc.stub.AbstractBlockingStub<ServiceDiscoveryBlockingStub> {
    private ServiceDiscoveryBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServiceDiscoveryBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ServiceDiscoveryBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Register a new service
     * </pre>
     */
    public com.londonsmartcity.discovery.RegistrationResponse registerService(com.londonsmartcity.discovery.ServiceRegistration request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRegisterServiceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Discover services by type
     * </pre>
     */
    public com.londonsmartcity.discovery.ServiceDiscoveryResponse discoverServices(com.londonsmartcity.discovery.ServiceDiscoveryRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDiscoverServicesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Heartbeat to keep service registration alive
     * </pre>
     */
    public com.londonsmartcity.discovery.HeartbeatResponse heartbeat(com.londonsmartcity.discovery.HeartbeatRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getHeartbeatMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Unregister a service
     * </pre>
     */
    public com.londonsmartcity.discovery.UnregisterResponse unregisterService(com.londonsmartcity.discovery.UnregisterRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUnregisterServiceMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Service Discovery Service
   * </pre>
   */
  public static final class ServiceDiscoveryFutureStub extends io.grpc.stub.AbstractFutureStub<ServiceDiscoveryFutureStub> {
    private ServiceDiscoveryFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServiceDiscoveryFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ServiceDiscoveryFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Register a new service
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.londonsmartcity.discovery.RegistrationResponse> registerService(
        com.londonsmartcity.discovery.ServiceRegistration request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRegisterServiceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Discover services by type
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.londonsmartcity.discovery.ServiceDiscoveryResponse> discoverServices(
        com.londonsmartcity.discovery.ServiceDiscoveryRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDiscoverServicesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Heartbeat to keep service registration alive
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.londonsmartcity.discovery.HeartbeatResponse> heartbeat(
        com.londonsmartcity.discovery.HeartbeatRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getHeartbeatMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Unregister a service
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.londonsmartcity.discovery.UnregisterResponse> unregisterService(
        com.londonsmartcity.discovery.UnregisterRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUnregisterServiceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER_SERVICE = 0;
  private static final int METHODID_DISCOVER_SERVICES = 1;
  private static final int METHODID_HEARTBEAT = 2;
  private static final int METHODID_UNREGISTER_SERVICE = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ServiceDiscoveryImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ServiceDiscoveryImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER_SERVICE:
          serviceImpl.registerService((com.londonsmartcity.discovery.ServiceRegistration) request,
              (io.grpc.stub.StreamObserver<com.londonsmartcity.discovery.RegistrationResponse>) responseObserver);
          break;
        case METHODID_DISCOVER_SERVICES:
          serviceImpl.discoverServices((com.londonsmartcity.discovery.ServiceDiscoveryRequest) request,
              (io.grpc.stub.StreamObserver<com.londonsmartcity.discovery.ServiceDiscoveryResponse>) responseObserver);
          break;
        case METHODID_HEARTBEAT:
          serviceImpl.heartbeat((com.londonsmartcity.discovery.HeartbeatRequest) request,
              (io.grpc.stub.StreamObserver<com.londonsmartcity.discovery.HeartbeatResponse>) responseObserver);
          break;
        case METHODID_UNREGISTER_SERVICE:
          serviceImpl.unregisterService((com.londonsmartcity.discovery.UnregisterRequest) request,
              (io.grpc.stub.StreamObserver<com.londonsmartcity.discovery.UnregisterResponse>) responseObserver);
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

  private static abstract class ServiceDiscoveryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ServiceDiscoveryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.londonsmartcity.discovery.ServiceDiscoveryProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ServiceDiscovery");
    }
  }

  private static final class ServiceDiscoveryFileDescriptorSupplier
      extends ServiceDiscoveryBaseDescriptorSupplier {
    ServiceDiscoveryFileDescriptorSupplier() {}
  }

  private static final class ServiceDiscoveryMethodDescriptorSupplier
      extends ServiceDiscoveryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ServiceDiscoveryMethodDescriptorSupplier(String methodName) {
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
      synchronized (ServiceDiscoveryGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ServiceDiscoveryFileDescriptorSupplier())
              .addMethod(getRegisterServiceMethod())
              .addMethod(getDiscoverServicesMethod())
              .addMethod(getHeartbeatMethod())
              .addMethod(getUnregisterServiceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
