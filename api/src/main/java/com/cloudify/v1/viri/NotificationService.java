package com.cloudify.v1.viri;
import com.cloudify.entities.Notification;

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
