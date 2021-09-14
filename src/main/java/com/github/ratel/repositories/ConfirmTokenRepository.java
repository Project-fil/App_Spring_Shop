package com.github.ratel.repositories;

import com.github.ratel.entity.ConfirmToken;
import com.github.ratel.entity.enums.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ConfirmTokenRepository extends JpaRepository<ConfirmToken, Long> {

    Optional<ConfirmToken> findByToken(String token);

    @Modifying
    @Query(value = "update ConfirmToken c set c.status=:status where c.id=:id")
    void update(@Param(value = "id") Long id, @Param(value = "status") EntityStatus status);

}
