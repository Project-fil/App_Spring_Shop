package com.github.ratel.services.impl;

import com.github.ratel.dto.OrderDto;
import com.github.ratel.entity.Order;
import com.github.ratel.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> findOrderById(long orderId) {
        return orderRepository.findById(orderId);
    }

    public long createOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setBuyer(orderDto.getBuyer());
        order.setItems(orderDto.getItems());
        order.setPrice(orderDto.getPrice());
        order.setCreatedAt(orderDto.getCreatedAt());

        return orderRepository.save(order).getId();
    }

    public Order changeOrderInfo(long orderId, OrderDto orderDto) {
        Order order = findOrderById(orderId).orElseThrow(() -> new RuntimeException("Not found order!"));
        order.setBuyer(orderDto.getBuyer());
        order.setItems(orderDto.getItems());
        order.setPrice(orderDto.getPrice());
        order.setCreatedAt(orderDto.getCreatedAt());

        return orderRepository.save(order);
    }

    public void deleteOrder(long orderId) {
        orderRepository.deleteById(orderId);
    }
}
