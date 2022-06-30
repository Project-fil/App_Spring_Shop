package com.github.ratel.controllers.app.interfaces;

import com.github.ratel.payload.response.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProductControllerApp {

    @GetMapping("free/product/find/all")
    ResponseEntity<List<ProductResponse>> findAll(@RequestParam long subcategoryId);

    @GetMapping("free/product/find/id")
    ResponseEntity<ProductResponse> findById(@RequestParam long id);



}
