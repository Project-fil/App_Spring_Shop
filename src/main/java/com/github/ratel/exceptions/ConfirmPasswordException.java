package com.github.ratel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Пароли не совпадают")
public class ConfirmPasswordException extends RuntimeException {
}
