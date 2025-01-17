package com.cloudify.v1.viri;

import com.cloudify.beans.WeatherApiClient;
import com.cloudify.beans.WeatherAndDelayServiceZrno;
import com.cloudify.entities.WeatherDelayPrediction;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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

    //ZRNA
    @Inject
    WeatherApiClient weatherApiClient;
    @Inject
    WeatherAndDelayServiceZrno weatherAndDelayServiceZrno;

    private static final Map<String, String[]> FLIGHT_DATABASE = new HashMap<>() {{
        put("52", new String[]{"New York", "Los Angeles"});
        put("53", new String[]{"Los Angeles", "Nome"});
        put("54", new String[]{"Moscow", "Vladivostok"});
        put("55", new String[]{"Reykjavik", "Akureyri"});
        put("56", new String[]{"Buenos Aires", "Rio de Janeiro"});
        put("57", new String[]{"Sydney", "Melbourne"});
        put("58", new String[]{"Copenhagen", "Oslo"});
        put("59", new String[]{"Ulaanbaatar", "Norilsk"});
        put("60", new String[]{"Dubai", "Doha"});
        put("61", new String[]{"Hong Kong", "Singapore"});
        put("62", new String[]{"Cape Town", "Johannesburg"});
        put("63", new String[]{"Stockholm", "Helsinki"});
        put("64", new String[]{"Santiago", "Lima"});
        put("65", new String[]{"Jakarta", "Manila"});
        put("66", new String[]{"London", "Edinburgh"});
        put("67", new String[]{"Cairo", "Khartoum"});
        put("68", new String[]{"Alaska", "Greenland"});
        put("69", new String[]{"Nairobi", "Dar es Salaam"});
        put("70", new String[]{"Kolkata", "Dhaka"});
        put("71", new String[]{"Baku", "Tehran"});
        put("72", new String[]{"New Delhi", "Karachi"});
        put("73", new String[]{"Lagos", "Accra"});
        put("74", new String[]{"Vancouver", "Victoria"});
        put("75", new String[]{"Warsaw", "Kiev"});
        put("76", new String[]{"Seoul", "Tokyo"});
        put("77", new String[]{"Mumbai", "Chennai"});
        put("78", new String[]{"Auckland", "Wellington"});
        put("79", new String[]{"Bangalore", "Hyderabad"});
        put("80", new String[]{"Lagos", "Douala"});

        put("81", new String[]{"Los Angeles", "Tokyo"});
        put("CD5679", new String[]{"LAX", "ORD"});
        put("AB1234", new String[]{"LAX", "JFK"});
        put("CD5678", new String[]{"LAX", "ORD"});
        put("EF9101", new String[]{"SFO", "MIA"});
        put("GH1122", new String[]{"ORD", "ATL"});
        put("IJ3345", new String[]{"JFK", "LHR"});
        put("KL5678", new String[]{"LAX", "SYD"});
    }};

    @Operation(description = "Predict weather and delays for a flight.", summary = "Predict weather and delays")
    @APIResponses({
            @APIResponse(
                    description = "Weather and delay prediction retrieved successfully",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = WeatherDelayPrediction.class))
            ),
            @APIResponse(description = "Invalid input!", responseCode = "400"),
            @APIResponse(description = "Error retrieving weather data", responseCode = "500")
    })
    @Tag(name = "Weather and Delay Prediction Service")
    @GET
    public Response predictWeatherAndDelay(
            @Parameter(description = "Unique identifier for the flight", required = true, example = "54")
            @PathParam("flightId") String flightId) {

        try {
            String[] flightData = FLIGHT_DATABASE.get(flightId);
            if (flightData == null) {
                return Response.status(404).entity("Flight ID not found").build();
            }

            String origin = flightData[0];
            String finalDestination = flightData[1];

            WeatherDelayPrediction originPrediction = getWeatherData(origin, flightId);
            WeatherDelayPrediction destinationPrediction = getWeatherData(finalDestination, flightId);

            WeatherDelayPrediction prediction = weatherAndDelayServiceZrno.getWeatherAndDelayPrediction(flightId, originPrediction, destinationPrediction, finalDestination, origin);


            Response.ResponseBuilder responseBuilder = Response.ok(prediction);

            // Add CORS headers
            responseBuilder.header("Access-Control-Allow-Origin", "http://localhost:4200");
            responseBuilder.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            responseBuilder.header("Access-Control-Allow-Headers", "Content-Type, Authorization");

            // Return the response
            return responseBuilder.build();



        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity("Error processing the weather data").build();
        }
    }

    public WeatherDelayPrediction getWeatherData(String location, String flightId) throws Exception {

        HttpClient client = weatherApiClient.getHttpClient();
        URI uri = weatherApiClient.getApiURI(location);

        HttpRequest request = weatherApiClient.buildGetRequest(uri);

        HttpResponse<String> response = weatherApiClient.getAResponse(client, request, location);

        String weatherForecast = parseWeatherForecast(response.body());
        double windSpeed = parseWeatherAttribute(response.body(), "wind_kph");
        double visibility = parseWeatherAttribute(response.body(), "vis_km");
        double humidity = parseWeatherAttribute(response.body(), "humidity");
        double temperature = parseWeatherAttribute(response.body(), "temp_c");

        double delayProbability = calculateDelayProbability(windSpeed, visibility, humidity, temperature);
        delayProbability *= 100;

        String formattedProbability = String.format("%.0f%%", delayProbability);

        WeatherDelayPrediction prediction = new WeatherDelayPrediction();
        prediction.setFlightId(flightId);
        prediction.setWeatherForecast(weatherForecast);
        prediction.setDelayProbability(formattedProbability);
        prediction.setWindSpeed(windSpeed);
        prediction.setVisibility(visibility);
        prediction.setHumidity(humidity);
        prediction.setTemperature(temperature);

        return prediction;
    }

    private String parseWeatherForecast(String responseBody) {
        int startIndex = responseBody.indexOf("\"text\":\"") + 8;
        int endIndex = responseBody.indexOf("\"", startIndex);
        return responseBody.substring(startIndex, endIndex);
    }

    private double parseWeatherAttribute(String responseBody, String attribute) {
        int startIndex = responseBody.indexOf("\"" + attribute + "\":") + attribute.length() + 3;
        int endIndex = responseBody.indexOf(",", startIndex);
        if (endIndex == -1) {
            endIndex = responseBody.indexOf("}", startIndex);
        }
        return Double.parseDouble(responseBody.substring(startIndex, endIndex));
    }

    private double calculateDelayProbability(double windSpeed, double visibility, double humidity, double temperature) {
        double probability = 0.0;

        if (windSpeed > 25) {
            probability += 0.15;
        }

        if (visibility < 5) {
            probability += 0.2;
        }

        if (humidity > 70) {
            probability += 0.1;
        }

        if (temperature < -5 || temperature > 35) {
            probability += 0.25;
        }

        return Math.min(1.0, probability);
    }

    @Operation(description = "Health check to verify if the weather service link is working.", summary = "Health Check")
    @APIResponses({
            @APIResponse(description = "Weather service is healthy", responseCode = "200"),
            @APIResponse(description = "Weather service is down", responseCode = "500")
    })
    @Tag(name = "Weather and Delay Prediction Service")
    @GET
    @Path("/health")
    public Response healthCheck(@PathParam("flightId") String flightId) {
        try {
            String[] flightData = FLIGHT_DATABASE.get(flightId);
            if (flightData == null) {
                return Response.status(404).entity("Weather service is unhealthy because ID is not valid").build();
            }

            String origin = flightData[0];
            WeatherDelayPrediction originPrediction = getWeatherData(origin, flightId);

            return Response.status(200).entity("Weather service is healthy").build();
        } catch (Exception e) {
            return Response.status(500).entity("Weather service is down: " + e.getMessage()).build();
        }
    }
}