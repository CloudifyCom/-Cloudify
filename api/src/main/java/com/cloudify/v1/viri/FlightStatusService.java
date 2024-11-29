package com.cloudify.v1.viri;

import com.cloudify.entities.BookingRequest;
import com.cloudify.entities.FlightStatus;
import com.cloudify.entities.Booking;
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
                title = "Flight Status Service",
                version = "v1.0.0"
        )
)
@ApplicationScoped
@Path("/flights/{flightId}/status")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FlightStatusService {

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

        // Mock response for demonstration
        FlightStatus status = new FlightStatus();
        status.setFlightId(flightId);
        status.setStatus("onTime");

        return Response.ok(status).build();
    }
}
