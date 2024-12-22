package com.cloudify.beans;


import com.cloudify.entities.FlightSearchResponse;
import com.cloudify.entities.FlightStatus;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@ApplicationScoped
public class FlightStatusResponseBean {

    public FlightStatus vrniflightStatus(String flightId, List<FlightSearchResponse.Flight> flightsDatabase) {
        FlightSearchResponse.Flight flight = null;
        for (FlightSearchResponse.Flight f : flightsDatabase) {
            if (f.getFlightId().equals(flightId)) {
                flight = f;
                break;
            }
        }

        if (flight == null) {
            FlightStatus flightStatus = new FlightStatus();
            flightStatus.setFlightId(flightId);
            flightStatus.setStatus("Flight not found!");
            return flightStatus;
        }


        LocalDateTime now = LocalDateTime.now();

        String status = determineFlightStatus(now, flight);

        FlightStatus flightStatus = new FlightStatus();
        flightStatus.setFlightId(flightId);
        flightStatus.setStatus(status);

        return flightStatus;
    }

    public String determineFlightStatus(LocalDateTime now, FlightSearchResponse.Flight flight) {
        LocalDateTime departureTime = flight.getDepartureTime();
        LocalDateTime arrivalTime = flight.getArrivalTime();
        long minutesToDeparture = ChronoUnit.MINUTES.between(now, departureTime);
        long minutesAfterArrival = ChronoUnit.MINUTES.between(now, arrivalTime);

        if (minutesToDeparture > 30) {
            return "Not ready to set off";
        } else if (minutesToDeparture <= 30 && minutesToDeparture > 0) {
            return "Ready to set off";
        } else if (minutesToDeparture <= 0 && minutesAfterArrival >= 0) {
            return "In the air";
        } else if (minutesAfterArrival < 0) {
            return "Landed";
        } else {
            return "Unknown status";
        }
    }
}
