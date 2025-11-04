package com.hamzaelzarw;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import io.smallrye.jwt.build.Jwt;
import java.time.Duration;
import java.util.Set;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    public record AuthRequest(String username, String password) {}
    public record TokenResponse(String token) {}

    @POST
    @Path("/register")
    @Transactional
    public User register(AuthRequest authRequest) {
        if (authRequest == null || authRequest.username() == null || authRequest.username().isBlank() || authRequest.password() == null || authRequest.password().isBlank()) {
            throw new BadRequestException("username and password are required");
        }
        if (User.find("username", authRequest.username()).firstResult() != null) {
            throw new WebApplicationException("username already exists", 409);
        }
        // Create and persist a new User entity from the incoming authRequest DTO
        User entity = new User();
        entity.username = authRequest.username();
        entity.password = authRequest.password(); // NOTE: In a real app, always store a hashed password
        entity.persist();
        return entity;
    }

    @POST
    @Path("/login")
    public TokenResponse login(AuthRequest authRequest) {
        if (authRequest == null || authRequest.username() == null || authRequest.password() == null) {
            throw new NotAuthorizedException("Invalid credentials");
        }
        User user = User.find("username", authRequest.username()).firstResult();
        if (user == null || !user.password.equals(authRequest.password())) {
            throw new NotAuthorizedException("Invalid credentials");
        }

        String token = Jwt.issuer("example.com")
                .expiresIn(Duration.ofDays(1)) // 1-day expiration
                .upn(user.username)
                .groups(Set.of("user"))
                .sign();

        return new TokenResponse(token);
    }
}

