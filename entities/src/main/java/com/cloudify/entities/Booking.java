package com.cloudify.entities;

import java.io.Serializable;
import java.util.List;

public class Booking implements Serializable {
    private String bookingId;
    private Flight flight;
    private List<Passenger> passengers;
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
