package com.github.ratel.utils;

import com.github.ratel.exceptions.ConfirmPasswordException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CheckUtil {

    public String checkPassAndConfirmPass(String pass, String confirmPass) {
        if (pass.equals(confirmPass)) {
            return confirmPass;
        } else {
            throw new ConfirmPasswordException("Пароли не совпадают");
        }
    }

}
