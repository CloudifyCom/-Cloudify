package com.cloudify.entities;

import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;

@org.hibernate.annotations.NamedQueries(value =
        {
            @org.hibernate.annotations.NamedQuery(
                    name = "Passenger.getPassenger",
                    query = "SELECT p FROM Passenger p WHERE p.passportNumber  = :id"
            ),
            @org.hibernate.annotations.NamedQuery(
                    name = "Passenger.deletePassenger",
                    query = "DELETE FROM Passenger p WHERE p.passportNumber = :id"
            ),
            @org.hibernate.annotations.NamedQuery(
                    name = "Passenger.updatePassenger",
                    query = "UPDATE Passenger p SET p.firstName = :name, p.lastName = :lastName WHERE p.passportNumber = :id"
            )
        }
)
@Entity
@Table(name = "passenger")
@Schema(description = "Passenger information")
public class Passenger implements Serializable {

    @Schema(description = "Passenger's name", example = "John")
    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Schema(description = "Passenger's lastname", example = "Doe")
    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Schema(description = "Passenger's passportNumber", example = "P987654321")
    @Id
    @Column(name = "passportnumber", unique = true, nullable = false)
    private String passportNumber;

    public Passenger() {}

    public Passenger(String firstName, String lastName, String passportNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                '}';
    }

}
