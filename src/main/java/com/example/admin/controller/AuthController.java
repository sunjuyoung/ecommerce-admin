package com.example.admin.controller;

import com.example.admin.dto.SignUpDto;
import com.example.admin.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody SignUpDto signUpDto){
        authService.register(signUpDto);
        return ResponseEntity.ok().body("User registered successfully");
    }

    @PostMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/logout")
    public String logout(){
        return "logout";
    }
}
