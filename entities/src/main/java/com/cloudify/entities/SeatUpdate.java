package com.cloudify.entities;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;

@Schema(description = "Details of seat updates for a flight")
public class SeatUpdate implements Serializable {

    @Schema(description = "Number of seats booked", example = "2")
    private Integer seatsBooked;

    @Schema(description = "Number of seats cancelled", example = "1")
    private Integer seatsCancelled;

    // Default constructor
    public SeatUpdate() {}

    // Parameterized constructor
    public SeatUpdate(Integer seatsBooked, Integer seatsCancelled) {
        this.seatsBooked = seatsBooked;
        this.seatsCancelled = seatsCancelled;
    }

    // Getters and Setters
    public Integer getSeatsBooked() {
        return seatsBooked;
    }

    public void setSeatsBooked(Integer seatsBooked) {
        this.seatsBooked = seatsBooked;
    }

    public Integer getSeatsCancelled() {
        return seatsCancelled;
    }

    public void setSeatsCancelled(Integer seatsCancelled) {
        this.seatsCancelled = seatsCancelled;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "SeatUpdate{" +
                "seatsBooked=" + seatsBooked +
                ", seatsCancelled=" + seatsCancelled +
                '}';
    }
}
