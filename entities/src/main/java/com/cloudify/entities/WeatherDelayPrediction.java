package com.cloudify.entities;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Weather and delay prediction details")
public class WeatherDelayPrediction {

    @Schema(description = "Unique identifier for the flight", example = "AB1234", required = true)
    private String flightId;

    @Schema(description = "Weather forecast for the flight", example = "Sunny", required = true)
    private String weatherForecast;

    @Schema(description = "Probability of delay (0.0 to 1.0)", example = "0.1", required = true)
    private double delayProbability;

    // Getters and Setters

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getWeatherForecast() {
        return weatherForecast;
    }

    public void setWeatherForecast(String weatherForecast) {
        this.weatherForecast = weatherForecast;
    }

    public double getDelayProbability() {
        return delayProbability;
    }

    public void setDelayProbability(double delayProbability) {
        this.delayProbability = delayProbability;
    }
}
