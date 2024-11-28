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
                @Server(url = "http://localhost:8080/v1", description = "Local Development Server"),
                @Server(url = "https://cloudify.com/v1", description = "Soon")
        },
        tags = {
                @Tag(name = "Flight Booking Service", description = "Everything about your booking"),
                @Tag(name = "Notification Service", description = "Access to flight-related notifications"),
                @Tag(name = "Payment Service", description = "Operations related to payment processing")
        }
)
@ApplicationPath("v1")
public class CloudifyApplication extends Application {
}
