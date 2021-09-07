package com.github.ratel.repositories;

import com.github.ratel.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   Optional <User> findByEmail(String email);

}
