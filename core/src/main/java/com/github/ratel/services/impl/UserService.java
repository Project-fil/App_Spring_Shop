package com.github.ratel.services.impl;

import com.github.ratel.dto.UserRegDto;
import com.github.ratel.entity.RoleEntity;
import com.github.ratel.entity.User;
import com.github.ratel.repositories.RoleEntityRepo;
import com.github.ratel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository UserRepository;

    private RoleEntityRepo roleEntityRepo;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository UserRepository, RoleEntityRepo roleEntityRepo, PasswordEncoder passwordEncoder) {
        this.UserRepository = UserRepository;
        this.roleEntityRepo = roleEntityRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAllUsers() {
        return UserRepository.findAll();
    }

    public Optional<User> findUserById(long userId) {
        return UserRepository.findById(userId);
    }

    public User findByLogin(String login) {
        return UserRepository.findByLogin(login);
    }

    public User findByLoginAndPassword(String login, String password) {
        User user = findByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getHashPassword())) {
                return user;
            }
        }
        return null;
    }

    public User saveUser(User user) {
        RoleEntity userRole = roleEntityRepo.findByName("ROLE_USER");
        user.setRole(userRole);
        user.setHashPassword(passwordEncoder.encode(user.getHashPassword()));
        return UserRepository.save(user);
    }

    public long createUser(UserRegDto userRegDto) {
        User user = new User();
        user.setFirstname(userRegDto.getFirstname());
        user.setLastname(userRegDto.getLastname());
        user.setEmail(userRegDto.getEmail());
        user.setLogin(userRegDto.getLogin());
        user.setHashPassword(userRegDto.getHashPassword());
        user.setPhone(userRegDto.getPhone());
        user.setAddress(userRegDto.getAddress());

        doesUserExist(user.getUserId());
        return UserRepository.save(user).getUserId();
    }

    public User changeUserInfo(long userId, UserRegDto userRegDto) {
        User user = findUserById(userId).orElseThrow(() -> new RuntimeException("Not found user!"));

        user.setFirstname(userRegDto.getFirstname());
        user.setLastname(userRegDto.getLastname());
        user.setEmail(userRegDto.getEmail());
        user.setLogin(userRegDto.getLogin());
        user.setHashPassword(userRegDto.getHashPassword());
        user.setPhone(userRegDto.getPhone());
        user.setAddress(userRegDto.getAddress());

        return UserRepository.save(user);
    }

    public void deleteUser(long userId) {
        UserRepository.deleteById(userId);
    }

    private void doesUserExist(long userId) {
        if (findUserById(userId).isPresent()) {
            throw new RuntimeException("This user already exists!");
        }
    }
}
