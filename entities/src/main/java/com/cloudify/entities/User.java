package com.cloudify.entities;

import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;

@org.hibernate.annotations.NamedQueries(value =
        {
                @org.hibernate.annotations.NamedQuery(
                        name = "User.getUser",
                        query = "SELECT p FROM User p WHERE p.userId  = :id"
                ),
                @org.hibernate.annotations.NamedQuery(
                        name = "User.deleteUser",
                        query = "DELETE FROM User p WHERE p.userId = :id"
                ),
                @org.hibernate.annotations.NamedQuery(
                        name = "User.updateUser",
                        query = "UPDATE User p SET p.phoneNumber = :phoneNumber, p.email = :email WHERE p.userId = :id"
                )
        }
)
@Entity
@Table(name = "user")
@Schema(description = "User details")
public class User implements Serializable {

    @Schema(description = "Unique user identifier", example = "U123456")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private int userId;

    @Column(name = "name")
    @Schema(description = "Name of the user", example = "John Doe")
    private String name;

    @Column(name = "email")
    @Schema(description = "Email address of the user", example = "john.doe@example.com")
    private String email;

    @Column(name = "phonenumber")
    @Schema(description = "Phone number of the user", example = "+1234567890")
    private String phoneNumber;

    public User() {}

    // Parameterized constructor
    public User(int userId, String name, String email, String phoneNumber) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
