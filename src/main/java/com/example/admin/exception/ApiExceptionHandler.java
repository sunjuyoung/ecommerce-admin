package com.example.admin.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.example.admin.controller")
public class ApiExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<?> handleApiRequestException(IllegalArgumentException e){
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
    }

    @ExceptionHandler(value = {ExpiredJwtException.class})
    public ResponseEntity<?> handleApiRequestException(ExpiredJwtException e){
        return ResponseEntity.status(401).body(e.getMessage());
    }

    @ExceptionHandler(value = {MalformedJwtException.class})
    public ResponseEntity<?> handleApiRequestException(MalformedJwtException e){
        return ResponseEntity.status(401).body(e.getMessage());
    }

}
