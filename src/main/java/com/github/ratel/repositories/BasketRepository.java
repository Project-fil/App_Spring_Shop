package com.github.ratel.repositories;

import com.github.ratel.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Cart, Long> {
}
