package com.example.admin.dto;

import com.example.admin.entity.Store;
import com.example.admin.entity.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreResponseDto {

    private Long id;

    private String name;


    private String email;
    private String username;
    private Long userId;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public StoreResponseDto(Store store){
        this.id = store.getId();
        this.name = store.getName();
        this.createdAt = store.getCreatedAt();
        this.updatedAt = store.getUpdatedAt();
        this.email = store.getUser().getEmail();
        this.username = store.getUser().getUsername();
        this.userId = store.getUser().getId();
    }
}
