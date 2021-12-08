package com.github.ratel.exceptions;

import com.github.ratel.payload.response.ErrorResponse;
import com.github.ratel.payload.response.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Objects;

@Slf4j
@ControllerAdvice
public class CatcherException extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String errorField = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getField)
                .findFirst().orElse(null);
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst().orElse(null);
        if (Objects.isNull(errorField)) log.error(ex.getMessage());
        else log.error("Error field [{}] with message: {}", errorField, errorMessage);
        return ResponseEntity.badRequest().body(new MessageResponse(errorMessage));
    }

    @ExceptionHandler(value = ConfirmPasswordException.class)
    public ResponseEntity<Object> handleConfirmPasswordException(ConfirmPasswordException ex, HttpServletRequest request) {
        log.error(ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(new ErrorResponse(
                        Instant.now(),
                        ex.getStatus(),
                        ex.getMessage(),
                        request.getRequestURI()
                )
        );
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, HttpServletRequest request) {
        log.error(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(new ErrorResponse(
                        Instant.now(),
                        ex.getStatusCode(),
                        ex.getMessage(),
                        request.getRequestURI()
                )
        );
    }

    @ExceptionHandler(value = InvalidTokenException.class)
    public ResponseEntity<Object> handleInvalidTokenException(InvalidTokenException ex, HttpServletRequest request) {
        log.error(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(new ErrorResponse(
                        Instant.now(),
                        ex.getStatusCode(),
                        ex.getMessage(),
                        request.getRequestURI()
                )
        );
    }

    @ExceptionHandler(value = UserAlreadyExistException.class)
    public ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException ex, HttpServletRequest request) {
        log.error(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(new ErrorResponse(
                        Instant.now(),
                        ex.getStatusCode(),
                        ex.getMessage(),
                        request.getRequestURI()
                )
        );
    }

    @ExceptionHandler(value = UnverifiedException.class)
    public ResponseEntity<Object> handleUnverifiedException(UnverifiedException ex, HttpServletRequest request) {
        log.error(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(new ErrorResponse(
                        Instant.now(),
                        ex.getStatusCode(),
                        ex.getMessage(),
                        request.getRequestURI()
                )
        );
    }

    @ExceptionHandler(value = WrongUserEmail.class)
    public ResponseEntity<Object> handleWrongUserEmail(WrongUserEmail ex, HttpServletRequest request) {
        log.error(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(new ErrorResponse(
                        Instant.now(),
                        ex.getStatusCode(),
                        ex.getMessage(),
                        request.getRequestURI()
                )
        );
    }

}
