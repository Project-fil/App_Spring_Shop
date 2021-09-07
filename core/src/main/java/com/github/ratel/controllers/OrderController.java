package com.github.ratel.controllers;

import com.github.ratel.dto.OrderDto;
import com.github.ratel.entity.Order;
import com.github.ratel.services.impl.OrderServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderServiceImpl;

    @GetMapping
    public List<Order> findAllOrders() {
        return orderServiceImpl.findAllOrders();
    }

    @GetMapping("/{orderId}")
    public Order findOrderById(@PathVariable long orderId) {
        return orderServiceImpl.findOrderById(orderId).orElseThrow(() -> new RuntimeException("Not found order!"));
    }

    @PostMapping
    public long createOrder(@RequestBody OrderDto orderDto) {
        return orderServiceImpl.createOrder(orderDto);
    }

    @PutMapping("/{orderId}")
    public Order changeOrderInfo(@PathVariable long orderId, @RequestBody OrderDto orderDto) {
        return orderServiceImpl.changeOrderInfo(orderId, orderDto);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable long orderId) {
        orderServiceImpl.deleteOrder(orderId);
    }
}
