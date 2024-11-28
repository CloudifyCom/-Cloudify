package com.cloudify.v1.viri;
import com.cloudify.entities.Flight;
import com.cloudify.entities.Notification;
import com.cloudify.entities.Payment;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Path("/payments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PaymentService {


    @Operation(description = "Retrieve the details of an existing payment using the paymentId.", summary = "Get payment details")
    @APIResponses({
            @APIResponse(description = "Successful retrieval of payment!", responseCode = "200", content = @Content(schema = @Schema(implementation = Payment.class))),
            @APIResponse(description = "Payment not found!", responseCode = "404")
    })
    @Tag(name = "Payment Service")
    @GET
    @Path("/{paymentId}")
    public Response getPayment(@PathParam("paymentId") String paymentId) {
        // Pridobimo payment iz baze podatkov
        //Payment payment = paymentDatabase.get(paymentId);
        String payment = "objekat";
        if (payment == null) {
            return Response.status(404).build();
        }
        return Response.ok(payment).build();
    }


    @Operation(description = "Process a payment for a specific booking.", summary = "Make a payment")
    @APIResponses({
            @APIResponse(description = "Payment successfully processed!", responseCode = "201", content = @Content(schema = @Schema(implementation = Payment.class))),
            @APIResponse(description = "Validation exception!", responseCode = "422"),
            @APIResponse(description = "Invalid input!", responseCode = "404")
    })
    @Tag(name = "Payment Service")
    @POST
    public Response makePayment(Payment paymentDetails) {

        //TO DO Sa Userom

        String paymentId = UUID.randomUUID().toString();
        Payment payment = new Payment(
                paymentId,
                paymentDetails.getDate(),
                paymentDetails.getUserId(),
                /*User sender,*/
                paymentDetails.getPaymentStatus(),
                paymentDetails.getAmount(),
                paymentDetails.getCurrency(),
                paymentDetails.getFlightId(),
                paymentDetails.getTransactionId());



        //paymentDatabase.put(payment.getId(), payment);
        return Response.status(201).entity(payment).build();
    }

    @Operation(description = "Update the status or other details of an existing payment.", summary = "Update payment details")
    @APIResponses({
            @APIResponse(description = "Payment successfully updated!", responseCode = "200", content = @Content(schema = @Schema(implementation = Payment.class))),
            @APIResponse(description = "Invalid payment data!", responseCode = "400")
    })
    @Tag(name = "Payment Service")
    @PUT
    @Path("/{paymentId}")
    public Response updatePayment(@PathParam("paymentId") String paymentId, String paymentStatus){//Payment updatedPayment) {
        //Proverimo ali obstaja payment v nasoj bazi
        //Payment existingPayment = paymentDatabase.get(paymentId);
        Payment existingPayment = new Payment();
        List<String> status = new ArrayList<>(Arrays.asList("ACCEPTED", "IN_PROGRESS", "REJECTED"));
        boolean obstaja = true; // ako podataka sa id obstaja v bazi podataka

        if (existingPayment!=null || obstaja) {

            // TO DO
            // Posodobimo listu obavestenih korisnika

            if(status.contains(paymentStatus.toUpperCase()) ) {
                existingPayment.setPaymentStatus(paymentStatus);
                return Response.ok(existingPayment).build();
            }

            return Response.status(422).build();

        } else {
            return Response.status(404).build();
        }
    }
}
