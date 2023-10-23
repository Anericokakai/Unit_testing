package com.unittest.unittesting.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerClass {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String ,String > handleUserNotFound(UserNotFoundException ex){

      Map<String ,String > errorMap= new HashMap<>();

      errorMap.put("errorMessage",ex.getMessage());

      return errorMap;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> invalidInputsException(MethodArgumentNotValidException ex){

        Map<String,String>  errorMap= new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error->{
                    errorMap.put(error.getField(),error.getDefaultMessage());
                });

        return errorMap;

    }

    @ExceptionHandler(UserExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)

    public Map<String,String> userAlreadyExist(UserExistException ex){

        Map<String,String> errorMap=new HashMap<>();

        errorMap.put("errorMessage", ex.getMessage());
        return  errorMap;

    }


}
