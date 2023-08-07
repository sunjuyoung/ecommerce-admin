package com.example.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {


    @GetMapping("/users")
    public String getUsers(){
        return "users";
    }




    @GetMapping("/current-user")
    public String getLoggedInUser(Principal principal){
        return principal.getName();
    }

}
