// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: environment_service.proto

package com.londonsmartcity.environment;

public interface NoiseLevelResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.londonsmartcity.environment.NoiseLevelResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string location_id = 1;</code>
   * @return The locationId.
   */
  java.lang.String getLocationId();
  /**
   * <code>string location_id = 1;</code>
   * @return The bytes for locationId.
   */
  com.google.protobuf.ByteString
      getLocationIdBytes();

  /**
   * <code>double decibels = 2;</code>
   * @return The decibels.
   */
  double getDecibels();

  /**
   * <code>string noise_level = 3;</code>
   * @return The noiseLevel.
   */
  java.lang.String getNoiseLevel();
  /**
   * <code>string noise_level = 3;</code>
   * @return The bytes for noiseLevel.
   */
  com.google.protobuf.ByteString
      getNoiseLevelBytes();

  /**
   * <code>int64 timestamp = 4;</code>
   * @return The timestamp.
   */
  long getTimestamp();
}
