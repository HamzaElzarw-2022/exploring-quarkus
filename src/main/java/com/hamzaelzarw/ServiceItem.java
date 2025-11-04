package com.hamzaelzarw;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "services")
public class ServiceItem extends PanacheEntity {
    @Column(nullable = false)
    public String name;

    @Column(length = 1000)
    public String description;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    public Category category;

    @Column(precision = 10, scale = 2, nullable = false)
    public BigDecimal price;
}

