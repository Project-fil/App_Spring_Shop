package com.github.ratel.services.impl;

import com.github.ratel.entity.Product;
import com.github.ratel.entity.enums.EntityStatus;
import com.github.ratel.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl {

    // TODO: 06.02.2022 refactor all

    private final ProductRepository productRepository;

}
