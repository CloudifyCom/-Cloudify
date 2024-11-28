package com.cloudify.entities;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;
import java.time.LocalDateTime;

@Schema(description = "Flight information")
public class Flight implements Serializable {

    @Schema(description = "Flight identifier", example = "AB1234")
    private String flightId;

    @Schema(description = "airline", example = "Delta Airlines")
    private String airline;

    @Schema(description = "origin", example = "LAX")
    private String origin;

    @Schema(description = "destination", example = "JFK")
    private String destination;

    @Schema(description = "departureTime", example = "2024-11-20T08:00:00Z")
    private LocalDateTime departureTime;

    @Schema(description = "arrivalTime", example = "2024-11-20T08:00:00Z")
    private LocalDateTime arrivalTime;

    @Schema(description = "price", example = "250.50")
    private double price;

    @Schema(description = "duration", example = "PT6H30M")
    private String duration;

    @Schema(description = "maxSeats", example = "20")
    private Integer maxSeats;

    public  Flight() {}

    // Parameterized constructor
    public Flight(String flightId, String airline, String origin, String destination, LocalDateTime departureTime, LocalDateTime arrivalTime, double price, String duration, Integer maxSeats) {
        this.flightId = flightId;
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.duration = duration;
        this.maxSeats = maxSeats;
    }

    public Flight(String flightId, String airline, String origin, String destination, LocalDateTime arrivalTime, double price, Integer maxSeats) {
        this.flightId = flightId;
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.maxSeats = maxSeats;
    }



    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(Integer maxSeats) {
        this.maxSeats = maxSeats;
    }
}
