package com.cloudify.v1.viri;
import com.cloudify.entities.Notification;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/notifications")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NotificationService {

    // Pridobi obstoječe obvestilo
    @Operation(description = "Retrieve the details of a specific notification.", summary = "Get an existing notification")
    @APIResponses({
            @APIResponse(description = "Successful retrieval of notification!", responseCode = "200", content = @Content(schema = @Schema(implementation = Notification.class))),
            @APIResponse(description = "Notification not found!", responseCode = "404")
    })
    @Tag(name = "Notification Service")
    @GET
    @Path("/{notificationId}")
    public Response getNotification(@PathParam("notificationId") String notificationId) {

        //pridobimo notification iz baze podatkov ako ne postoji vrnemo 404

        boolean obstaja = false;
        //Notification notification = notificationDatabase.get(notificationId);
        if (obstaja){//booking == null) {
            return Response.status(404).build();
        }
        return Response.ok("Objekat notification").build();
    }

    // Ustvari novo obvestilo
    @Operation(description = "Send a notification to users about flight-related information.", summary = "Create a new notification")
    @APIResponses({
            @APIResponse(description = "Notification successfully created!", responseCode = "201", content = @Content(schema = @Schema(implementation = Notification.class))),
            @APIResponse(description = "Invalid input!", responseCode = "404")
    })
    @Tag(name = "Notification Service")
    @POST
    public Response createNotification(Notification notificationDetails) {

        String notificationId = UUID.randomUUID().toString();

        Notification newNotification = new Notification(
                notificationId,
                notificationDetails.getUserId(),
                /*List<User> notifiedUsers,*/
                notificationDetails.getTextLength(), notificationDetails.getSendingTime(),
                notificationDetails.getTitle(),
                notificationDetails.getContent(),
                notificationDetails.getNotificationType());

        return Response.status(201).entity(newNotification).build();
    }

    // Posodobi obstoječe obvestilo
    @Operation(description = "Modify the content or details of an existing notification.", summary = "Update an existing notification")
    @APIResponses({
            @APIResponse(description = "Notification successfully updated", responseCode = "200", content = @Content(schema = @Schema(implementation = Notification.class))),
            @APIResponse(description = " Invalid input data!", responseCode = "400"),
            @APIResponse(description = " Notification not found!", responseCode = "404")
    })
    @Tag(name = "Notification Service")
    @PUT
    @Path("/{notificationId}")
    public Response updateNotification(@PathParam("notificationId") String notificationId, Notification notificationDetails) {


        //Notification existingBooking = notificationDatabase.get(notificationId);
        Notification existingBooking = new Notification();
        boolean obstaja = true; // ako podataka sa id obstaja v bazi podataka

        if (existingBooking!=null || obstaja) {

            // TO DO
            // Posodobimo listu obavestenih korisnika

            existingBooking.setContent(notificationDetails.getContent());
            existingBooking.setTextLength(notificationDetails.getTextLength());
            existingBooking.setNotificationType(notificationDetails.getNotificationType());



            return Response.ok(existingBooking).build();
        } else {
            return Response.status(404).build();
        }
    }

    // Izbriši obstoječe obvestilo
    @Operation(description = "Remove an existing notification.", summary = "Delete a notification")
    @APIResponses({
            @APIResponse(description = "Notification successfully deleted!", responseCode = "200"),
            @APIResponse(description = "Notification not found!", responseCode = "404")
    })
    @Tag(name = "Notification Service")
    @DELETE
    @Path("/{notificationId}")
    public Response deleteNotification(@PathParam("notificationId") String notificationId) {

//        if (!notificationDatabase.containsKey(notificationId)) {
//            return Response.status(404).build();
//        }
//
//        bookingDatabase.remove(bookingId);
        return Response.ok().build();
    }
}
