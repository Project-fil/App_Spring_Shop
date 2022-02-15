package com.github.ratel.controllers.app.impl;

import com.github.ratel.controllers.ApiSecurityHeader;
import com.github.ratel.services.ProductService;
import com.github.ratel.services.SubcategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductControllerAppImpl implements ApiSecurityHeader {

    private final ProductService productService;

    private final SubcategoryService subcategoryService;



}
