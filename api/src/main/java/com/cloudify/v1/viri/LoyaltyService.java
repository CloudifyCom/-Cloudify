package com.cloudify.v1.viri;

import com.cloudify.beans.LoyaltyServiceApiBean;
import com.cloudify.entities.LoyaltyMember;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
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
                title = "Loyalty Program Service",
                version = "v1.0.0"
        )
)
@ApplicationScoped
@Path("/loyalty")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoyaltyService {

    @Inject
    LoyaltyServiceApiBean loyaltyServiceApiBean;

    @GET
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
    public Response listLoyaltyMembers() {
        return Response.ok(loyaltyServiceApiBean.listLoyaltyMembers()).build();
    }

    @POST
    @Operation(summary = "Enroll a user in the loyalty program", description = "Add a user to the loyalty program.")
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
        return Response.status(Response.Status.CREATED).entity(loyaltyServiceApiBean.enrollUser(member)).build();
    }
}
