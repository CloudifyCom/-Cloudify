package com.cloudify.entities;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;

@Schema(description = "Seat availability details")
public class SeatAvailability implements Serializable {

    @Schema(description = "Flight ID", example = "ABC123")
    private String flightId;

    @Schema(description = "Number of seats currently available", example = "45")
    private Integer availableSeats;

    @Schema(description = "Total number of seats on the flight", example = "180")
    private Integer totalSeats;

    // Default constructor
    public SeatAvailability() {}

    // Parameterized constructor
    public SeatAvailability(String flightId, Integer availableSeats, Integer totalSeats) {
        this.flightId = flightId;
        this.availableSeats = availableSeats;
        this.totalSeats = totalSeats;
    }

    // Getters and Setters
    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "SeatAvailability{" +
                "flightId='" + flightId + '\'' +
                ", availableSeats=" + availableSeats +
                ", totalSeats=" + totalSeats +
                '}';
    }
}
