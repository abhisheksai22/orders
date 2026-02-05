package com.abhi.orders.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String description;
    private Double price;
    private Long inventoryId;

    @CreationTimestamp
    @Column(updatable = false)
    private OffsetDateTime createdTimestamp;
    @UpdateTimestamp
    private OffsetDateTime lastUpdatedTimestamp;

}