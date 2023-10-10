package com.unittest.unittesting.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(value = {
            UserNotFoundException.class
    })

    public ResponseEntity<Object> handleUserNotFoundException(
            UserNotFoundException userNotFoundException
    ){


        return  new ResponseEntity<>(userNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
    }
}
