package com.github.ratel.repositories;

import com.github.ratel.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {

   Optional <Roles> findByRole(String role);
}
