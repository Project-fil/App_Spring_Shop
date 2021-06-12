package com.github.ratel.dto;

import com.github.ratel.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private String products;

    private BigDecimal price;

    private Date createdAt;

    private String email;

    private String address;

    public OrderDto(Order order) {
        this.products = order.getProducts();
        this.price = order.getPrice();
        this.createdAt = order.getCreatedAt();
        this.email = order.getEmail();
        this.address = order.getAddress();
    }
}
