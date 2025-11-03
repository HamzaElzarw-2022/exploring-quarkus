package com.hamzaelzarw;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import io.smallrye.jwt.build.Jwt;
import java.util.Set;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @POST
    @Path("/register")
    @Transactional
    public User register(Request request) {
        // Create and persist a new User entity from the incoming request DTO
        User entity = new User();
        entity.username = request.username;
        entity.password = request.password; // NOTE: In a real app, always store a hashed password
        entity.persist();
        return entity;
    }

    @POST
    @Path("/login")
    public Response login(Request loginRequest) {
        User user = User.find("username", loginRequest.username).firstResult();
        if (user == null || !user.password.equals(loginRequest.password)) {
            throw new NotAuthorizedException("Invalid credentials");
        }

        String token = Jwt.issuer("example.com")
                .upn(user.username)
                .groups(Set.of("user"))
                .sign();

        Response response = new Response();
        response.token = token;

        return response;
    }

    public static class Request {
        public String username;
        public String password;
    }

    public static class Response {
        public String token;
    }
}
