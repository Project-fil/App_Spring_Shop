package com.github.ratel.services.impl;

import com.github.ratel.dto.UserDto;
import com.github.ratel.entity.User;
import com.github.ratel.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(long userId) {
        return userRepository.findById(userId);
    }

    public long createUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setPhone(userDto.getPhone());
        user.setAddress(userDto.getAddress());
        user.setRole(userDto.getRole());

        doesUserExist(user.getId());
        return userRepository.save(user).getId();
    }

    public User changeUserInfo(long userId, UserDto userDto) {
        User user = findUserById(userId).orElseThrow(() -> new RuntimeException("Not found user!"));

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setPhone(userDto.getPhone());
        user.setAddress(userDto.getAddress());
        user.setRole(userDto.getRole());

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
