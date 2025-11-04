package com.hamzaelzarw;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking extends PanacheEntity {
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    public ServiceItem service;

    @Column(name = "scheduled_at", nullable = false)
    public LocalDateTime scheduledAt;

    @Column(name = "address", nullable = false, length = 1000)
    public String address;

    @Column(name = "notes", length = 2000)
    public String notes;
}

