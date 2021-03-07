package com.alzamer.ahmad.awsSpringbootReactImage.Controller;

import com.alzamer.ahmad.awsSpringbootReactImage.Exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler
{

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(UserNotFoundException exception)
    {
        log.error("User not found :"+exception.getMessage());
        return ResponseEntity.notFound().build();
    }


    @ExceptionHandler
    public ResponseEntity handleAllException(Exception exception)
    {
        log.error("User not found :"+exception.getMessage());
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
