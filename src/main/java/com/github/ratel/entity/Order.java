package com.github.ratel.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "BIGINT", unique = true)
    private long id;

    @Column(name = "order_item_id", nullable = false, columnDefinition = "BIGINT")
    private long orderItemId;

    @Column(name = "price", nullable = false, columnDefinition = "DECIMAL")
    private BigDecimal price;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP")
    private Date createdAt;

    @Column(name = "email", nullable = false, columnDefinition = "TEXT")
    private String email;

    @Column(name = "address", nullable = false, columnDefinition = "TEXT")
    private String address;

    public Order(
            long orderItemId,
            BigDecimal price,
            Date createdAt,
            String email,
            String address) {
        this.orderItemId = orderItemId;
        this.price = price;
        this.createdAt = createdAt;
        this.email = email;
        this.address = address;
    }
}
