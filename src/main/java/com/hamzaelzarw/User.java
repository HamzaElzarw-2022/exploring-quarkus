package com.hamzaelzarw;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends PanacheEntity {
    @Column(nullable = false, unique = true, length = 100)
    public String username;
    public String password; // hashed password in real app
}
