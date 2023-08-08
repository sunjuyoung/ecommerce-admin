package com.example.admin.service;

import com.example.admin.dto.StoreCreateDto;
import com.example.admin.entity.Store;
import com.example.admin.entity.User;
import com.example.admin.repository.StoreRepository;
import com.example.admin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    public void createStore(StoreCreateDto storeCreateDto) {

        User user = userRepository.findById(storeCreateDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));

        if(storeRepository.existsByUser(user)){
            throw new IllegalArgumentException("이미 매장이 존재합니다.");
        }

        Store store = new Store(storeCreateDto.getName(), user);
        storeRepository.save(store);
    }
}
