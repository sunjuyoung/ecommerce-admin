package com.example.admin.repository;

import com.example.admin.dto.StoreResponseDto;
import com.example.admin.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface StoreRepositoryExtension {

    public List<StoreResponseDto> findAllByUser(Long userId);
}
