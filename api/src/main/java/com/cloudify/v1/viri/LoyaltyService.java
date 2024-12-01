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
import java.util.ArrayList;
import java.util.List;

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
}
