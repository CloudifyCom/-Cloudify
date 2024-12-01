package com.cloudify.v1;
import com.cloudify.entities.*;
import org.eclipse.microprofile.openapi.annotations.Components;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title = "Swagger !Cloudify - OpenAPI 3.0",
                version = "1.0.11",
                description = "The purpose of the project is to develop a web application for the airline, which enables flight search, reservations, flight registration and flight status tracking. The application solves the problem of inefficient and disconnected systems, which make it difficult for users to register for a flight and inform about the status of the flight. Our solution uses a microservice architecture that ensures greater reliability, scalability and responsiveness."
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Local Development Server"),
                @Server(url = "https://cloudify.com", description = "Soon")
        },
        tags = {
                @Tag(name = "Flight Booking Service", description = "Everything about your booking"),
                @Tag(name = "Notification Service", description = "Access to flight-related notifications"),
                @Tag(name = "Payment Service", description = "Operations related to payment processing")
        },
        components = @Components(
                schemas = {
                        @Schema(name = "FlightBookingRequest", description = "Flight booking request", implementation = BookingRequest.class)
                        @Schema(name = "Flight", description = "Flight details", implementation = Flight.class),
                        @Schema(name = "Booking", description = "Booking details", implementation = Booking.class),
                        @Schema(name = "BookingRequest", description = "Booking request details", implementation = BookingRequest.class),
                        @Schema(name = "Passenger", description = "Passenger details", implementation = Passenger.class),
                        @Schema(name = "LoyaltyMember", description = "Loyalty member details", implementation = LoyaltyMember.class),
                        @Schema(name = "LoyaltyEnrollment", description = "Loyalty enrollment details", implementation = LoyaltyEnrollment.class),
                        @Schema(name = "User", description = "User details", implementation = User.class),
                        @Schema(name = "SeatAvailability", description = "Seat availability details", implementation = SeatAvailability.class),
                        @Schema(name = "SeatUpdate", description = "Seat update details", implementation = SeatUpdate.class),
                        @Schema(name = "Payment", description = "Payment details", implementation = Payment.class),
                        @Schema(name = "Notification", description = "Notification details", implementation = Notification.class)
                }
        )
)
@ApplicationPath("v1")
public class CloudifyApplication extends Application {
}
