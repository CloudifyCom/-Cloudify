package com.cloudify.v1.viri;

import com.cloudify.entities.BookingRequest;
import com.cloudify.entities.FlightStatus;
import com.cloudify.entities.WeatherDelayPrediction;
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
                title = "Weather and Delay Prediction Service",
                version = "v1.0.0"
        )
)
@ApplicationScoped
@Path("/weather-delay-prediction/{flightId}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WeatherAndDelayService {

    @Operation(description = "Predict weather and delays for a flight.", summary = "Predict weather and delays")
    @APIResponses({
            @APIResponse(description = "Weather and delay prediction retrieved successfully", responseCode = "200", content = @Content(schema = @Schema(implementation = WeatherDelayPrediction.class))),
            @APIResponse(description = "Invalid input!", responseCode = "400")
    })
    @Tag(name = "Weather and Delay Prediction Service")
    @GET
    public Response predictWeatherAndDelay(
            @Parameter(description = "Unique identifier for the flight", required = true, example = "AB1234")
            @PathParam("flightId") String flightId) {

        // Mock response for demonstration
        WeatherDelayPrediction prediction = new WeatherDelayPrediction();
        prediction.setFlightId(flightId);
        prediction.setWeatherForecast("Sunny");
        prediction.setDelayProbability(0.1);

        return Response.ok(prediction).build();
    }
}
