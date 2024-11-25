package com.cloudify.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Payment implements Serializable {

    private String paymentId;
    private LocalDateTime date;
    private Long userId;
    //private User sender;
    private String paymentStatus;
    private double amount;
    private String currency;
    private String flightId;
    private String transactionId;

    public Payment() {}

    // Parameterized constructor
    public Payment(String paymentId, LocalDateTime date, Long userId, /*User sender,*/ String paymentStatus, double amount, String currency, String flightId, String transactionId) {
        this.paymentId = paymentId;
        this.date = date;
        this.userId = userId;
        //this.sender = sender;
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
}
