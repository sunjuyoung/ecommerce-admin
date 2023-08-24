package com.example.admin.service;

import com.example.admin.dto.StoreCreateDto;
import com.example.admin.dto.StoreResponseDto;
import com.example.admin.entity.Store;
import com.example.admin.entity.User;
import com.example.admin.repository.StoreRepository;
import com.example.admin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;


    public Long createStore(StoreCreateDto storeCreateDto) {

        User user = userRepository.findById(storeCreateDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));

        Store store = new Store(storeCreateDto.getName(), user);
        Store newStore = storeRepository.save(store);
        return newStore.getId();
    }

    public StoreResponseDto getStoreById(Long id, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        Store store = storeRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new IllegalArgumentException("해당 매장이 없습니다."));
        return new StoreResponseDto(store);
    }

    public  List<StoreResponseDto> getStoresByUserId(Long userId) {
        if(!userRepository.existsUserById(userId)){
            throw new IllegalArgumentException("해당 유저가 없습니다.");
        }
        List<StoreResponseDto> dtoList = storeRepository.findAllByUser(userId);
        return dtoList;
    }

    public Long updateStoreById(Long id,  StoreCreateDto storeCreateDto) {
        Store store = storeRepository.findById(id).get();
        store.updateName(storeCreateDto.getName());
        return store.getId();
    }
}
