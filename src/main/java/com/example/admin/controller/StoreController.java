package com.example.admin.controller;

import com.example.admin.dto.StoreCreateDto;
import com.example.admin.service.StoreService;
import com.example.admin.validation.StoreCreateValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

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
     * @return
     */
    @Operation(
            summary = "매장 생성",
            description = "매장을 생성합니다."
    )
    @PostMapping("/create")
    public ResponseEntity<String> createStore(@Valid @RequestBody  StoreCreateDto storeCreateDto, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors.getAllErrors().get(0).getDefaultMessage());
        }
        storeService.createStore(storeCreateDto);
        return ResponseEntity.ok().body(storeCreateDto.getName()+" 매장이 생성되었습니다.");

    }


}
