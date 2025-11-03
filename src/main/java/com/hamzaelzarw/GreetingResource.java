package com.hamzaelzarw;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;

@Path("/hello")
@Produces(MediaType.TEXT_PLAIN)
public class GreetingResource {

    @GET
    @Path("/public")
    public String hello() {
        return "Hello, anonymous user!";
    }

    @GET
    @Path("/secure")
    @RolesAllowed("user")
    public String secureHello(@Context SecurityContext ctx) {
        return "Hello, authenticated user! " + ctx.getUserPrincipal().getName();
    }
}
