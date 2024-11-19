package com.cloudify.v1.viri;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("notification")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NotificationService {

    @GET
    //@Produces(MediaType.APPLICATION_JSON)
    public Response getNotification() {
        // Ustvarimo preprost JSON odgovor
        String jsonOdgovor = "Hello World";

        // Vrni odgovor s statusom 200 (OK)
        return Response.ok("Hello World").build();
    }
}
