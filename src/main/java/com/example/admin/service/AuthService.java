package com.example.admin.service;

import com.example.admin.dto.AuthRequestDto;
import com.example.admin.dto.AuthResponseDto;
import com.example.admin.dto.SignUpDto;
import com.example.admin.entity.User;
import com.example.admin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public void register(SignUpDto signUpDto) {
        var user = User.builder()
                .username(signUpDto.getUsername())
                .email(signUpDto.getEmail())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .role("USER")
                .build();
       userRepository.save(user);

    }
        public AuthResponseDto login(AuthRequestDto requestDto){
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return AuthResponseDto.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .username(user.getUsername())
                .build();
    }
}
