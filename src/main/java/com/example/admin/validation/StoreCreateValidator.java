package com.example.admin.validation;

import com.example.admin.dto.AuthRequestDto;
import com.example.admin.dto.StoreCreateDto;
import com.example.admin.repository.StoreRepository;
import com.example.admin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
@RequiredArgsConstructor
public class StoreCreateValidator implements Validator {


    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return StoreCreateDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {

        StoreCreateDto storeCreateDto = (StoreCreateDto) object;
        if(storeRepository.existsByName(storeCreateDto.getName())){
            errors.rejectValue("name", "wrong.value", "이미 존재하는 매장 이름입니다.");
        }
        if(!userRepository.existsById(storeCreateDto.getUserId())){
            errors.rejectValue("userId", "wrong.value", "존재하지 않는 유저입니다.");
        }


    }
}
