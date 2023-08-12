package com.example.admin;

import com.example.admin.dto.SignUpDto;
import com.example.admin.service.AuthService;
import com.example.admin.service.UserService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}



	@Bean
	ApplicationRunner applicationRunner(AuthService authService){
		return args -> {
			// 1. 회원가입
			SignUpDto signUpDto = new SignUpDto();
			signUpDto.setUsername("test1");
			signUpDto.setEmail("test1@email.com");
			signUpDto.setPassword("1234");
			signUpDto.setPasswordConfirm("1234");
			authService.register(signUpDto);

		};
	}
}
