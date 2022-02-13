package com.github.ratel.utils;

import com.github.ratel.exceptions.ConfirmPasswordException;
import com.github.ratel.exceptions.EntityAlreadyExistException;
import com.github.ratel.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CheckUtil {

    private final UserService userService;

    public void checkUserByEmail(String email) {
        if (Objects.nonNull(this.userService.checkUserByEmail(email))) {
            throw new EntityAlreadyExistException("Пользователь уже существует");
        }
    }
    public String checkPassAndConfirmPass(String pass, String confirmPass) {
        if (pass.equals(confirmPass)) {
            return confirmPass;
        } else {
            throw new ConfirmPasswordException("Пароли не совпадают");
        }
    }

}
