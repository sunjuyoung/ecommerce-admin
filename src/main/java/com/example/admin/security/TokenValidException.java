package com.example.admin.security;

import io.jsonwebtoken.JwtException;

public class TokenValidException extends JwtException {

    public TokenValidException(String message) {
        super(message);
    }

    public TokenValidException(String message, Throwable cause) {
        super(message, cause);
    }
}
