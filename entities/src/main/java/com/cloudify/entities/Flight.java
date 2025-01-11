package com.cloudify.entities;

import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;
import java.time.LocalDateTime;

@org.hibernate.annotations.NamedQueries(value =
        {
                @org.hibernate.annotations.NamedQuery(
                        name = "Flight.getFlight",
                        query = "SELECT p FROM Flight p WHERE p.flightId  = :id"
                ),
                @org.hibernate.annotations.NamedQuery(
                        name = "Flight.deleteFlight",
                        query = "DELETE FROM Flight p WHERE p.flightId = :id"
                ),
                @org.hibernate.annotations.NamedQuery(
                        name = "Flight.updateFlight",
                        query = "UPDATE Flight p SET p.departureTime = :departureTime, p.duration = :duration WHERE p.flightId = :id"
                )
        }
)
@Entity
@Table(name = "flight")
@Schema(description = "Flight information")
public class Flight implements Serializable {

    @Schema(description = "Flight identifier", example = "AB1234")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flightId")
    private String flightId;

    @Column(name = "airline")
    @Schema(description = "airline", example = "Delta Airlines")
    private String airline;

    @Column(name = "origin")
    @Schema(description = "origin", example = "LAX")
    private String origin;

    @Column(name = "destination")
    @Schema(description = "destination", example = "JFK")
    private String destination;

    @Column(name = "departureTime")
    @Schema(description = "departureTime", example = "2024-11-20T08:00:00Z")
    private LocalDateTime departureTime;

    @Column(name = "arrivalTime")
    @Schema(description = "arrivalTime", example = "2024-11-20T08:00:00Z")
    private LocalDateTime arrivalTime;

    @Column(name = "price")
    @Schema(description = "price", example = "250.50")
    private double price;

    @Column(name = "duration")
    @Schema(description = "duration", example = "PT6H30M")
    private String duration;

    @Column(name = "maxSeats")
    @Schema(description = "maxSeats", example = "20")
    private Integer maxSeats;

    @Column(name = "travelClass")
    @Schema(description = "travelClass", example = "economy")
    private String travelClass;

    public  Flight() {}

    // Parameterized constructor
    public Flight(String flightId, String airline, String origin, String destination, LocalDateTime departureTime, LocalDateTime arrivalTime, double price, String duration, Integer maxSeats, String travelClass) {
        this.flightId = flightId;
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.duration = duration;
        this.maxSeats = maxSeats;
        this.travelClass = travelClass;
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

    public String getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(String travelClass) {
        this.travelClass = travelClass;
    }
}
