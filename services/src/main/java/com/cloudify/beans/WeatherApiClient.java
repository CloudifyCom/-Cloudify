package com.cloudify.beans;

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


@ApplicationScoped
public class WeatherApiClient {

    private static final String WEATHER_API_URL = "https://api.weatherapi.com/v1/current.json";
    private static final String API_KEY = "d576c288a6614b35845231225240112";

    public HttpClient getHttpClient() {
        HttpClient client = HttpClient.newHttpClient();
        return client;
    }

    public URI getApiURI(String location) {

        String encodedLocation = URLEncoder.encode(location, StandardCharsets.UTF_8);
        URI uri = URI.create(WEATHER_API_URL + "?key=" + API_KEY + "&q=" + encodedLocation);
        return uri;
    }

    public HttpRequest buildGetRequest(URI uri) {
        return HttpRequest.newBuilder(uri).GET().build();
    }

    public HttpResponse<String> getAResponse(HttpClient client, HttpRequest request, String location) throws Exception{
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new Exception("Error retrieving weather data for: " + location);
        }

        return response;
    }

}