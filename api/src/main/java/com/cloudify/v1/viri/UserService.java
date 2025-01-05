package com.cloudify.v1.viri;

import com.cloudify.beans.UserServiceApiBean;
import com.cloudify.entities.LoyaltyMember;
import com.cloudify.entities.User;
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
                title = "User Service",
                version = "v1.0.0"
        )
)
@ApplicationScoped
@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService {

    @Inject
    UserServiceApiBean userServiceApiBean;

    @GET
    @Operation(summary = "List all users", description = "Retrieve a list of all registered users.")
    @APIResponses({
            @APIResponse(
                    description = "Returned list of users.",
                    responseCode = "200"
            ),
            @APIResponse(
                    description = "Server error.",
                    responseCode = "500"
            )
    })
    @Tag(name = "User Service")
    public Response listUsers(@QueryParam("limit") @DefaultValue("100") int limit,
                              @QueryParam("offset") @DefaultValue("0") int offset) {
        return Response.ok(userServiceApiBean.listUsers(limit, offset)).build();
    }

    @POST
    @Operation(summary = "Create a new user", description = "Register a new user in the system.")
    @APIResponses({
            @APIResponse(
                    description = "User successfully created.",
                    responseCode = "201"
            ),
            @APIResponse(
                    description = "Invalid input.",
                    responseCode = "400"
            ),
            @APIResponse(
                    description = "Internal server error.",
                    responseCode = "500"
            )
    })
    @Tag(name = "User Service")
    public Response createUser(User user) {
        return Response.status(Response.Status.CREATED).entity(userServiceApiBean.createUser(user)).build();
    }

    @GET
    @Path("/{userId}")
    @Operation(summary = "Retrieve a user", description = "Get details of a specific user by ID.")
    @APIResponses({
            @APIResponse(
                    description = "User details retrieved successfully.",
                    responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = User.class))
            ),
            @APIResponse(
                    description = "User not found.",
                    responseCode = "404"
            ),
            @APIResponse(
                    description = "Server error.",
                    responseCode = "500"
            )
    })
    @Tag(name = "User Service")
    public Response getUser(@PathParam("userId") int userId) {
        return Response.ok(userServiceApiBean.getUser(userId)).build();
    }

    @PUT
    @Path("/{userId}")
    @Operation(summary = "Update a user", description = "Modify the details of an existing user.")
    @APIResponses({
            @APIResponse(
                    description = "User updated successfully.",
                    responseCode = "200"
            ),
            @APIResponse(
                    description = "Invalid input.",
                    responseCode = "400"
            ),
            @APIResponse(
                    description = "User not found.",
                    responseCode = "404"
            ),
            @APIResponse(
                    description = "Server error.",
                    responseCode = "500"
            )
    })
    @Tag(name = "User Service")
    public Response updateUser(@PathParam("userId") int userId, User user) {
        return Response.ok(userServiceApiBean.updateUser(userId, user)).build();
    }

    @DELETE
    @Path("/{userId}")
    @Operation(summary = "Delete a user", description = "Remove a user from the system.")
    @APIResponses({
            @APIResponse(
                    description = "User deleted successfully.",
                    responseCode = "200"
            ),
            @APIResponse(
                    description = "User not found.",
                    responseCode = "404"
            ),
            @APIResponse(
                    description = "Server error.",
                    responseCode = "500"
            )
    })
    @Tag(name = "User Service")
    public Response deleteUser(@PathParam("userId") int userId) {
        userServiceApiBean.deleteUser(userId);
        return Response.ok().build();
    }
}
