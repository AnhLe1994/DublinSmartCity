// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: transport_service.proto

package com.londonsmartcity.transport;

public interface DisruptionReportOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.londonsmartcity.transport.DisruptionReport)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string line_id = 1;</code>
   * @return The lineId.
   */
  java.lang.String getLineId();
  /**
   * <code>string line_id = 1;</code>
   * @return The bytes for lineId.
   */
  com.google.protobuf.ByteString
      getLineIdBytes();

  /**
   * <code>string description = 2;</code>
   * @return The description.
   */
  java.lang.String getDescription();
  /**
   * <code>string description = 2;</code>
   * @return The bytes for description.
   */
  com.google.protobuf.ByteString
      getDescriptionBytes();

  /**
   * <code>string severity = 3;</code>
   * @return The severity.
   */
  java.lang.String getSeverity();
  /**
   * <code>string severity = 3;</code>
   * @return The bytes for severity.
   */
  com.google.protobuf.ByteString
      getSeverityBytes();
}
