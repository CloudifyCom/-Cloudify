package com.cloudify.beans;
import com.cloudify.entities.FlightSearchResponse;
import com.cloudify.entities.WeatherDelayPrediction;
import org.jboss.weld.context.http.Http;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class FlightSearchResponseBean {

    public FlightSearchResponse vrniResponse(List<FlightSearchResponse.Flight> flightsDatabase, String origin, String destination, String departureDate, Integer availableSeats, String travelClass) {
        List<FlightSearchResponse.Flight> filteredFlights = new ArrayList<>();

        for (FlightSearchResponse.Flight flight : flightsDatabase) {
            boolean isMatching = flight.getOrigin().equalsIgnoreCase(origin)
                    && flight.getDestination().equalsIgnoreCase(destination)
                    && flight.getDepartureDate().equals(departureDate)
                    && flight.getAvailableSeats() >= availableSeats
                    && (travelClass == null || flight.getTravelClass().equalsIgnoreCase(travelClass));

            if (isMatching) {
                filteredFlights.add(flight);
            }
        }

        FlightSearchResponse response = new FlightSearchResponse();
        if (filteredFlights.isEmpty()) {
            response.setMessage("No flights available for the given criteria.");
        } else {
            response.setAvailableFlights(filteredFlights);
            response.setMessage("Successfully retrieved available flights.");
        }

        return response;

    }
}
