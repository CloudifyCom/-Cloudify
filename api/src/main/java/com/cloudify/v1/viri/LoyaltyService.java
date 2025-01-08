package com.cloudify.v1.viri;

import com.cloudify.entities.LoyaltyMember;
import com.cloudify.entities.User;
import com.cloudify.entities.BookingRequest;
import com.cloudify.entities.Booking;
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

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@OpenAPIDefinition(
        info = @Info(
                title = "Loyalty Program Service",
                version = "v1.0.0"
        )
)
@ApplicationScoped
@Path("/loyalty")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoyaltyService implements HealthCheck{


    @Operation(summary = "List all loyalty members", description = "Retrieve all users enrolled in the loyalty program.")
    @APIResponses({
            @APIResponse(
                    description = "List of loyalty members",
                    responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = LoyaltyMember[].class))
            ),
            @APIResponse(
                    description = "Internal server error",
                    responseCode = "500",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class))
            )
    })
    @Tag(name = "Loyalty Program Service")
    @GET
    public Response listLoyaltyMembers() {
        //TODO: implement server error logic, implement database request

        // Logic to list all loyalty members

        // List<LoyaltyMember> members = getAllLoyaltyMembers();
        User mica = new User(1, "Mica", "mica@mejl", "99");
        User pera = new User(2, "Pera", "pera@mejl", "109");
        LoyaltyMember member = new LoyaltyMember(1, mica, 99);
        LoyaltyMember member2 = new LoyaltyMember(2, pera, 109);

        List<LoyaltyMember> loyaltyMemberList = new ArrayList<LoyaltyMember>(){{
            add(member);
            add(member2);
        }};
        return Response.ok(loyaltyMemberList).build();
    }

    @POST
    @Operation(summary = "Enroll a user in the loyalty program", description = "Add a user to the loyalty program.")
    //@Consumes(MediaType.APPLICATION_JSON)
    @APIResponses({
            @APIResponse(
                    description = "User successfully enrolled",
                    responseCode = "200"
            ),
            @APIResponse(
                    description = "Invalid input",
                    responseCode = "400"
            ),
            @APIResponse(
                    description = "Internal server error",
                    responseCode = "500"
            )
    })
    @Tag(name = "Loyalty Program Service")
    public Response enrollUser(LoyaltyMember member) {
        //TODO implement database entries
        // Logic to enroll user

        return Response.status(Response.Status.CREATED).entity(member).build();
    }

    @GET
    @Path("/health")
    @Operation(summary = "Health check for Loyalty Service", description = "Check if the Loyalty Service is working correctly")
    @APIResponses({
            @APIResponse(description = "Loyalty Service is healthy", responseCode = "200"),
            @APIResponse(description = "Loyalty Service is unhealthy", responseCode = "503")
    })
    @Tag(name = "Loyalty Program Service")
    public Response healthCheck() {
        HealthCheckResponse healthCheckResponse = performHealthCheck();

        if (healthCheckResponse.getState() == HealthCheckResponse.State.UP) {
            return Response.ok("Loyalty Service is healthy.").build();
        } else {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity("Loyalty Service is unhealthy.").build();
        }
    }

    private HealthCheckResponse performHealthCheck() {
        try {
            // Check the /loyalty endpoint
            String urlString = "http://localhost:8080/v1/loyalty";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                return HealthCheckResponse.named("Loyalty Service Health Check")
                        .state(true)  // UP
                        .build();
            } else {
                return HealthCheckResponse.named("Loyalty Service Health Check")
                        .state(false)  // DOWN
                        .build();
            }
        } catch (IOException e) {
            return HealthCheckResponse.named("Loyalty Service Health Check")
                    .state(false)  // DOWN
                    .build();
        }
    }

    @Override
    public HealthCheckResponse call() {
        return performHealthCheck();
    }

}
