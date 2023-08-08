package com.example.admin.controller;

import com.example.admin.dto.AuthRequestDto;
import com.example.admin.dto.AuthResponseDto;
import com.example.admin.dto.SignUpDto;
import com.example.admin.service.AuthService;
import com.example.admin.validation.SignupValidator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth", description = "Auth API")
public class AuthController {

    private final AuthService authService;
    private final SignupValidator signupValidator;

    @InitBinder("signUpDto")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(signupValidator);
    }

    /**
     * 회원가입
     * @param signUpDto
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody SignUpDto signUpDto, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body("잘못된 요청입니다." +errors.getAllErrors().get(0).getDefaultMessage());
        }
        authService.register(signUpDto);
        return ResponseEntity.ok().body(signUpDto.getUsername()+"님 회원가입이 완료되었습니다. ");
    }

    /**
     * 로그인
     * @param authRequestDto
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequestDto authRequestDto, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors.getAllErrors().get(0).getDefaultMessage());
        }
        AuthResponseDto authResponseDto = authService.login(authRequestDto);

        return ResponseEntity.ok().body(authResponseDto);
    }

    @PostMapping("/logout")
    public String logout(){
        return "logout";
    }
}
