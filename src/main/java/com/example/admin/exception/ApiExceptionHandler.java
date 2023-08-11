package com.example.admin.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.example.admin.controller")
public class ApiExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public String handleApiRequestException(IllegalArgumentException e){
        return e.getMessage();
    }
}
