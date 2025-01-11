package com.cloudify.beans;

import com.cloudify.entities.Passenger;
import com.cloudify.entities.Payment;

import java.util.UUID;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PaymentBean {

    @PersistenceContext(unitName = "filme-jpa")
    private EntityManager em;

    public Passenger getPayment(String paymentId) {
        // Pridobimo payment iz baze podatkov
        //Payment payment = paymentDatabase.get(paymentId);
        Passenger passenger;
        System.out.println("Received paymentId: " + paymentId);
        try {
            TypedQuery<Passenger> query = em.createNamedQuery("Passenger.getPassenger", Passenger.class);
            query.setParameter("id", paymentId);
            passenger = query.getSingleResult();
        } catch(Exception e) {
            System.err.println("Error retrieving Passenger: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        if(passenger == null) {
            System.out.println("ALO");
        }
        return passenger;
    }

    public Payment postPayment(Payment paymentDetails) {
        String paymentId = UUID.randomUUID().toString();
        /*paymentId,
                paymentDetails.getDate(),
                paymentDetails.getUserId(),
                paymentDetails.getSender(),  /// <------------------------
                paymentDetails.getPaymentStatus(),
                paymentDetails.getAmount(),
                paymentDetails.getCurrency(),
                paymentDetails.getFlightId(),
                paymentDetails.getTransactionId()*/

        //paymentDatabase.put(payment.getId(), payment);
        return new Payment(
                /*paymentId,
                paymentDetails.getDate(),
                paymentDetails.getUserId(),
                paymentDetails.getSender(),  /// <------------------------
                paymentDetails.getPaymentStatus(),
                paymentDetails.getAmount(),
                paymentDetails.getCurrency(),
                paymentDetails.getFlightId(),
                paymentDetails.getTransactionId()*/);
    }

}
