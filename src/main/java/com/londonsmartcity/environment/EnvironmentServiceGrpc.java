package com.londonsmartcity.environment;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Environment Service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: environment_service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class EnvironmentServiceGrpc {

  private EnvironmentServiceGrpc() {}

  public static final String SERVICE_NAME = "com.londonsmartcity.environment.EnvironmentService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.londonsmartcity.environment.AirQualityRequest,
      com.londonsmartcity.environment.AirQualityResponse> getGetAirQualityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAirQuality",
      requestType = com.londonsmartcity.environment.AirQualityRequest.class,
      responseType = com.londonsmartcity.environment.AirQualityResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.londonsmartcity.environment.AirQualityRequest,
      com.londonsmartcity.environment.AirQualityResponse> getGetAirQualityMethod() {
    io.grpc.MethodDescriptor<com.londonsmartcity.environment.AirQualityRequest, com.londonsmartcity.environment.AirQualityResponse> getGetAirQualityMethod;
    if ((getGetAirQualityMethod = EnvironmentServiceGrpc.getGetAirQualityMethod) == null) {
      synchronized (EnvironmentServiceGrpc.class) {
        if ((getGetAirQualityMethod = EnvironmentServiceGrpc.getGetAirQualityMethod) == null) {
          EnvironmentServiceGrpc.getGetAirQualityMethod = getGetAirQualityMethod =
              io.grpc.MethodDescriptor.<com.londonsmartcity.environment.AirQualityRequest, com.londonsmartcity.environment.AirQualityResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAirQuality"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.environment.AirQualityRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.environment.AirQualityResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EnvironmentServiceMethodDescriptorSupplier("GetAirQuality"))
              .build();
        }
      }
    }
    return getGetAirQualityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.londonsmartcity.environment.NoiseLevelRequest,
      com.londonsmartcity.environment.NoiseLevelResponse> getGetNoiseLevelMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetNoiseLevel",
      requestType = com.londonsmartcity.environment.NoiseLevelRequest.class,
      responseType = com.londonsmartcity.environment.NoiseLevelResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.londonsmartcity.environment.NoiseLevelRequest,
      com.londonsmartcity.environment.NoiseLevelResponse> getGetNoiseLevelMethod() {
    io.grpc.MethodDescriptor<com.londonsmartcity.environment.NoiseLevelRequest, com.londonsmartcity.environment.NoiseLevelResponse> getGetNoiseLevelMethod;
    if ((getGetNoiseLevelMethod = EnvironmentServiceGrpc.getGetNoiseLevelMethod) == null) {
      synchronized (EnvironmentServiceGrpc.class) {
        if ((getGetNoiseLevelMethod = EnvironmentServiceGrpc.getGetNoiseLevelMethod) == null) {
          EnvironmentServiceGrpc.getGetNoiseLevelMethod = getGetNoiseLevelMethod =
              io.grpc.MethodDescriptor.<com.londonsmartcity.environment.NoiseLevelRequest, com.londonsmartcity.environment.NoiseLevelResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetNoiseLevel"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.environment.NoiseLevelRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.environment.NoiseLevelResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EnvironmentServiceMethodDescriptorSupplier("GetNoiseLevel"))
              .build();
        }
      }
    }
    return getGetNoiseLevelMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.londonsmartcity.environment.WeatherRequest,
      com.londonsmartcity.environment.WeatherResponse> getGetWeatherMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetWeather",
      requestType = com.londonsmartcity.environment.WeatherRequest.class,
      responseType = com.londonsmartcity.environment.WeatherResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.londonsmartcity.environment.WeatherRequest,
      com.londonsmartcity.environment.WeatherResponse> getGetWeatherMethod() {
    io.grpc.MethodDescriptor<com.londonsmartcity.environment.WeatherRequest, com.londonsmartcity.environment.WeatherResponse> getGetWeatherMethod;
    if ((getGetWeatherMethod = EnvironmentServiceGrpc.getGetWeatherMethod) == null) {
      synchronized (EnvironmentServiceGrpc.class) {
        if ((getGetWeatherMethod = EnvironmentServiceGrpc.getGetWeatherMethod) == null) {
          EnvironmentServiceGrpc.getGetWeatherMethod = getGetWeatherMethod =
              io.grpc.MethodDescriptor.<com.londonsmartcity.environment.WeatherRequest, com.londonsmartcity.environment.WeatherResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetWeather"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.environment.WeatherRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.environment.WeatherResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EnvironmentServiceMethodDescriptorSupplier("GetWeather"))
              .build();
        }
      }
    }
    return getGetWeatherMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.londonsmartcity.environment.EnvironmentalDataRequest,
      com.londonsmartcity.environment.EnvironmentalData> getStreamEnvironmentalDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamEnvironmentalData",
      requestType = com.londonsmartcity.environment.EnvironmentalDataRequest.class,
      responseType = com.londonsmartcity.environment.EnvironmentalData.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.londonsmartcity.environment.EnvironmentalDataRequest,
      com.londonsmartcity.environment.EnvironmentalData> getStreamEnvironmentalDataMethod() {
    io.grpc.MethodDescriptor<com.londonsmartcity.environment.EnvironmentalDataRequest, com.londonsmartcity.environment.EnvironmentalData> getStreamEnvironmentalDataMethod;
    if ((getStreamEnvironmentalDataMethod = EnvironmentServiceGrpc.getStreamEnvironmentalDataMethod) == null) {
      synchronized (EnvironmentServiceGrpc.class) {
        if ((getStreamEnvironmentalDataMethod = EnvironmentServiceGrpc.getStreamEnvironmentalDataMethod) == null) {
          EnvironmentServiceGrpc.getStreamEnvironmentalDataMethod = getStreamEnvironmentalDataMethod =
              io.grpc.MethodDescriptor.<com.londonsmartcity.environment.EnvironmentalDataRequest, com.londonsmartcity.environment.EnvironmentalData>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StreamEnvironmentalData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.environment.EnvironmentalDataRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.environment.EnvironmentalData.getDefaultInstance()))
              .setSchemaDescriptor(new EnvironmentServiceMethodDescriptorSupplier("StreamEnvironmentalData"))
              .build();
        }
      }
    }
    return getStreamEnvironmentalDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.londonsmartcity.environment.AirQualityAlert,
      com.londonsmartcity.environment.AlertResponse> getReportAirQualityAlertMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReportAirQualityAlert",
      requestType = com.londonsmartcity.environment.AirQualityAlert.class,
      responseType = com.londonsmartcity.environment.AlertResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.londonsmartcity.environment.AirQualityAlert,
      com.londonsmartcity.environment.AlertResponse> getReportAirQualityAlertMethod() {
    io.grpc.MethodDescriptor<com.londonsmartcity.environment.AirQualityAlert, com.londonsmartcity.environment.AlertResponse> getReportAirQualityAlertMethod;
    if ((getReportAirQualityAlertMethod = EnvironmentServiceGrpc.getReportAirQualityAlertMethod) == null) {
      synchronized (EnvironmentServiceGrpc.class) {
        if ((getReportAirQualityAlertMethod = EnvironmentServiceGrpc.getReportAirQualityAlertMethod) == null) {
          EnvironmentServiceGrpc.getReportAirQualityAlertMethod = getReportAirQualityAlertMethod =
              io.grpc.MethodDescriptor.<com.londonsmartcity.environment.AirQualityAlert, com.londonsmartcity.environment.AlertResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReportAirQualityAlert"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.environment.AirQualityAlert.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.environment.AlertResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EnvironmentServiceMethodDescriptorSupplier("ReportAirQualityAlert"))
              .build();
        }
      }
    }
    return getReportAirQualityAlertMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.londonsmartcity.environment.HistoricalDataRequest,
      com.londonsmartcity.environment.HistoricalDataResponse> getGetHistoricalDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetHistoricalData",
      requestType = com.londonsmartcity.environment.HistoricalDataRequest.class,
      responseType = com.londonsmartcity.environment.HistoricalDataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.londonsmartcity.environment.HistoricalDataRequest,
      com.londonsmartcity.environment.HistoricalDataResponse> getGetHistoricalDataMethod() {
    io.grpc.MethodDescriptor<com.londonsmartcity.environment.HistoricalDataRequest, com.londonsmartcity.environment.HistoricalDataResponse> getGetHistoricalDataMethod;
    if ((getGetHistoricalDataMethod = EnvironmentServiceGrpc.getGetHistoricalDataMethod) == null) {
      synchronized (EnvironmentServiceGrpc.class) {
        if ((getGetHistoricalDataMethod = EnvironmentServiceGrpc.getGetHistoricalDataMethod) == null) {
          EnvironmentServiceGrpc.getGetHistoricalDataMethod = getGetHistoricalDataMethod =
              io.grpc.MethodDescriptor.<com.londonsmartcity.environment.HistoricalDataRequest, com.londonsmartcity.environment.HistoricalDataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetHistoricalData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.environment.HistoricalDataRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.londonsmartcity.environment.HistoricalDataResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EnvironmentServiceMethodDescriptorSupplier("GetHistoricalData"))
              .build();
        }
      }
    }
    return getGetHistoricalDataMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EnvironmentServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EnvironmentServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EnvironmentServiceStub>() {
        @java.lang.Override
        public EnvironmentServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EnvironmentServiceStub(channel, callOptions);
        }
      };
    return EnvironmentServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EnvironmentServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EnvironmentServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EnvironmentServiceBlockingStub>() {
        @java.lang.Override
        public EnvironmentServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EnvironmentServiceBlockingStub(channel, callOptions);
        }
      };
    return EnvironmentServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EnvironmentServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EnvironmentServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EnvironmentServiceFutureStub>() {
        @java.lang.Override
        public EnvironmentServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EnvironmentServiceFutureStub(channel, callOptions);
        }
      };
    return EnvironmentServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Environment Service
   * </pre>
   */
  public static abstract class EnvironmentServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Get current air quality data
     * </pre>
     */
    public void getAirQuality(com.londonsmartcity.environment.AirQualityRequest request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.environment.AirQualityResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAirQualityMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get noise level data
     * </pre>
     */
    public void getNoiseLevel(com.londonsmartcity.environment.NoiseLevelRequest request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.environment.NoiseLevelResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetNoiseLevelMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get weather data
     * </pre>
     */
    public void getWeather(com.londonsmartcity.environment.WeatherRequest request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.environment.WeatherResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetWeatherMethod(), responseObserver);
    }

    /**
     * <pre>
     * Stream real-time environmental data
     * </pre>
     */
    public void streamEnvironmentalData(com.londonsmartcity.environment.EnvironmentalDataRequest request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.environment.EnvironmentalData> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStreamEnvironmentalDataMethod(), responseObserver);
    }

    /**
     * <pre>
     * Report air quality alert
     * </pre>
     */
    public void reportAirQualityAlert(com.londonsmartcity.environment.AirQualityAlert request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.environment.AlertResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReportAirQualityAlertMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get historical environmental data
     * </pre>
     */
    public void getHistoricalData(com.londonsmartcity.environment.HistoricalDataRequest request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.environment.HistoricalDataResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetHistoricalDataMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetAirQualityMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.londonsmartcity.environment.AirQualityRequest,
                com.londonsmartcity.environment.AirQualityResponse>(
                  this, METHODID_GET_AIR_QUALITY)))
          .addMethod(
            getGetNoiseLevelMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.londonsmartcity.environment.NoiseLevelRequest,
                com.londonsmartcity.environment.NoiseLevelResponse>(
                  this, METHODID_GET_NOISE_LEVEL)))
          .addMethod(
            getGetWeatherMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.londonsmartcity.environment.WeatherRequest,
                com.londonsmartcity.environment.WeatherResponse>(
                  this, METHODID_GET_WEATHER)))
          .addMethod(
            getStreamEnvironmentalDataMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                com.londonsmartcity.environment.EnvironmentalDataRequest,
                com.londonsmartcity.environment.EnvironmentalData>(
                  this, METHODID_STREAM_ENVIRONMENTAL_DATA)))
          .addMethod(
            getReportAirQualityAlertMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.londonsmartcity.environment.AirQualityAlert,
                com.londonsmartcity.environment.AlertResponse>(
                  this, METHODID_REPORT_AIR_QUALITY_ALERT)))
          .addMethod(
            getGetHistoricalDataMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.londonsmartcity.environment.HistoricalDataRequest,
                com.londonsmartcity.environment.HistoricalDataResponse>(
                  this, METHODID_GET_HISTORICAL_DATA)))
          .build();
    }
  }

  /**
   * <pre>
   * Environment Service
   * </pre>
   */
  public static final class EnvironmentServiceStub extends io.grpc.stub.AbstractAsyncStub<EnvironmentServiceStub> {
    private EnvironmentServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnvironmentServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EnvironmentServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Get current air quality data
     * </pre>
     */
    public void getAirQuality(com.londonsmartcity.environment.AirQualityRequest request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.environment.AirQualityResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAirQualityMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get noise level data
     * </pre>
     */
    public void getNoiseLevel(com.londonsmartcity.environment.NoiseLevelRequest request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.environment.NoiseLevelResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetNoiseLevelMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get weather data
     * </pre>
     */
    public void getWeather(com.londonsmartcity.environment.WeatherRequest request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.environment.WeatherResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetWeatherMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Stream real-time environmental data
     * </pre>
     */
    public void streamEnvironmentalData(com.londonsmartcity.environment.EnvironmentalDataRequest request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.environment.EnvironmentalData> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getStreamEnvironmentalDataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Report air quality alert
     * </pre>
     */
    public void reportAirQualityAlert(com.londonsmartcity.environment.AirQualityAlert request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.environment.AlertResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReportAirQualityAlertMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get historical environmental data
     * </pre>
     */
    public void getHistoricalData(com.londonsmartcity.environment.HistoricalDataRequest request,
        io.grpc.stub.StreamObserver<com.londonsmartcity.environment.HistoricalDataResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetHistoricalDataMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Environment Service
   * </pre>
   */
  public static final class EnvironmentServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<EnvironmentServiceBlockingStub> {
    private EnvironmentServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnvironmentServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EnvironmentServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Get current air quality data
     * </pre>
     */
    public com.londonsmartcity.environment.AirQualityResponse getAirQuality(com.londonsmartcity.environment.AirQualityRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAirQualityMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get noise level data
     * </pre>
     */
    public com.londonsmartcity.environment.NoiseLevelResponse getNoiseLevel(com.londonsmartcity.environment.NoiseLevelRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetNoiseLevelMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get weather data
     * </pre>
     */
    public com.londonsmartcity.environment.WeatherResponse getWeather(com.londonsmartcity.environment.WeatherRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetWeatherMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Stream real-time environmental data
     * </pre>
     */
    public java.util.Iterator<com.londonsmartcity.environment.EnvironmentalData> streamEnvironmentalData(
        com.londonsmartcity.environment.EnvironmentalDataRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getStreamEnvironmentalDataMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Report air quality alert
     * </pre>
     */
    public com.londonsmartcity.environment.AlertResponse reportAirQualityAlert(com.londonsmartcity.environment.AirQualityAlert request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReportAirQualityAlertMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get historical environmental data
     * </pre>
     */
    public com.londonsmartcity.environment.HistoricalDataResponse getHistoricalData(com.londonsmartcity.environment.HistoricalDataRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetHistoricalDataMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Environment Service
   * </pre>
   */
  public static final class EnvironmentServiceFutureStub extends io.grpc.stub.AbstractFutureStub<EnvironmentServiceFutureStub> {
    private EnvironmentServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnvironmentServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EnvironmentServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Get current air quality data
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.londonsmartcity.environment.AirQualityResponse> getAirQuality(
        com.londonsmartcity.environment.AirQualityRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAirQualityMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get noise level data
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.londonsmartcity.environment.NoiseLevelResponse> getNoiseLevel(
        com.londonsmartcity.environment.NoiseLevelRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetNoiseLevelMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get weather data
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.londonsmartcity.environment.WeatherResponse> getWeather(
        com.londonsmartcity.environment.WeatherRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetWeatherMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Report air quality alert
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.londonsmartcity.environment.AlertResponse> reportAirQualityAlert(
        com.londonsmartcity.environment.AirQualityAlert request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReportAirQualityAlertMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get historical environmental data
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.londonsmartcity.environment.HistoricalDataResponse> getHistoricalData(
        com.londonsmartcity.environment.HistoricalDataRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetHistoricalDataMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_AIR_QUALITY = 0;
  private static final int METHODID_GET_NOISE_LEVEL = 1;
  private static final int METHODID_GET_WEATHER = 2;
  private static final int METHODID_STREAM_ENVIRONMENTAL_DATA = 3;
  private static final int METHODID_REPORT_AIR_QUALITY_ALERT = 4;
  private static final int METHODID_GET_HISTORICAL_DATA = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EnvironmentServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(EnvironmentServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_AIR_QUALITY:
          serviceImpl.getAirQuality((com.londonsmartcity.environment.AirQualityRequest) request,
              (io.grpc.stub.StreamObserver<com.londonsmartcity.environment.AirQualityResponse>) responseObserver);
          break;
        case METHODID_GET_NOISE_LEVEL:
          serviceImpl.getNoiseLevel((com.londonsmartcity.environment.NoiseLevelRequest) request,
              (io.grpc.stub.StreamObserver<com.londonsmartcity.environment.NoiseLevelResponse>) responseObserver);
          break;
        case METHODID_GET_WEATHER:
          serviceImpl.getWeather((com.londonsmartcity.environment.WeatherRequest) request,
              (io.grpc.stub.StreamObserver<com.londonsmartcity.environment.WeatherResponse>) responseObserver);
          break;
        case METHODID_STREAM_ENVIRONMENTAL_DATA:
          serviceImpl.streamEnvironmentalData((com.londonsmartcity.environment.EnvironmentalDataRequest) request,
              (io.grpc.stub.StreamObserver<com.londonsmartcity.environment.EnvironmentalData>) responseObserver);
          break;
        case METHODID_REPORT_AIR_QUALITY_ALERT:
          serviceImpl.reportAirQualityAlert((com.londonsmartcity.environment.AirQualityAlert) request,
              (io.grpc.stub.StreamObserver<com.londonsmartcity.environment.AlertResponse>) responseObserver);
          break;
        case METHODID_GET_HISTORICAL_DATA:
          serviceImpl.getHistoricalData((com.londonsmartcity.environment.HistoricalDataRequest) request,
              (io.grpc.stub.StreamObserver<com.londonsmartcity.environment.HistoricalDataResponse>) responseObserver);
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

  private static abstract class EnvironmentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EnvironmentServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.londonsmartcity.environment.EnvironmentServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EnvironmentService");
    }
  }

  private static final class EnvironmentServiceFileDescriptorSupplier
      extends EnvironmentServiceBaseDescriptorSupplier {
    EnvironmentServiceFileDescriptorSupplier() {}
  }

  private static final class EnvironmentServiceMethodDescriptorSupplier
      extends EnvironmentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    EnvironmentServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (EnvironmentServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EnvironmentServiceFileDescriptorSupplier())
              .addMethod(getGetAirQualityMethod())
              .addMethod(getGetNoiseLevelMethod())
              .addMethod(getGetWeatherMethod())
              .addMethod(getStreamEnvironmentalDataMethod())
              .addMethod(getReportAirQualityAlertMethod())
              .addMethod(getGetHistoricalDataMethod())
              .build();
        }
      }
    }
    return result;
  }
}
