package com.github.ratel.repositories;

import com.github.ratel.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleEntityRepo extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName(String name);
}
