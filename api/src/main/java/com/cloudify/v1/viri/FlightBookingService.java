package com.cloudify.v1.viri;

import com.cloudify.entities.BookingRequest;
import com.cloudify.entities.Booking;
import com.cloudify.entities.Passenger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/bookings")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FlightBookingService {

    @GET
    @Path("/{bookingId}")
    public Response getBooking(@PathParam("bookingId") String bookingId) {
        //pridobimo booking iz baze podatkov ako ne postoji vrnemo 404
        boolean obstaja = false;
        //Booking booking = bookingDatabase.get(bookingId);
        if (obstaja){//booking == null) {
            return Response.status(404).build();
        }
        return Response.ok(bookingId).build();
    }

    @POST
    public Response createBooking(BookingRequest bookingRequest) {
        // Kreiramo nov booking
        if(bookingRequest.getFlight().getPrice() == bookingRequest.getPaymentMethod().getAmount()) {

            String bookingId = UUID.randomUUID().toString();
            Booking booking = new Booking(bookingId, bookingRequest.getFlight());
            List<Passenger> newListPassenger = new ArrayList<Passenger>();
            newListPassenger.add(bookingRequest.getPassenger());
            booking.setPassengers(newListPassenger);

            double price = 0;
            price = price + bookingRequest.getPaymentMethod().getAmount();
            booking.setTotalPrice(price);

            // Vnesemo booking v bazu podataka
            //bookingDatabase.put(booking.getId(), booking);

            return Response.status(201).entity(booking).build();
        }
        return  Response.status(422).build();
    }

    @PUT
    @Path("/{bookingId}")
    public Response updateBooking(@PathParam("bookingId") String bookingId, BookingRequest updatedBooking) {
        // Posodobimo booking ako postoji v bazi podatkov
        //Booking existingBooking = bookingDatabase.get(bookingId);
        Booking existingBooking = null;
        if (existingBooking == null) {
            return Response.status(404).build();
        }

        List<Passenger> newList = existingBooking.getPassengers();
        newList.add(updatedBooking.getPassenger());
        existingBooking.setPassengers(newList);

        double totalPrice = existingBooking.getTotalPrice();
        totalPrice = totalPrice + updatedBooking.getPaymentMethod().getAmount();
        existingBooking.setTotalPrice(totalPrice);

        return Response.ok(existingBooking).build();
    }

    @DELETE
    @Path("/{bookingId}")
    public Response cancelBooking(@PathParam("bookingId") String bookingId) {
//        if (!bookingDatabase.containsKey(bookingId)) {
//            return Response.status(404).build();
//        }
//
//        bookingDatabase.remove(bookingId);
        return Response.ok().build();
    }
}
