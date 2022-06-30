package com.github.ratel.controllers.admin.impl;

import com.github.ratel.controllers.admin.OrderControllerAdmin;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/app/shop/")
@RestController(value = "orderControllerAdminImpl")
public class OrderControllerAdminImpl implements OrderControllerAdmin {

    @Override
    public ResponseEntity<Object> checkOrder(Long orderId) {
        return null;
    }
}
