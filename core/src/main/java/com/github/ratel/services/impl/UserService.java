package com.github.ratel.services.impl;

import com.github.ratel.dto.UserRegDto;
import com.github.ratel.entity.Roles;
import com.github.ratel.entity.User;
import com.github.ratel.exceptions.EntityNotFound;
import com.github.ratel.payload.EntityStatus;
import com.github.ratel.repositories.RoleRepository;
import com.github.ratel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findById(long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User findUserById(long userId) {
       return userRepository.getById(userId);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User findByLoginAndPassword(String login, String password) {
        User user = findByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
     throw new EntityNotFound("Entity not found in db");
    }

    @Transactional
    public User saveUser(User user) {
        Roles userRoles = this.roleRepository.getById(1L);
        user.setRoles(Set.of(userRoles));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void updateUser(User user) {
        user.setUpdatedAt(new Date());
        userRepository.save(user);
    }

    public long createUser(UserRegDto userRegDto) {
        User user = new User();
        user.setFirstname(userRegDto.getFirstname());
        user.setLastname(userRegDto.getLastname());
        user.setEmail(userRegDto.getEmail());
        user.setLogin(userRegDto.getLogin());
        user.setPassword(userRegDto.getPassword());
        user.setPhone(userRegDto.getPhone());
        user.setAddress(userRegDto.getAddress());
        doesUserExist(user.getId());
        return this.userRepository.save(user).getId();
    }

    public User changeUserInfo(long userId, UserRegDto userRegDto) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Not found user!"));
        user.setFirstname(userRegDto.getFirstname());
        user.setLastname(userRegDto.getLastname());
        user.setEmail(userRegDto.getEmail());
        user.setLogin(userRegDto.getLogin());
        user.setPassword(userRegDto.getPassword());
        user.setPhone(userRegDto.getPhone());
        user.setAddress(userRegDto.getAddress());
        user.setUpdatedAt(new Date());
        return this.userRepository.save(user);
    }

    public void deleteUserById(long userId) {
        User user = this.userRepository.getById(userId);
        if(user.getStatus().equals(EntityStatus.on)) {
            user.setStatus(EntityStatus.off);
            this.updateUser(user);
        } else {
           throw new EntityNotFound("User has been deleted");
        }
    }

    private void doesUserExist(long userId) {
        if (this.userRepository.findById(userId).isPresent()) {
            throw new RuntimeException("This user already exists!");
        }
    }
}
