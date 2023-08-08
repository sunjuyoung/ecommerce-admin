package com.example.admin.controller;

import com.example.admin.dto.StoreCreateDto;
import com.example.admin.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/store")
@Tag(name = "Store", description = "매장 API")
public class StoreController {




    private final StoreService storeService;

    /**
     * 매장 생성
     * @return
     */
    @Operation(
            summary = "매장 생성",
            description = "매장을 생성합니다."
    )
    @PostMapping("/create")
    public ResponseEntity<?> createStore(StoreCreateDto storeCreateDto){
        storeService.createStore(storeCreateDto);
        return ResponseEntity.ok().build();
    }

}
