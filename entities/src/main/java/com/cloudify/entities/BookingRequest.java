package com.cloudify.entities;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(description = "BookingRequest information")
public class BookingRequest implements Serializable {

    //Proces rezervisanja leta preko BookingRequesta

    @Schema(description = "flight")
    private Flight flight;

    @Schema(description = "Passenger")
    private Passenger passenger;

    @Schema(description = "Payment")
    private Payment paymentMethod;

    public BookingRequest() {}

    // Parameterized constructor
    public BookingRequest(Flight flight, Passenger passenger, Payment paymentMethod) {
        this.flight = flight;
        this.passenger = passenger;
        this.paymentMethod = paymentMethod;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Payment getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Payment paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
