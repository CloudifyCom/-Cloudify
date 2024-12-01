package com.cloudify.v1.viri;

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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@OpenAPIDefinition(
        info = @Info(
                title = "Flight Booking Service",
                version = "v1.0.0"
        )
)
@ApplicationScoped
@Path("/bookings")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class  FlightBookingService {

    @Operation(description = "Retrieve the details of an existing booking using the bookingId.", summary = "Get an existing booking")
    @APIResponses({
            @APIResponse(
                    description = "Successful retrieval of booking!",
                    responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Booking.class))
            ),
            @APIResponse(
                    description = "Booking not found!",
                    responseCode = "404"
            )
    })
    @Tag(name = "Flight Booking Service")
    @GET
    @Path("/{bookingId}")
    public Response getBooking( @Parameter(description = "Unique identifier of the booking", example = "BK987654")
            @PathParam("bookingId") String bookingId) {
        //pridobimo booking iz baze podatkov ako ne postoji vrnemo 404
        boolean obstaja = false;
        //Booking booking = bookingDatabase.get(bookingId);
        if (obstaja){//booking == null) {
            return Response.status(404).build();
        }
        Booking booking = new Booking(bookingId, null);
        return Response.ok(booking).build();
    }

    @Operation(description = "Create a new booking for a flight with passenger details and payment method.", summary = "Create a new flight booking")
    @APIResponses({
            @APIResponse(description = "Successfully created booking!", responseCode = "201", content = @Content(schema = @Schema(implementation = Booking.class))),
            @APIResponse(description = "The transaction amount and the flight price must be equal!", responseCode = "422"),
            @APIResponse(description = "Invalid input!", responseCode = "400")
    })
    @Tag(name = "Flight Booking Service")
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

    @Operation(description = "Update an existing booking with new details.", summary = "Update an existing booking")
    @APIResponses({
            @APIResponse(description = "Booking successfully updated!", responseCode = "200", content = @Content(schema = @Schema(implementation = Booking.class))),
            @APIResponse(description = "Booking not found!", responseCode = "404"),
            @APIResponse(description = "The transaction amount and the flight price must be equal or the booking is filled!", responseCode = "404")
    })
    @Tag(name = "Flight Booking Service")
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

    @Operation(description = "Delete an existing booking using the bookingId.", summary = "Cancel a booking")
    @APIResponses({
            @APIResponse(description = "Booking successfully canceled!", responseCode = "200"),
            @APIResponse(description = "Booking not found!", responseCode = "404")
    })
    @Tag(name = "Flight Booking Service")
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
