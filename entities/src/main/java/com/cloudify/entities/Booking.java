package com.cloudify.entities;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;


@Schema(description = "Booking information")
public class Booking implements Serializable {

    @Schema(description = "bookingId", example = "BK987654")
    private String bookingId;

    @Schema(description = "Flight")
    private Flight flight;

    @Schema(description = "List<Passenger>")
    private List<Passenger> passengers;

    @Schema(description = "totalPrice", example = "250.50")
    private double totalPrice;

    public Booking() {}

    // Parameterized constructor
    public Booking(String bookingId, Flight flight, List<Passenger> passengers, double totalPrice) {
        this.bookingId = bookingId;
        this.flight = flight;
        this.passengers = passengers;
        this.totalPrice = totalPrice;
    }

    public Booking(String bookingId, Flight flight) {
        this.bookingId = bookingId;
        this.flight = flight;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
