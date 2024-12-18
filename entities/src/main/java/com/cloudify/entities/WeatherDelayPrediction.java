package com.cloudify.entities;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Weather and delay prediction details")
public class WeatherDelayPrediction {

    @Schema(description = "Unique identifier for the flight", example = "AB1234", required = true)
    private String flightId;

    @Schema(description = "Weather forecast for the flight", example = "Sunny", required = true)
    private String weatherForecast;

    @Schema(description = "Probability of delay (0.0 to 100.0)", example = "10", required = true)
    private String delayProbability;

    @Schema(description = "Final destination of the flight", example = "Los Angeles", required = true)
    private String finalDestination;

    @Schema(description = "Final destination of the flight", example = "Los Angeles", required = true)
    private String originalDestination;

    @Schema(description = "Weather forecast for the final destination", example = "Clear", required = true)
    private String finalDestinationWeatherForecast;

    @Schema(description = "Probability of delay for the final destination (0.0 to 100.0)", example = "20", required = true)
    private String finalDestinationDelayProbability;

    @Schema(description = "Wind speed at the flight's origin location (km/h)", example = "15.0")
    private double windSpeed;

    @Schema(description = "Visibility at the flight's origin location (km)", example = "10.0")
    private double visibility;

    @Schema(description = "Humidity at the flight's origin location (%)", example = "60.0")
    private double humidity;

    @Schema(description = "Temperature at the flight's origin location (°C)", example = "22.5")
    private double temperature;

    @Schema(description = "Final probability of delay (average of origin and destination)", example = "15.0")
    private String finalProbabilityOfDelay;

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

    public String getDelayProbability() {
        return delayProbability;
    }

    public void setDelayProbability(String delayProbability) {
        this.delayProbability = delayProbability;
    }

    public String getFinalDestination() {
        return finalDestination;
    }

    public void setFinalDestination(String finalDestination) {
        this.finalDestination = finalDestination;
    }

    public String getOriginDestination() {
        return originalDestination;
    }

    public void setOriginDestination(String originalDestination) { this.originalDestination = originalDestination; }

    public String getFinalDestinationWeatherForecast() {
        return finalDestinationWeatherForecast;
    }

    public void setFinalDestinationWeatherForecast(String finalDestinationWeatherForecast) {
        this.finalDestinationWeatherForecast = finalDestinationWeatherForecast;
    }

    public String getFinalDestinationDelayProbability() {
        return finalDestinationDelayProbability;
    }

    public void setFinalDestinationDelayProbability(String finalDestinationDelayProbability) {
        this.finalDestinationDelayProbability = finalDestinationDelayProbability;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getFinalProbabilityOfDelay() {
        return finalProbabilityOfDelay;
    }

    public void setFinalProbabilityOfDelay(String finalProbabilityOfDelay) {
        this.finalProbabilityOfDelay = finalProbabilityOfDelay;
    }
}
