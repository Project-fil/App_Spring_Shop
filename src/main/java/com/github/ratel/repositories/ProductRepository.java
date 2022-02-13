package com.github.ratel.repositories;

import com.github.ratel.entity.Product;
import com.github.ratel.entity.enums.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
