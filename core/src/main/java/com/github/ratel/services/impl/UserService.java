package com.github.ratel.services.impl;

import com.github.ratel.dto.UserRegDto;
import com.github.ratel.entity.RoleEntity;
import com.github.ratel.entity.User;
import com.github.ratel.repositories.RoleEntityRepo;
import com.github.ratel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleEntityRepo roleEntityRepo;

    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, RoleEntityRepo roleEntityRepo, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleEntityRepo = roleEntityRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(long userId) {
        return userRepository.findById(userId);
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
        return null;
    }

    public User saveUser(User user) {
        RoleEntity userRole = roleEntityRepo.findByName("ROLE_USER");
        user.setRole(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void updateUser(User user) {
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

        doesUserExist(user.getUserId());
        return userRepository.save(user).getUserId();
    }

    public User changeUserInfo(long userId, UserRegDto userRegDto) {
        User user = findUserById(userId).orElseThrow(() -> new RuntimeException("Not found user!"));

        user.setFirstname(userRegDto.getFirstname());
        user.setLastname(userRegDto.getLastname());
        user.setEmail(userRegDto.getEmail());
        user.setLogin(userRegDto.getLogin());
        user.setPassword(userRegDto.getPassword());
        user.setPhone(userRegDto.getPhone());
        user.setAddress(userRegDto.getAddress());
        user.setUpdatedAt(new Date());
        return userRepository.save(user);
    }

    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

    private void doesUserExist(long userId) {
        if (findUserById(userId).isPresent()) {
            throw new RuntimeException("This user already exists!");
        }
    }
}
