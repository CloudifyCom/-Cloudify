package com.cloudify.entities;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;

@Schema(description = "Response containing available flights based on search criteria")
public class FlightSearchResponse {

    @Schema(description = "List of available flights matching the search criteria")
    private List<Flight> availableFlights;

    @Schema(description = "Message indicating the status or result of the search")
    private String message;

    // Getters and Setters
    public List<Flight> getAvailableFlights() {
        return availableFlights;
    }

    public void setAvailableFlights(List<Flight> availableFlights) {
        this.availableFlights = availableFlights;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Schema(description = "Flight details")
    public static class Flight {

        @Schema(description = "Unique identifier for the flight", example = "AB1234", required = true)
        private String flightId;

        @Schema(description = "Origin airport code", example = "LAX", required = true)
        private String origin;

        @Schema(description = "Destination airport code", example = "JFK", required = true)
        private String destination;

        @Schema(description = "Departure date of the flight", example = "2024-11-20", required = true)
        private String departureDate;

        @Schema(description = "Available seats on the flight", example = "150", required = true)
        private int availableSeats;

        @Schema(description = "Price for the flight", example = "299.99", required = true)
        private double price;

        @Schema(description = "Class of the flight", example = "economy", required = false)
        private String travelClass;

        @Schema(description = "Airline name", example = "Delta Airlines", required = false)
        private String airline;

        @Schema(description = "Flight duration in minutes", example = "330", required = false)
        private int flightDuration;

        @Schema(description = "Departure time", example = "16:00", required = false)
        private LocalDateTime departureTime;

        @Schema(description = "Arrival time", example = "16:00", required = false)
        private LocalDateTime arrivalTime;

        // Getters and Setters
        public String getFlightId() {
            return flightId;
        }

        public void setFlightId(String flightId) {
            this.flightId = flightId;
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

        public String getDepartureDate() {
            return departureDate;
        }

        public void setDepartureDate(String departureDate) {
            this.departureDate = departureDate;
        }

        public int getAvailableSeats() {
            return availableSeats;
        }

        public void setAvailableSeats(int availableSeats) {
            this.availableSeats = availableSeats;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getTravelClass() {
            return travelClass;
        }

        public void setTravelClass(String travelClass) {
            this.travelClass = travelClass;
        }

        public String getAirline() {
            return airline;
        }

        public void setAirline(String airline) {
            this.airline = airline;
        }

        public int getFlightDuration() {
            return flightDuration;
        }

        public void setFlightDuration(int flightDuration) {
            this.flightDuration = flightDuration;
        }

        public LocalDateTime  getArrivalTime() {
            return arrivalTime;
        }

        public void setArrivalTime(LocalDateTime arrivalTime) {
            this.arrivalTime = arrivalTime;
        }

        public LocalDateTime  getDepartureTime() {
            return departureTime;
        }

        public void setDepartureTime(LocalDateTime departureTime) {
            this.departureTime = departureTime;
        }
    }
}
