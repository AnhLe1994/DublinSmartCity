// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: environment_service.proto

package com.londonsmartcity.environment;

public interface EnvironmentalDataOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.londonsmartcity.environment.EnvironmentalData)
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
   * <code>double pm25 = 2;</code>
   * @return The pm25.
   */
  double getPm25();

  /**
   * <code>double pm10 = 3;</code>
   * @return The pm10.
   */
  double getPm10();

  /**
   * <code>double no2 = 4;</code>
   * @return The no2.
   */
  double getNo2();

  /**
   * <code>double o3 = 5;</code>
   * @return The o3.
   */
  double getO3();

  /**
   * <code>double decibels = 6;</code>
   * @return The decibels.
   */
  double getDecibels();

  /**
   * <code>double temperature = 7;</code>
   * @return The temperature.
   */
  double getTemperature();

  /**
   * <code>double humidity = 8;</code>
   * @return The humidity.
   */
  double getHumidity();

  /**
   * <code>double wind_speed = 9;</code>
   * @return The windSpeed.
   */
  double getWindSpeed();

  /**
   * <code>string weather_condition = 10;</code>
   * @return The weatherCondition.
   */
  java.lang.String getWeatherCondition();
  /**
   * <code>string weather_condition = 10;</code>
   * @return The bytes for weatherCondition.
   */
  com.google.protobuf.ByteString
      getWeatherConditionBytes();

  /**
   * <code>int64 timestamp = 11;</code>
   * @return The timestamp.
   */
  long getTimestamp();
}
