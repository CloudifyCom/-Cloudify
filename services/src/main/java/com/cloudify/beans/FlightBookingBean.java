package com.cloudify.beans;

import com.cloudify.entities.Booking;
import com.cloudify.entities.BookingRequest;
import com.cloudify.entities.Passenger;
import com.cloudify.entities.Payment;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class FlightBookingBean {
    public Response updateBooking(BookingRequest updatedBooking) {


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
}
