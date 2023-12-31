package com.unittest.unittesting.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceotionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)

    public Map<String,String > handleInvalidInputs(MethodArgumentNotValidException ex){
        Map<String,String > errorMap=new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error->{
                    errorMap.put(error.getField(), error.getDefaultMessage());
                });
        return errorMap;
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String,String > handleBusinnesException(UserNotFoundException ex){

        Map<String,String>  errorMap= new HashMap<>();

        errorMap.put("errorMessage",ex.getMessage());
        return errorMap;
    }

    @ExceptionHandler(UserExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String ,String > handleUserExistException(UserExistException ex){
        Map<String ,String > errorMap=new HashMap<>();
        errorMap.put("errorMessage",ex.getMessage());
        return errorMap;
    }
}
