// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: traffic_service.proto

package com.londonsmartcity.traffic;

public interface TrafficUpdateOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.londonsmartcity.traffic.TrafficUpdate)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string location = 1;</code>
   * @return The location.
   */
  java.lang.String getLocation();
  /**
   * <code>string location = 1;</code>
   * @return The bytes for location.
   */
  com.google.protobuf.ByteString
      getLocationBytes();

  /**
   * <code>int32 congestion_level = 2;</code>
   * @return The congestionLevel.
   */
  int getCongestionLevel();

  /**
   * <code>int32 average_speed = 3;</code>
   * @return The averageSpeed.
   */
  int getAverageSpeed();

  /**
   * <code>string status = 4;</code>
   * @return The status.
   */
  java.lang.String getStatus();
  /**
   * <code>string status = 4;</code>
   * @return The bytes for status.
   */
  com.google.protobuf.ByteString
      getStatusBytes();

  /**
   * <code>int64 timestamp = 5;</code>
   * @return The timestamp.
   */
  long getTimestamp();
}
