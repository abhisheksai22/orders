package com.abhi.orders.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_detail_id", nullable = false)
    @ToString.Exclude
    private UserDetail userDetail;

    @OneToMany(
            mappedBy = "orderDetail",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    private List<CartItem> cartItems;

    @CreationTimestamp
    @Column(updatable = false)
    private OffsetDateTime createdTimestamp;

    @UpdateTimestamp
    private OffsetDateTime lastUpdatedTimestamp;
}

