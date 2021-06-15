package com.github.ratel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "BIGINT", unique = true)
    private long id;

    @Column(name = "item_name", nullable = false, columnDefinition = "TEXT", unique = true)
    private String itemName;

    @Column(name = "quantity", nullable = false, columnDefinition = "BIGINT")
    private long quantity;

    @Column(name = "total_amount", nullable = false, columnDefinition = "DECIMAL")
    private BigDecimal totalAmount;

}
