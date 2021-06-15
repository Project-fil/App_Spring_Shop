package com.github.ratel.repositories;

import com.github.ratel.payload.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleEntityRepo extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}
