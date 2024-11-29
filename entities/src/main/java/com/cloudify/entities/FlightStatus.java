package com.cloudify.entities;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Flight status information")
public class FlightStatus {

    @Schema(description = "Unique identifier for the flight", example = "AB1234")
    private String flightId;

    @Schema(description = "Current status of the flight", example = "onTime")
    private String status;

    @Schema(description = "Estimated time of arrival", example = "2024-11-20T08:00:00Z")
    private String estimatedArrivalTime;

    @Schema(description = "Current location of the flight", example = "40.7128N, 74.0060W")
    private String currentLocation;

    // Constructor
    public FlightStatus() {}

    // Getters and setters
    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEstimatedArrivalTime() {
        return estimatedArrivalTime;
    }

    public void setEstimatedArrivalTime(String estimatedArrivalTime) {
        this.estimatedArrivalTime = estimatedArrivalTime;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }
}
