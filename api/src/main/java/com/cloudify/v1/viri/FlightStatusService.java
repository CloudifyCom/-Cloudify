package com.cloudify.v1.viri;

import com.cloudify.entities.FlightStatus;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.cloudify.entities.FlightSearchResponse;
import com.cloudify.beans.FlightStatusResponseBean;
import javax.inject.Inject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.net.HttpURLConnection;
import java.net.URL;

@OpenAPIDefinition(
        info = @Info(
                title = "Flight Status Service",
                version = "v1.0.0"
        )
)
@ApplicationScoped
@Path("/flights/{flightId}/status")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FlightStatusService {


    @Inject
    FlightStatusResponseBean flightStatusResponseBean;

    private static List<FlightSearchResponse.Flight> flightsDatabase = new ArrayList<>();

    static {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Flight 1
        FlightSearchResponse.Flight flight1 = new FlightSearchResponse.Flight();
        flight1.setFlightId("AB1234");
        flight1.setOrigin("LAX");
        flight1.setDestination("JFK");
        flight1.setDepartureDate("2024-11-20");
        flight1.setDepartureTime(LocalDateTime.parse("2024-11-20 09:00", formatter));
        flight1.setAvailableSeats(50);
        flight1.setTravelClass("economy");
        flight1.setPrice(299.99);
        flight1.setAirline("Delta Airlines");
        flight1.setFlightDuration(330);
        flight1.setArrivalTime(flight1.getDepartureTime().plusMinutes(flight1.getFlightDuration()));

        // Flight 2
        FlightSearchResponse.Flight flight2 = new FlightSearchResponse.Flight();
        flight2.setFlightId("CD5678");
        flight2.setOrigin("LAX");
        flight2.setDestination("ORD");
        flight2.setDepartureDate("2024-11-22");
        flight2.setDepartureTime(LocalDateTime.parse("2024-11-22 14:30", formatter));
        flight2.setAvailableSeats(150);
        flight2.setTravelClass("business");
        flight2.setPrice(599.99);
        flight2.setAirline("American Airlines");
        flight2.setFlightDuration(225);
        flight2.setArrivalTime(flight2.getDepartureTime().plusMinutes(flight2.getFlightDuration()));

        // Flight 3
        FlightSearchResponse.Flight flight3 = new FlightSearchResponse.Flight();
        flight3.setFlightId("EF9101");
        flight3.setOrigin("SFO");
        flight3.setDestination("MIA");
        flight3.setDepartureDate("2024-12-01");
        flight3.setDepartureTime(LocalDateTime.parse("2024-12-01 07:00", formatter));
        flight3.setAvailableSeats(200);
        flight3.setTravelClass("economy");
        flight3.setPrice(199.99);
        flight3.setAirline("United Airlines");
        flight3.setFlightDuration(320);
        flight3.setArrivalTime(flight3.getDepartureTime().plusMinutes(flight3.getFlightDuration()));

        // Flight 4
        FlightSearchResponse.Flight flight4 = new FlightSearchResponse.Flight();
        flight4.setFlightId("GH1122");
        flight4.setOrigin("ORD");
        flight4.setDestination("ATL");
        flight4.setDepartureDate("2024-12-22");
        flight4.setDepartureTime(LocalDateTime.parse("2024-12-22 22:00", formatter));
        flight4.setAvailableSeats(180);
        flight4.setTravelClass("first");
        flight4.setPrice(999.99);
        flight4.setAirline("Delta Airlines");
        flight4.setFlightDuration(150);
        flight4.setArrivalTime(flight4.getDepartureTime().plusMinutes(flight4.getFlightDuration()));

        // Flight 5
        FlightSearchResponse.Flight flight5 = new FlightSearchResponse.Flight();
        flight5.setFlightId("IJ3345");
        flight5.setOrigin("JFK");
        flight5.setDestination("LHR");
        flight5.setDepartureDate("2024-12-23");
        flight5.setDepartureTime(LocalDateTime.parse("2024-12-23 11:00", formatter));
        flight5.setAvailableSeats(120);
        flight5.setTravelClass("economy");
        flight5.setPrice(499.99);
        flight5.setAirline("British Airways");
        flight5.setFlightDuration(420);
        flight5.setArrivalTime(flight5.getDepartureTime().plusMinutes(flight5.getFlightDuration()));

        // Flight 6
        FlightSearchResponse.Flight flight6 = new FlightSearchResponse.Flight();
        flight6.setFlightId("KL5678");
        flight6.setOrigin("LAX");
        flight6.setDestination("SYD");
        flight6.setDepartureDate("2024-12-22");
        flight6.setDepartureTime(LocalDateTime.parse("2024-12-22 23:02", formatter));
        flight6.setAvailableSeats(70);
        flight6.setTravelClass("business");
        flight6.setPrice(1499.99);
        flight6.setAirline("Qantas Airways");
        flight6.setFlightDuration(830);
        flight6.setArrivalTime(flight6.getDepartureTime().plusMinutes(flight6.getFlightDuration()));

        flightsDatabase.add(flight1);
        flightsDatabase.add(flight2);
        flightsDatabase.add(flight3);
        flightsDatabase.add(flight4);
        flightsDatabase.add(flight5);
        flightsDatabase.add(flight6);
    }

    @Operation(description = "Retrieve the current status of a flight.", summary = "Get flight status")
    @APIResponses({
            @APIResponse(description = "Flight status retrieved successfully", responseCode = "200", content = @Content(schema = @Schema(implementation = FlightStatus.class))),
            @APIResponse(description = "Flight not found!", responseCode = "404")
    })
    @Tag(name = "Flight Status Service")
    @GET
    public Response getFlightStatus(
            @Parameter(description = "Unique identifier for the flight", required = true, example = "AB1234")
            @PathParam("flightId") String flightId) {

        FlightStatus flightStatus = flightStatusResponseBean.vrniflightStatus(flightId, flightsDatabase);

        return Response.ok(flightStatus).build();
    }

    // Health Check Method
    @Operation(description = "Health check for Flight Status Service.", summary = "Check if the flight status service is working correctly")
    @APIResponses({
            @APIResponse(description = "Flight Status Service is healthy", responseCode = "200"),
            @APIResponse(description = "Flight Status Service is unhealthy", responseCode = "503")
    })
    @Path("/health")
    @GET
    public Response healthCheck(
            @Parameter(description = "Unique identifier for the flight", required = true, example = "AB1234")
            @PathParam("flightId") String flightId) {

        try {
            FlightStatus flightStatus = null;

            for(FlightSearchResponse.Flight flight : flightsDatabase) {
                if(flight.getFlightId().equals(flightId)) {
                    flightStatus = flightStatusResponseBean.vrniflightStatus(flightId, flightsDatabase);
                }
            }

            boolean isLinkAvailable = checkFlightStatusLink(flightId);


            if (flightStatus != null && isLinkAvailable) {
                return Response.ok("Flight Status Service is healthy. Both the flight status and the link are working correctly.").build();
            } else {
                String errorMessage = "Flight Status Service is unhealthy. ";
                if (flightStatus == null) {
                    errorMessage += "Flight status not found for " + flightId + ". ";
                }
                if (!isLinkAvailable) {
                    errorMessage += "Flight status link is not responding. ";
                }
                return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(errorMessage).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error during health check: " + e.getMessage()).build();
        }
    }

    private boolean checkFlightStatusLink(String flightId) {
        try {
            String urlString = "http://localhost:8080/v1/flights/" + flightId + "/status";

            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();

            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (Exception e) {
            return false;
        }
    }

}
