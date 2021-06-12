package com.github.ratel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name = "order_id", nullable = false, columnDefinition = "BIGINT", unique = true)
    private long order_id;

    @Column(name= "products", nullable = false, columnDefinition = "TEXT")
    private String products;

    @Column(name= "price", nullable = false, columnDefinition = "DECIMAL")
    private BigDecimal price;

    @Column(name= "createdAt", nullable = false, columnDefinition = "TEXT")
    private Date createdAt;

    @Column(name= "email", nullable = false, columnDefinition = "TEXT")
    private String email;

    @Column(name= "address", nullable = false, columnDefinition = "TEXT")
    private String address;
}
