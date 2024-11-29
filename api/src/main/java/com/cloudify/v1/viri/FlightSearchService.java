package com.cloudify.v1.viri;

import com.cloudify.entities.BookingRequest;
import com.cloudify.entities.Booking;
import com.cloudify.entities.FlightSearchResponse;
import com.cloudify.entities.Flight;
import com.cloudify.entities.Passenger;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@OpenAPIDefinition(
        info = @Info(
                title = "Flight Search Service",
                version = "v1.0.0"
        )
)
@ApplicationScoped
@Path("/flights")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FlightSearchService {

    @Operation(description = "Retrieve available flights based on origin, destination, and date.", summary = "Search flights")
    @APIResponses({
            @APIResponse(description = "Successful retrieval of available flights", responseCode = "200", content = @Content(schema = @Schema(implementation = FlightSearchResponse.class))),
            @APIResponse(description = "Invalid input!", responseCode = "400")
    })
    @Tag(name = "Flight Search Service")
    @GET
    @Path("/search")
    public Response searchFlights(
            @Parameter(description = "Airport code of the origin", required = true, example = "LAX")
            @QueryParam("origin") String origin,
            @Parameter(description = "Airport code of the destination", required = true, example = "JFK")
            @QueryParam("destination") String destination,
            @Parameter(description = "Date of departure", required = true, example = "2024-11-20")
            @QueryParam("departureDate") String departureDate,
            @Parameter(description = "Number of availableSeats", example = "2")
            @QueryParam("availableSeats") Integer availableSeats,
            @Parameter(description = "Travel class", example = "economy")
            @QueryParam("travelClass") String travelClass) {

        FlightSearchResponse response = new FlightSearchResponse();

        return Response.ok(response).build();
    }
}
