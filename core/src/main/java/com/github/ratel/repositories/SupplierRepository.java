package com.github.ratel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.function.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
