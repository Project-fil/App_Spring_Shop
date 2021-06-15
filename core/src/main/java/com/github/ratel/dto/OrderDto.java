package com.github.ratel.dto;

import com.github.ratel.entity.OrderItem;
import com.github.ratel.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class OrderDto {

    private User buyer;
    private List<OrderItem> items;
    private BigDecimal price;
    private Date createdAt;
}
