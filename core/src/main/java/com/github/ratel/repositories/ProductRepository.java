package com.github.ratel.repositories;

import com.github.ratel.entity.enums.EntityStatus;
import com.github.ratel.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByStatus(EntityStatus status);

    Optional<Product> findProductByVendorCode(String vendorCode);

    Optional<Product> findProductByNameAndStatus(EntityStatus status, String name);

    Optional<Product> findByName(String name);
}
