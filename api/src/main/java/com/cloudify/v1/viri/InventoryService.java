package com.cloudify.v1.viri;

import com.cloudify.entities.LoyaltyMember;
import com.cloudify.entities.User;
import com.cloudify.entities.BookingRequest;
import com.cloudify.entities.Booking;
import com.cloudify.entities.Passenger;
import com.cloudify.entities.Flight;
import com.cloudify.entities.SeatAvailability;
import com.cloudify.entities.SeatUpdate;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.net.URL;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@OpenAPIDefinition(
        info = @Info(
                title = "Inventory Service",
                version = "v1.0.0"
        )
)
@ApplicationScoped
@Path("/flights")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InventoryService implements HealthCheck{

    @GET
    @Operation(summary = "Get a list of available flights", description = "Retrieve available flights based on origin, destination, and date range.")
    @APIResponses({
            @APIResponse(
                    description = "List of available flights",
                    responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Flight[].class))
            ),
            @APIResponse(
                    description = "Bad request (e.g., missing required parameters)",
                    responseCode = "400"
            ),
            @APIResponse(
                    description = "Internal server error.",
                    responseCode = "500"
            )
    })
    @Tag(name = "Inventory Service")
    public Response listFlights(@QueryParam("origin") String origin,
                                @QueryParam("destination") String destination,
                                @QueryParam("departureDate") String departureDate,
                                @QueryParam("returnDate") String returnDate) {
        // Logic for fetching flights

        return Response.ok(/* list of flights */).build();
    }

    @POST
    @Operation(summary = "Add a new flight", description = "Create a new flight in the inventory system.")
    @APIResponses({
            @APIResponse(
                    description = "Flight created successfully",
                    responseCode = "201",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Flight.class))
            ),
            @APIResponse(
                    description = "Invalid input data",
                    responseCode = "400"
            ),
            @APIResponse(
                    description = "Internal server error",
                    responseCode = "500"
            )
    })
    @Tag(name = "Inventory Service")
    public Response addFlight(Flight flight) {
        // Logic for adding a new flight
        return Response.status(Response.Status.CREATED).entity(flight).build();
    }

    @GET
    @Path("/{flightId}")
    @Operation(summary = "Get flight details", description = "Retrieve detailed information about a specific flight.")
    @APIResponses({
            @APIResponse(description = "Details of the specified flight",
                    responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Flight.class))
            ),
            @APIResponse(
                    description = "Flight not found",
                    responseCode = "404"
            ),
            @APIResponse(
                    description = "Internal server error",
                    responseCode = "500"
            )
    })
    @Tag(name = "Inventory Service")
    public Response getFlightDetails(
            @Parameter(description = "Unique identifier of the flight", required = true, example = "ABC123")
            @PathParam("flightId") String flightId
    ) {
        try {
            // Logic to get flight details
            return Response.ok(/* flight details object */).build();
        } catch (Exception e) {
            return Response.status(500).entity("Internal server error").build();
        }
    }
    @GET
    @Path("/{flightId}/seats")
    @Operation(summary = "Get seat availability", description = "Retrieve available seats for a specific flight.")
    @APIResponses({
            @APIResponse(
                    description = "Seat availability for the specified flight",
                    responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = SeatAvailability.class))
            ),
            @APIResponse(
                    description = "Flight not found",
                    responseCode = "404"
            ),
            @APIResponse(
                    description = "Internal server error",
                    responseCode = "500"
            )
    })
    @Tag(name = "Inventory Service")
    public Response getSeatAvailability(
            @Parameter(description = "Unique identifier of the flight", required = true, example = "ABC123")
            @PathParam("flightId") String flightId
    ) {
        try {
            // Logic to retrieve seat availability
            return Response.ok(/* seat availability object */).build();
        } catch (Exception e) {
            return Response.status(500).entity("Internal server error").build();
        }
    }

    @PATCH
    @Path("/{flightId}/seats")
    @Operation(summary = "Update seat availability", description = "Update seat availability for a specific flight after booking or cancellation.")
    @APIResponses({
            @APIResponse(
                    description = "Seat availability updated successfully",
                    responseCode = "200"
            ),
            @APIResponse(
                    description = "Invalid request",
                    responseCode = "400"
            ),
            @APIResponse(
                    description = "Flight not found",
                    responseCode = "404"
            ),
            @APIResponse(
                    description = "Internal server error",
                    responseCode = "500"
            )
    })
    @Tag(name = "Inventory Service")
    public Response updateSeatAvailability(
            @Parameter(description = "Unique identifier of the flight", required = true, example = "ABC123")
            @PathParam("flightId") String flightId,
            SeatUpdate seatUpdate
    ) {
        try {
            // Logic to update seat availability
            return Response.ok(/* updated seat availability object */).build();
        } catch (Exception e) {
            return Response.status(500).entity("Internal server error").build();
        }
    }

    @GET
    @Path("/{flightId}/seats/health")
    @Operation(summary = "Health check for Inventory Service", description = "Check if the inventory service is working correctly")
    @APIResponses({
            @APIResponse(description = "Inventory Service is healthy", responseCode = "200"),
            @APIResponse(description = "Inventory Service is unhealthy", responseCode = "503")
    })
    @Tag(name = "Inventory Service")
    public Response healthCheck(@Parameter(description = "Unique identifier of the flight", required = true, example = "ABC123")
                                    @PathParam("flightId") String flightId) {
        HealthCheckResponse healthCheckResponse = performHealthCheck(flightId);

        if (healthCheckResponse.getState() == HealthCheckResponse.State.UP) {
            return Response.ok("Inventory Service is healthy.").build();
        } else {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity("Inventory Service is unhealthy.").build();
        }
    }

    private HealthCheckResponse performHealthCheck(String flightId) {
        try {
            String urlString = "http://localhost:8080/v1/flights/" + flightId + "/seats";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                return HealthCheckResponse.named("Inventory Health Check")
                        .state(true)  // Pass `true` for UP
                        .build();
            } else {
                return HealthCheckResponse.named("Inventory Health Check")
                        .state(false)  // Pass `false` for DOWN
                        .build();
            }
        } catch (Exception e) {
            return HealthCheckResponse.named("Inventory Health Check")
                    .state(false)  // Pass `false` for DOWN
                    .build();
        }
    }

    @Override
    public HealthCheckResponse call() {
        return performHealthCheck("defaultFlightId");
    }
}
