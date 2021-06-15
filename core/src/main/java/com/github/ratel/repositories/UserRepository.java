package com.github.ratel.repositories;

import com.github.ratel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);

}
