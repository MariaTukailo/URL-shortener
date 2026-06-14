package com.example.urlshortener.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(ResponseStatusException.class)
public ResponseEntity<ExceptionDto> handleNotFound(ResponseStatusException exception) {
    log.error("Not found exception");
    ExceptionDto exceptionDto=new ExceptionDto( HttpStatus.NOT_FOUND.getReasonPhrase(),
        exception.getMessage(),
        HttpStatus.NOT_FOUND.value(),
        LocalDateTime.now());

    return new ResponseEntity<>(exceptionDto,HttpStatus.NOT_FOUND);
}

@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDto> handleValidation(MethodArgumentNotValidException exception){
    log.error("Validation exception");
    ExceptionDto exceptionDto=new ExceptionDto( HttpStatus.BAD_REQUEST.getReasonPhrase(),
            exception.getMessage(),
            HttpStatus.BAD_REQUEST.value(),
            LocalDateTime.now());
    return new ResponseEntity<>(exceptionDto,HttpStatus.BAD_REQUEST);
}

@ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionDto> handleBadCredentials(BadCredentialsException exception){
    log.error("Unauthorized exception");
    ExceptionDto exceptionDto=new ExceptionDto( "Unauthorized",
            "Invalid username or password",
            401,
            LocalDateTime.now());
    return new ResponseEntity<>(exceptionDto,HttpStatus.UNAUTHORIZED);

}
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleException(Exception exception){
    log.error("Server exception");
    ExceptionDto exceptionDto=new ExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            exception.getMessage(),
            500,
            LocalDateTime.now());
    return new ResponseEntity<>(exceptionDto,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
