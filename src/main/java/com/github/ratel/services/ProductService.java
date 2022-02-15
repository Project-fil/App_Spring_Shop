package com.github.ratel.services;

import com.github.ratel.entity.Product;
import com.github.ratel.entity.Subcategory;

import java.util.List;

public interface ProductService {

    List<Product> findAll(Subcategory subcategory);

    Product findById(long id);

    Product findByVendorCode(String code);

    Product create(Product product, Subcategory subcategory);

    Product update(Product product);

    void deleteById(long id);
}
