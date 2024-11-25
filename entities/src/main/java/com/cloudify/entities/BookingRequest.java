package com.cloudify.entities;

import java.io.Serializable;
import java.util.List;


public class BookingRequest implements Serializable {

    //Proces rezervisanja leta preko BookingRequesta

    private Flight flight;
    private Passenger passenger;
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
