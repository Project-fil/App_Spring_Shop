package com.github.ratel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.LOCKED, reason = "You are not verified")
public class NotVerificationException extends RuntimeException {
}
