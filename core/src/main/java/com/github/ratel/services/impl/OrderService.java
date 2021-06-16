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
        order.setOrderItemId(orderDto.getOrder_item_id());
        order.setCreatedAt(orderDto.getCreatedAt());
        order.setPrice(orderDto.getPrice());
        order.setEmail(orderDto.getEmail());
        order.setAddress(orderDto.getAddress());

        return orderRepository.save(order).getOrderId();
    }

    public Order changeOrderInfo(long orderId, OrderDto orderDto) {
        Order order = findOrderById(orderId).orElseThrow(() -> new RuntimeException("Not found order!"));
        order.setOrderItemId(orderDto.getOrder_item_id());
        order.setCreatedAt(orderDto.getCreatedAt());
        order.setPrice(orderDto.getPrice());
        order.setEmail(orderDto.getEmail());
        order.setAddress(orderDto.getAddress());

        return orderRepository.save(order);
    }

    public void deleteOrder(long orderId) {
        orderRepository.deleteById(orderId);
    }
}
