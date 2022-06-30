package com.github.ratel.controllers.app.impl;

import com.github.ratel.controllers.app.interfaces.OrderControllerApp;
import com.github.ratel.payload.request.CreateOrderRequest;
import com.github.ratel.payload.request.UpdateOrderRequest;
import org.springframework.http.ResponseEntity;

public class OrderControllerAppImpl implements OrderControllerApp {

    @Override
    public ResponseEntity<Object> getById(Long orderId) {
        return null;
    }

    @Override
    public ResponseEntity<Object> getByUser(Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<Object> createOrder(CreateOrderRequest createOrderRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Object> updateOrder(UpdateOrderRequest updateOrderRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Object> deleteOrder(Long id) {
        return null;
    }

}
