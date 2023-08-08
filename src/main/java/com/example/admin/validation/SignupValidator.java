package com.example.admin.validation;

import com.example.admin.dto.SignUpDto;
import com.example.admin.repository.UserRepository;
import jakarta.validation.ConstraintViolation;

import jakarta.validation.executable.ExecutableValidator;
import jakarta.validation.metadata.BeanDescriptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.lang.annotation.Annotation;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class SignupValidator implements Validator {

    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return SignUpDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        SignUpDto signUpDto = (SignUpDto) object;

        if(!signUpDto.getPassword().equals(signUpDto.getPasswordConfirm())){
            errors.rejectValue("password", "wrong.value", "비밀번호가 일치하지 않습니다.");
        }

        if(userRepository.existsByEmail(signUpDto.getEmail())){
            errors.rejectValue("email", "wrong.value", "이미 사용중인 이메일입니다.");
        }

        if(userRepository.existsByUsername(signUpDto.getUsername())){
            errors.rejectValue("username", "wrong.value", "이미 사용중인 닉네임입니다.");
        }

    }
}
