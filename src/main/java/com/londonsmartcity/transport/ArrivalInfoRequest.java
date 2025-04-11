// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: transport_service.proto

package com.londonsmartcity.transport;

/**
 * Protobuf type {@code com.londonsmartcity.transport.ArrivalInfoRequest}
 */
public final class ArrivalInfoRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.londonsmartcity.transport.ArrivalInfoRequest)
    ArrivalInfoRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ArrivalInfoRequest.newBuilder() to construct.
  private ArrivalInfoRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ArrivalInfoRequest() {
    stopId_ = "";
    lineId_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ArrivalInfoRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ArrivalInfoRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            stopId_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            lineId_ = s;
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (com.google.protobuf.UninitializedMessageException e) {
      throw e.asInvalidProtocolBufferException().setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.londonsmartcity.transport.TransportServiceProto.internal_static_com_londonsmartcity_transport_ArrivalInfoRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.londonsmartcity.transport.TransportServiceProto.internal_static_com_londonsmartcity_transport_ArrivalInfoRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.londonsmartcity.transport.ArrivalInfoRequest.class, com.londonsmartcity.transport.ArrivalInfoRequest.Builder.class);
  }

  public static final int STOP_ID_FIELD_NUMBER = 1;
  private volatile java.lang.Object stopId_;
  /**
   * <code>string stop_id = 1;</code>
   * @return The stopId.
   */
  @java.lang.Override
  public java.lang.String getStopId() {
    java.lang.Object ref = stopId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      stopId_ = s;
      return s;
    }
  }
  /**
   * <code>string stop_id = 1;</code>
   * @return The bytes for stopId.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getStopIdBytes() {
    java.lang.Object ref = stopId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      stopId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int LINE_ID_FIELD_NUMBER = 2;
  private volatile java.lang.Object lineId_;
  /**
   * <code>string line_id = 2;</code>
   * @return The lineId.
   */
  @java.lang.Override
  public java.lang.String getLineId() {
    java.lang.Object ref = lineId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      lineId_ = s;
      return s;
    }
  }
  /**
   * <code>string line_id = 2;</code>
   * @return The bytes for lineId.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getLineIdBytes() {
    java.lang.Object ref = lineId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      lineId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(stopId_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, stopId_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(lineId_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, lineId_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(stopId_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, stopId_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(lineId_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, lineId_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.londonsmartcity.transport.ArrivalInfoRequest)) {
      return super.equals(obj);
    }
    com.londonsmartcity.transport.ArrivalInfoRequest other = (com.londonsmartcity.transport.ArrivalInfoRequest) obj;

    if (!getStopId()
        .equals(other.getStopId())) return false;
    if (!getLineId()
        .equals(other.getLineId())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + STOP_ID_FIELD_NUMBER;
    hash = (53 * hash) + getStopId().hashCode();
    hash = (37 * hash) + LINE_ID_FIELD_NUMBER;
    hash = (53 * hash) + getLineId().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.londonsmartcity.transport.ArrivalInfoRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.londonsmartcity.transport.ArrivalInfoRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.londonsmartcity.transport.ArrivalInfoRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.londonsmartcity.transport.ArrivalInfoRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.londonsmartcity.transport.ArrivalInfoRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.londonsmartcity.transport.ArrivalInfoRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.londonsmartcity.transport.ArrivalInfoRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.londonsmartcity.transport.ArrivalInfoRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.londonsmartcity.transport.ArrivalInfoRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.londonsmartcity.transport.ArrivalInfoRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.londonsmartcity.transport.ArrivalInfoRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.londonsmartcity.transport.ArrivalInfoRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.londonsmartcity.transport.ArrivalInfoRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code com.londonsmartcity.transport.ArrivalInfoRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.londonsmartcity.transport.ArrivalInfoRequest)
      com.londonsmartcity.transport.ArrivalInfoRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.londonsmartcity.transport.TransportServiceProto.internal_static_com_londonsmartcity_transport_ArrivalInfoRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.londonsmartcity.transport.TransportServiceProto.internal_static_com_londonsmartcity_transport_ArrivalInfoRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.londonsmartcity.transport.ArrivalInfoRequest.class, com.londonsmartcity.transport.ArrivalInfoRequest.Builder.class);
    }

    // Construct using com.londonsmartcity.transport.ArrivalInfoRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      stopId_ = "";

      lineId_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.londonsmartcity.transport.TransportServiceProto.internal_static_com_londonsmartcity_transport_ArrivalInfoRequest_descriptor;
    }

    @java.lang.Override
    public com.londonsmartcity.transport.ArrivalInfoRequest getDefaultInstanceForType() {
      return com.londonsmartcity.transport.ArrivalInfoRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.londonsmartcity.transport.ArrivalInfoRequest build() {
      com.londonsmartcity.transport.ArrivalInfoRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.londonsmartcity.transport.ArrivalInfoRequest buildPartial() {
      com.londonsmartcity.transport.ArrivalInfoRequest result = new com.londonsmartcity.transport.ArrivalInfoRequest(this);
      result.stopId_ = stopId_;
      result.lineId_ = lineId_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.londonsmartcity.transport.ArrivalInfoRequest) {
        return mergeFrom((com.londonsmartcity.transport.ArrivalInfoRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.londonsmartcity.transport.ArrivalInfoRequest other) {
      if (other == com.londonsmartcity.transport.ArrivalInfoRequest.getDefaultInstance()) return this;
      if (!other.getStopId().isEmpty()) {
        stopId_ = other.stopId_;
        onChanged();
      }
      if (!other.getLineId().isEmpty()) {
        lineId_ = other.lineId_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.londonsmartcity.transport.ArrivalInfoRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.londonsmartcity.transport.ArrivalInfoRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object stopId_ = "";
    /**
     * <code>string stop_id = 1;</code>
     * @return The stopId.
     */
    public java.lang.String getStopId() {
      java.lang.Object ref = stopId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        stopId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string stop_id = 1;</code>
     * @return The bytes for stopId.
     */
    public com.google.protobuf.ByteString
        getStopIdBytes() {
      java.lang.Object ref = stopId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        stopId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string stop_id = 1;</code>
     * @param value The stopId to set.
     * @return This builder for chaining.
     */
    public Builder setStopId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      stopId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string stop_id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearStopId() {
      
      stopId_ = getDefaultInstance().getStopId();
      onChanged();
      return this;
    }
    /**
     * <code>string stop_id = 1;</code>
     * @param value The bytes for stopId to set.
     * @return This builder for chaining.
     */
    public Builder setStopIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      stopId_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object lineId_ = "";
    /**
     * <code>string line_id = 2;</code>
     * @return The lineId.
     */
    public java.lang.String getLineId() {
      java.lang.Object ref = lineId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        lineId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string line_id = 2;</code>
     * @return The bytes for lineId.
     */
    public com.google.protobuf.ByteString
        getLineIdBytes() {
      java.lang.Object ref = lineId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        lineId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string line_id = 2;</code>
     * @param value The lineId to set.
     * @return This builder for chaining.
     */
    public Builder setLineId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      lineId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string line_id = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearLineId() {
      
      lineId_ = getDefaultInstance().getLineId();
      onChanged();
      return this;
    }
    /**
     * <code>string line_id = 2;</code>
     * @param value The bytes for lineId to set.
     * @return This builder for chaining.
     */
    public Builder setLineIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      lineId_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.londonsmartcity.transport.ArrivalInfoRequest)
  }

  // @@protoc_insertion_point(class_scope:com.londonsmartcity.transport.ArrivalInfoRequest)
  private static final com.londonsmartcity.transport.ArrivalInfoRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.londonsmartcity.transport.ArrivalInfoRequest();
  }

  public static com.londonsmartcity.transport.ArrivalInfoRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ArrivalInfoRequest>
      PARSER = new com.google.protobuf.AbstractParser<ArrivalInfoRequest>() {
    @java.lang.Override
    public ArrivalInfoRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ArrivalInfoRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ArrivalInfoRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ArrivalInfoRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.londonsmartcity.transport.ArrivalInfoRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

