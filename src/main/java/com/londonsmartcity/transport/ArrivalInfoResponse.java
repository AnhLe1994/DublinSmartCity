// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: transport_service.proto

package com.londonsmartcity.transport;

/**
 * Protobuf type {@code com.londonsmartcity.transport.ArrivalInfoResponse}
 */
public final class ArrivalInfoResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.londonsmartcity.transport.ArrivalInfoResponse)
    ArrivalInfoResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ArrivalInfoResponse.newBuilder() to construct.
  private ArrivalInfoResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ArrivalInfoResponse() {
    stopId_ = "";
    lineId_ = "";
    arrivals_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ArrivalInfoResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ArrivalInfoResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
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
          case 26: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              arrivals_ = new java.util.ArrayList<com.londonsmartcity.transport.ArrivalInfo>();
              mutable_bitField0_ |= 0x00000001;
            }
            arrivals_.add(
                input.readMessage(com.londonsmartcity.transport.ArrivalInfo.parser(), extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        arrivals_ = java.util.Collections.unmodifiableList(arrivals_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.londonsmartcity.transport.TransportServiceProto.internal_static_com_londonsmartcity_transport_ArrivalInfoResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.londonsmartcity.transport.TransportServiceProto.internal_static_com_londonsmartcity_transport_ArrivalInfoResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.londonsmartcity.transport.ArrivalInfoResponse.class, com.londonsmartcity.transport.ArrivalInfoResponse.Builder.class);
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

  public static final int ARRIVALS_FIELD_NUMBER = 3;
  private java.util.List<com.londonsmartcity.transport.ArrivalInfo> arrivals_;
  /**
   * <code>repeated .com.londonsmartcity.transport.ArrivalInfo arrivals = 3;</code>
   */
  @java.lang.Override
  public java.util.List<com.londonsmartcity.transport.ArrivalInfo> getArrivalsList() {
    return arrivals_;
  }
  /**
   * <code>repeated .com.londonsmartcity.transport.ArrivalInfo arrivals = 3;</code>
   */
  @java.lang.Override
  public java.util.List<? extends com.londonsmartcity.transport.ArrivalInfoOrBuilder> 
      getArrivalsOrBuilderList() {
    return arrivals_;
  }
  /**
   * <code>repeated .com.londonsmartcity.transport.ArrivalInfo arrivals = 3;</code>
   */
  @java.lang.Override
  public int getArrivalsCount() {
    return arrivals_.size();
  }
  /**
   * <code>repeated .com.londonsmartcity.transport.ArrivalInfo arrivals = 3;</code>
   */
  @java.lang.Override
  public com.londonsmartcity.transport.ArrivalInfo getArrivals(int index) {
    return arrivals_.get(index);
  }
  /**
   * <code>repeated .com.londonsmartcity.transport.ArrivalInfo arrivals = 3;</code>
   */
  @java.lang.Override
  public com.londonsmartcity.transport.ArrivalInfoOrBuilder getArrivalsOrBuilder(
      int index) {
    return arrivals_.get(index);
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
    for (int i = 0; i < arrivals_.size(); i++) {
      output.writeMessage(3, arrivals_.get(i));
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
    for (int i = 0; i < arrivals_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, arrivals_.get(i));
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
    if (!(obj instanceof com.londonsmartcity.transport.ArrivalInfoResponse)) {
      return super.equals(obj);
    }
    com.londonsmartcity.transport.ArrivalInfoResponse other = (com.londonsmartcity.transport.ArrivalInfoResponse) obj;

    if (!getStopId()
        .equals(other.getStopId())) return false;
    if (!getLineId()
        .equals(other.getLineId())) return false;
    if (!getArrivalsList()
        .equals(other.getArrivalsList())) return false;
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
    if (getArrivalsCount() > 0) {
      hash = (37 * hash) + ARRIVALS_FIELD_NUMBER;
      hash = (53 * hash) + getArrivalsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.londonsmartcity.transport.ArrivalInfoResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.londonsmartcity.transport.ArrivalInfoResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.londonsmartcity.transport.ArrivalInfoResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.londonsmartcity.transport.ArrivalInfoResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.londonsmartcity.transport.ArrivalInfoResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.londonsmartcity.transport.ArrivalInfoResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.londonsmartcity.transport.ArrivalInfoResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.londonsmartcity.transport.ArrivalInfoResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.londonsmartcity.transport.ArrivalInfoResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.londonsmartcity.transport.ArrivalInfoResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.londonsmartcity.transport.ArrivalInfoResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.londonsmartcity.transport.ArrivalInfoResponse parseFrom(
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
  public static Builder newBuilder(com.londonsmartcity.transport.ArrivalInfoResponse prototype) {
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
   * Protobuf type {@code com.londonsmartcity.transport.ArrivalInfoResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.londonsmartcity.transport.ArrivalInfoResponse)
      com.londonsmartcity.transport.ArrivalInfoResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.londonsmartcity.transport.TransportServiceProto.internal_static_com_londonsmartcity_transport_ArrivalInfoResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.londonsmartcity.transport.TransportServiceProto.internal_static_com_londonsmartcity_transport_ArrivalInfoResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.londonsmartcity.transport.ArrivalInfoResponse.class, com.londonsmartcity.transport.ArrivalInfoResponse.Builder.class);
    }

    // Construct using com.londonsmartcity.transport.ArrivalInfoResponse.newBuilder()
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
        getArrivalsFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      stopId_ = "";

      lineId_ = "";

      if (arrivalsBuilder_ == null) {
        arrivals_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        arrivalsBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.londonsmartcity.transport.TransportServiceProto.internal_static_com_londonsmartcity_transport_ArrivalInfoResponse_descriptor;
    }

    @java.lang.Override
    public com.londonsmartcity.transport.ArrivalInfoResponse getDefaultInstanceForType() {
      return com.londonsmartcity.transport.ArrivalInfoResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.londonsmartcity.transport.ArrivalInfoResponse build() {
      com.londonsmartcity.transport.ArrivalInfoResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.londonsmartcity.transport.ArrivalInfoResponse buildPartial() {
      com.londonsmartcity.transport.ArrivalInfoResponse result = new com.londonsmartcity.transport.ArrivalInfoResponse(this);
      int from_bitField0_ = bitField0_;
      result.stopId_ = stopId_;
      result.lineId_ = lineId_;
      if (arrivalsBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          arrivals_ = java.util.Collections.unmodifiableList(arrivals_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.arrivals_ = arrivals_;
      } else {
        result.arrivals_ = arrivalsBuilder_.build();
      }
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
      if (other instanceof com.londonsmartcity.transport.ArrivalInfoResponse) {
        return mergeFrom((com.londonsmartcity.transport.ArrivalInfoResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.londonsmartcity.transport.ArrivalInfoResponse other) {
      if (other == com.londonsmartcity.transport.ArrivalInfoResponse.getDefaultInstance()) return this;
      if (!other.getStopId().isEmpty()) {
        stopId_ = other.stopId_;
        onChanged();
      }
      if (!other.getLineId().isEmpty()) {
        lineId_ = other.lineId_;
        onChanged();
      }
      if (arrivalsBuilder_ == null) {
        if (!other.arrivals_.isEmpty()) {
          if (arrivals_.isEmpty()) {
            arrivals_ = other.arrivals_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureArrivalsIsMutable();
            arrivals_.addAll(other.arrivals_);
          }
          onChanged();
        }
      } else {
        if (!other.arrivals_.isEmpty()) {
          if (arrivalsBuilder_.isEmpty()) {
            arrivalsBuilder_.dispose();
            arrivalsBuilder_ = null;
            arrivals_ = other.arrivals_;
            bitField0_ = (bitField0_ & ~0x00000001);
            arrivalsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getArrivalsFieldBuilder() : null;
          } else {
            arrivalsBuilder_.addAllMessages(other.arrivals_);
          }
        }
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
      com.londonsmartcity.transport.ArrivalInfoResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.londonsmartcity.transport.ArrivalInfoResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

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

    private java.util.List<com.londonsmartcity.transport.ArrivalInfo> arrivals_ =
      java.util.Collections.emptyList();
    private void ensureArrivalsIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        arrivals_ = new java.util.ArrayList<com.londonsmartcity.transport.ArrivalInfo>(arrivals_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.londonsmartcity.transport.ArrivalInfo, com.londonsmartcity.transport.ArrivalInfo.Builder, com.londonsmartcity.transport.ArrivalInfoOrBuilder> arrivalsBuilder_;

    /**
     * <code>repeated .com.londonsmartcity.transport.ArrivalInfo arrivals = 3;</code>
     */
    public java.util.List<com.londonsmartcity.transport.ArrivalInfo> getArrivalsList() {
      if (arrivalsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(arrivals_);
      } else {
        return arrivalsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .com.londonsmartcity.transport.ArrivalInfo arrivals = 3;</code>
     */
    public int getArrivalsCount() {
      if (arrivalsBuilder_ == null) {
        return arrivals_.size();
      } else {
        return arrivalsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .com.londonsmartcity.transport.ArrivalInfo arrivals = 3;</code>
     */
    public com.londonsmartcity.transport.ArrivalInfo getArrivals(int index) {
      if (arrivalsBuilder_ == null) {
        return arrivals_.get(index);
      } else {
        return arrivalsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .com.londonsmartcity.transport.ArrivalInfo arrivals = 3;</code>
     */
    public Builder setArrivals(
        int index, com.londonsmartcity.transport.ArrivalInfo value) {
      if (arrivalsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureArrivalsIsMutable();
        arrivals_.set(index, value);
        onChanged();
      } else {
        arrivalsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.londonsmartcity.transport.ArrivalInfo arrivals = 3;</code>
     */
    public Builder setArrivals(
        int index, com.londonsmartcity.transport.ArrivalInfo.Builder builderForValue) {
      if (arrivalsBuilder_ == null) {
        ensureArrivalsIsMutable();
        arrivals_.set(index, builderForValue.build());
        onChanged();
      } else {
        arrivalsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.londonsmartcity.transport.ArrivalInfo arrivals = 3;</code>
     */
    public Builder addArrivals(com.londonsmartcity.transport.ArrivalInfo value) {
      if (arrivalsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureArrivalsIsMutable();
        arrivals_.add(value);
        onChanged();
      } else {
        arrivalsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .com.londonsmartcity.transport.ArrivalInfo arrivals = 3;</code>
     */
    public Builder addArrivals(
        int index, com.londonsmartcity.transport.ArrivalInfo value) {
      if (arrivalsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureArrivalsIsMutable();
        arrivals_.add(index, value);
        onChanged();
      } else {
        arrivalsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.londonsmartcity.transport.ArrivalInfo arrivals = 3;</code>
     */
    public Builder addArrivals(
        com.londonsmartcity.transport.ArrivalInfo.Builder builderForValue) {
      if (arrivalsBuilder_ == null) {
        ensureArrivalsIsMutable();
        arrivals_.add(builderForValue.build());
        onChanged();
      } else {
        arrivalsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.londonsmartcity.transport.ArrivalInfo arrivals = 3;</code>
     */
    public Builder addArrivals(
        int index, com.londonsmartcity.transport.ArrivalInfo.Builder builderForValue) {
      if (arrivalsBuilder_ == null) {
        ensureArrivalsIsMutable();
        arrivals_.add(index, builderForValue.build());
        onChanged();
      } else {
        arrivalsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.londonsmartcity.transport.ArrivalInfo arrivals = 3;</code>
     */
    public Builder addAllArrivals(
        java.lang.Iterable<? extends com.londonsmartcity.transport.ArrivalInfo> values) {
      if (arrivalsBuilder_ == null) {
        ensureArrivalsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, arrivals_);
        onChanged();
      } else {
        arrivalsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .com.londonsmartcity.transport.ArrivalInfo arrivals = 3;</code>
     */
    public Builder clearArrivals() {
      if (arrivalsBuilder_ == null) {
        arrivals_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        arrivalsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .com.londonsmartcity.transport.ArrivalInfo arrivals = 3;</code>
     */
    public Builder removeArrivals(int index) {
      if (arrivalsBuilder_ == null) {
        ensureArrivalsIsMutable();
        arrivals_.remove(index);
        onChanged();
      } else {
        arrivalsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .com.londonsmartcity.transport.ArrivalInfo arrivals = 3;</code>
     */
    public com.londonsmartcity.transport.ArrivalInfo.Builder getArrivalsBuilder(
        int index) {
      return getArrivalsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .com.londonsmartcity.transport.ArrivalInfo arrivals = 3;</code>
     */
    public com.londonsmartcity.transport.ArrivalInfoOrBuilder getArrivalsOrBuilder(
        int index) {
      if (arrivalsBuilder_ == null) {
        return arrivals_.get(index);  } else {
        return arrivalsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .com.londonsmartcity.transport.ArrivalInfo arrivals = 3;</code>
     */
    public java.util.List<? extends com.londonsmartcity.transport.ArrivalInfoOrBuilder> 
         getArrivalsOrBuilderList() {
      if (arrivalsBuilder_ != null) {
        return arrivalsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(arrivals_);
      }
    }
    /**
     * <code>repeated .com.londonsmartcity.transport.ArrivalInfo arrivals = 3;</code>
     */
    public com.londonsmartcity.transport.ArrivalInfo.Builder addArrivalsBuilder() {
      return getArrivalsFieldBuilder().addBuilder(
          com.londonsmartcity.transport.ArrivalInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .com.londonsmartcity.transport.ArrivalInfo arrivals = 3;</code>
     */
    public com.londonsmartcity.transport.ArrivalInfo.Builder addArrivalsBuilder(
        int index) {
      return getArrivalsFieldBuilder().addBuilder(
          index, com.londonsmartcity.transport.ArrivalInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .com.londonsmartcity.transport.ArrivalInfo arrivals = 3;</code>
     */
    public java.util.List<com.londonsmartcity.transport.ArrivalInfo.Builder> 
         getArrivalsBuilderList() {
      return getArrivalsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.londonsmartcity.transport.ArrivalInfo, com.londonsmartcity.transport.ArrivalInfo.Builder, com.londonsmartcity.transport.ArrivalInfoOrBuilder> 
        getArrivalsFieldBuilder() {
      if (arrivalsBuilder_ == null) {
        arrivalsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.londonsmartcity.transport.ArrivalInfo, com.londonsmartcity.transport.ArrivalInfo.Builder, com.londonsmartcity.transport.ArrivalInfoOrBuilder>(
                arrivals_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        arrivals_ = null;
      }
      return arrivalsBuilder_;
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


    // @@protoc_insertion_point(builder_scope:com.londonsmartcity.transport.ArrivalInfoResponse)
  }

  // @@protoc_insertion_point(class_scope:com.londonsmartcity.transport.ArrivalInfoResponse)
  private static final com.londonsmartcity.transport.ArrivalInfoResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.londonsmartcity.transport.ArrivalInfoResponse();
  }

  public static com.londonsmartcity.transport.ArrivalInfoResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ArrivalInfoResponse>
      PARSER = new com.google.protobuf.AbstractParser<ArrivalInfoResponse>() {
    @java.lang.Override
    public ArrivalInfoResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ArrivalInfoResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ArrivalInfoResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ArrivalInfoResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.londonsmartcity.transport.ArrivalInfoResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

