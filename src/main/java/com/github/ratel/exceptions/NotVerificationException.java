package com.github.ratel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.LOCKED, reason = "Пользователь не верефицирован")
public class NotVerificationException extends RuntimeException {
}
