package com.example.admin.controller;

import com.example.admin.dto.StoreCreateDto;
import com.example.admin.dto.StoreResponseDto;
import com.example.admin.service.StoreService;
import com.example.admin.validation.StoreCreateValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/store")
@Tag(name = "Store", description = "매장 API")
public class StoreController {

    private final StoreService storeService;
    private final StoreCreateValidator storeCreateValidator;

    @InitBinder("storeCreateDto")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(storeCreateValidator);
    }

    /**
     * 매장 생성
     * @param storeCreateDto
     */
    @Operation(
            summary = "매장 생성",
            description = "매장을 생성합니다."
    )
    @PostMapping("/create")
    public ResponseEntity<?> createStore(@Valid @RequestBody  StoreCreateDto storeCreateDto, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors.getAllErrors().get(0).getDefaultMessage());
        }
        Long storeId = storeService.createStore(storeCreateDto);
        return ResponseEntity.ok().body(storeId);
    }

    /**
     * 매장 조회
     * @param id
     * @param userId
     */
    @GetMapping(value = "/get/{id}",produces = "application/json")
    public ResponseEntity<?> getStore(@PathVariable Long id,  @RequestParam Long userId){
        if(userId == null){
            return ResponseEntity.badRequest().body("잘못된 요청입니다. 유저아이디 정보가 없습니다.");
        }
        StoreResponseDto response = storeService.getStoreById(id, userId);
        return ResponseEntity.ok().body(response);
    }


    /**
     * 유저별 매장 조회
     * @param userId
     */
    @GetMapping(value = "/{userId}",produces = "application/json")
    public ResponseEntity<?> getStoresByUserId(@PathVariable Long userId){
        if(userId == null){
            return ResponseEntity.badRequest().body("잘못된 요청입니다. 유저아이디 정보가 없습니다.");
        }
        List<StoreResponseDto> storesByUserId = storeService.getStoresByUserId(userId);
        return ResponseEntity.ok().body(storesByUserId);
    }


}
