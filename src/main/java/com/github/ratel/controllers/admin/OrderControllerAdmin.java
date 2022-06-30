package com.github.ratel.controllers.admin;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@SecurityRequirement(name = "Authorization")
public interface OrderControllerAdmin {

    @PutMapping("order/update")
    ResponseEntity<Object> checkOrder(@PathVariable Long orderId);

}
