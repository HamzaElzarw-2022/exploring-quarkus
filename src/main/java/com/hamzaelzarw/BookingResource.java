package com.hamzaelzarw;

import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;
import java.time.LocalDateTime;
import java.util.List;

@Path("/bookings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingResource {

    public record CreateBookingRequest(Long serviceId, String scheduledAt, String address, String notes) {}
    public record BookingDTO(Long id, Long serviceId, String serviceName, LocalDateTime scheduledAt, String address, String notes) {}

    @GET
    @RolesAllowed("user")
    public List<BookingDTO> myBookings(@Context SecurityContext ctx) {
        String username = ctx.getUserPrincipal().getName();
        User user = User.find("username", username).firstResult();
        if (user == null) throw new NotFoundException("User not found");
        return Booking.<Booking>list("user", user).stream()
                .map(BookingResource::toDto)
                .toList();
    }

    @POST
    @Transactional
    @RolesAllowed("user")
    public BookingDTO create(CreateBookingRequest req, @Context SecurityContext ctx) {
        if (req == null || req.serviceId == null || req.scheduledAt == null || req.address == null || req.address.isBlank()) {
            throw new BadRequestException("Missing required fields");
        }
        ServiceItem service = ServiceItem.findById(req.serviceId);
        if (service == null) throw new NotFoundException("Service not found");

        String username = ctx.getUserPrincipal().getName();
        User user = User.find("username", username).firstResult();
        if (user == null) throw new NotFoundException("User not found");

        Booking b = new Booking();
        b.user = user;
        b.service = service;
        try {
            b.scheduledAt = LocalDateTime.parse(req.scheduledAt);
        } catch (Exception e) {
            throw new BadRequestException("scheduledAt must be ISO-8601, e.g. 2025-01-01T10:00:00");
        }
        b.address = req.address;
        b.notes = req.notes;
        b.persist();
        return toDto(b);
    }

    static BookingDTO toDto(Booking b) {
        return new BookingDTO(
                b.id,
                b.service != null ? b.service.id : null,
                b.service != null ? b.service.name : null,
                b.scheduledAt,
                b.address,
                b.notes
        );
    }
}

