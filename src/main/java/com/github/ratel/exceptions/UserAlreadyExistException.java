package com.github.ratel.exceptions;

import com.github.ratel.exceptions.statuscode.StatusCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
public class UserAlreadyExistException extends RuntimeException {

    private Integer statusCode;
    private final String message;

    public UserAlreadyExistException(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public UserAlreadyExistException(StatusCode error) {
        this.statusCode = error.getCode();
        this.message = error.getMessage();
    }

    public UserAlreadyExistException(String message) {
        super(message);
        this.message = message;
    }

}
