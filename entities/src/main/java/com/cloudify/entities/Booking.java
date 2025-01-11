package com.cloudify.entities;

import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbTransient;
import java.io.Serializable;
import java.util.List;

@org.hibernate.annotations.NamedQueries(value =
        {
                @org.hibernate.annotations.NamedQuery(
                        name = "Booking.getBoking",
                        query = "SELECT p FROM Booking p WHERE p.bookingId  = :id"
                ),
                @org.hibernate.annotations.NamedQuery(
                        name = "Booking.deleteBoking",
                        query = "DELETE FROM Booking p WHERE p.bookingId = :id"
                ),
                @org.hibernate.annotations.NamedQuery(
                        name = "Booking.updateBoking",
                        query = "UPDATE Booking p SET p.passengers = :passengers, p.totalPrice = :totalPrice WHERE p.bookingId = :id"
                )
        }
)
@Entity
@Table(name = "booking")
@Schema(description = "Booking information")
public class Booking implements Serializable {

    @Schema(description = "bookingId", example = "BK987654")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookingId")
    private String bookingId;

    @JsonbTransient
    @Schema(description = "Flight")
    @OneToOne
    @JoinColumn(name = "flightId")
    private Flight flight;

    @JsonbTransient
    @Schema(description = "List<Passenger>")
    @ManyToMany(mappedBy = "bookingpassengers")
    private List<Passenger> passengers;

    @Schema(description = "totalPrice", example = "250.50")
    @Column(name = "totalprice")
    private double totalPrice;

    public Booking() {}

    // Parameterized constructor
    public Booking(String bookingId, Flight flight, List<Passenger> passengers, double totalPrice) {
        this.bookingId = bookingId;
        this.flight = flight;
        this.passengers = passengers;
        this.totalPrice = totalPrice;
    }

    public Booking(String bookingId, Flight flight) {
        this.bookingId = bookingId;
        this.flight = flight;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
