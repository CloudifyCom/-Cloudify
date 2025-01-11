package com.cloudify.beans;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import com.cloudify.entities.Flight;
import com.cloudify.entities.FlightSearchResponse;
import com.cloudify.entities.SeatAvailability;
import com.cloudify.entities.SeatUpdate;

import static com.arjuna.ats.arjuna.tools.osb.mbean.StateManagerWrapper.formatter;


@ApplicationScoped
public class InventoryServiceApiBean {
    public List<Flight> listFlights(String origin, String destination, String departureDate, String returnDate) {
        // Logic for fetching flights
        List<Flight> ret = null;
        return ret;
    }

    public Flight addFlight(Flight flight) {
        // Logic for adding a new flight
        return flight;
    }

    public Flight getFlightDetails(String flightId) {
        // Logic to get flight details

        Flight flight = null;
        if(Objects.equals(flightId, "AB1234")) {
            flight = new Flight("AB1234", "Delta Airlines", "LAX", "JFK", LocalDateTime.of(2025, 1, 10, 15, 30), LocalDateTime.of(2025, 1, 10, 20, 30), 299.99, "330", 50, "economy");
        }
        if(Objects.equals(flightId, "CD5678")) {
            flight = new Flight("CD5678", "American Airlines", "LAX", "ORD", LocalDateTime.of(2025, 1, 10, 15, 30), LocalDateTime.of(2025, 1, 10, 20, 30), 599.99, "225", 150, "business");
        }
        if(Objects.equals(flightId, "EF9101")) {
            flight = new Flight("EF9101", "United Airlines", "SFO", "MIA", LocalDateTime.of(2025, 1, 10, 15, 30), LocalDateTime.of(2025, 1, 10, 20, 30), 199.99, "320", 200, "economy");
        }
        if(Objects.equals(flightId, "GH1122")) {
            flight = new Flight("GH1122", "Delta Airlines", "ORD", "ATL", LocalDateTime.of(2025, 1, 10, 15, 30), LocalDateTime.of(2025, 1, 10, 20, 30), 999.99, "150", 180, "first");
        }
        if(Objects.equals(flightId, "IJ3345")) {
            flight = new Flight("IJ3345", "British Airways", "JFK", "LHR", LocalDateTime.of(2025, 1, 10, 15, 30), LocalDateTime.of(2025, 1, 10, 20, 30), 499.99, "420", 120, "economy");
        }
        if(Objects.equals(flightId, "KL5678")) {
            flight = new Flight("KL5678", "Qantas Airways", "LAX", "SYD", LocalDateTime.of(2025, 1, 10, 15, 30), LocalDateTime.of(2025, 1, 10, 20, 30), 1499.99, "830", 70, "business");
        }
        if(Objects.equals(flightId, "CD5679")) {
            flight = new Flight("CD5679", "American Airlines", "LAX", "ORD", LocalDateTime.of(2025, 1, 10, 15, 30), LocalDateTime.of(2025, 1, 10, 20, 30), 680.99, "225", 150, "business");
        }

        if(Objects.equals(flightId, "54")) {
            flight = new Flight("54", "Russian Airlines", "Moscow", "Vladivostok", LocalDateTime.of(2025, 1, 10, 15, 30), LocalDateTime.of(2025, 1, 10, 20, 30), 680.99, "225", 150, "business");
        }
        return flight;
    }

    public SeatAvailability getSeatAvailability(String flightId) {
        // Logic to retrieve seat availability
        return null;
    }

    public SeatAvailability updateSeatAvailability(String flightId, SeatUpdate seatUpdate) {
        // Logic to update seat availability
        return null;
    }
}
