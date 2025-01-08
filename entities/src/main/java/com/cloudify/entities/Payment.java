package com.cloudify.entities;

import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;


import java.io.Serializable;
import java.time.LocalDateTime;


import org.hibernate.annotations.NamedQueries;


import org.hibernate.annotations.NamedQuery;


@Entity
@Table(name = "payment")
@Schema(description = "Payment information")
public class Payment implements Serializable {


    @Schema(description = "paymentId", example = "123456789")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String paymentId;

    @Schema(description = "date", example = "2024-11-25T14:30:00")
    private LocalDateTime date;

    @Schema(description = "userId", example = "198772")
    private Long userId;


    @Schema(description = "sender")
    @OneToOne
    @JoinColumn(name = "userId")
    private User sender;

    @Schema(description = "paymentStatus", example = "ACCEPTED")
    private String paymentStatus;

    @Schema(description = "amount", example = "100.42")
    private double amount;

    @Schema(description = "currency", example = "USD")
    private String currency;

    @Schema(description = "flightId", example = "AB1234")
    private String flightId;

    @Schema(description = "transactionId", example = "TX123456789")
    private String transactionId;

    public Payment() {}

    // Parameterized constructor
    public Payment(String paymentId, LocalDateTime date, Long userId, User sender, String paymentStatus, double amount, String currency, String flightId, String transactionId) {
        this.paymentId = paymentId;
        this.date = date;
        this.userId = userId;
        this.sender = sender;
        this.paymentStatus = paymentStatus;
        this.amount = amount;
        this.currency = currency;
        this.flightId = flightId;
        this.transactionId = transactionId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
}
