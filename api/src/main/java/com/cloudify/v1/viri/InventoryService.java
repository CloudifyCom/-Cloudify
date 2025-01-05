package com.cloudify.v1.viri;

import com.cloudify.beans.InventoryServiceApiBean;
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
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
public class InventoryService {

    @Inject
    InventoryServiceApiBean inventoryServiceApiBean;

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
        return Response.ok(inventoryServiceApiBean.listFlights(origin, destination, departureDate, returnDate)).build();
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
        return Response.status(Response.Status.CREATED).entity(inventoryServiceApiBean.addFlight(flight)).build();
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
            return Response.ok(inventoryServiceApiBean.getFlightDetails(flightId)).build();
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
            return Response.ok(inventoryServiceApiBean.getSeatAvailability(flightId)).build();
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
            return Response.ok(inventoryServiceApiBean.updateSeatAvailability(flightId, seatUpdate)).build();
        } catch (Exception e) {
            return Response.status(500).entity("Internal server error").build();
        }
    }
}
