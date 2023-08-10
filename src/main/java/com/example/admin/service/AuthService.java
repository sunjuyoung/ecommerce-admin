package com.example.admin.service;

import com.example.admin.auth.AuthUser;
import com.example.admin.dto.AuthRequestDto;
import com.example.admin.dto.AuthResponseDto;
import com.example.admin.dto.SignUpDto;
import com.example.admin.entity.Roles;
import com.example.admin.entity.User;
import com.example.admin.repository.UserRepository;
import com.example.admin.security.JwtService;
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

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public void register(SignUpDto signUpDto) {
        var user = User.builder()
                .username(signUpDto.getUsername())
                .email(signUpDto.getEmail())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .role(Roles.USER)
                .build();
       userRepository.save(user);

    }
        public AuthResponseDto login(AuthRequestDto requestDto){
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getEmail(),
                            requestDto.getPassword()
                    )
            );
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("입력하신 정보가 일치하지 않습니다, 이메일 또는 비밀번호를 확인해주세요."));
        if(!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("입력하신 정보가 일치하지 않습니다, 이메일 또는 비밀번호를 확인해주세요.");
        }

        var jwtToken = jwtService.generateToken(new AuthUser(user));
        log.info(jwtToken);
        return AuthResponseDto.builder()
                .email(user.getEmail())
                .role(user.getRole().name())
                .username(user.getUsername())
                .token(jwtToken)
                .build();
    }
}
