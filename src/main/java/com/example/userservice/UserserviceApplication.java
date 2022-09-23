package com.example.userservice;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.userservice.domain.AppRole;
import com.example.userservice.domain.AppUser;
import com.example.userservice.service.UserService;

@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	CommandLineRunner run(UserService userService) {
		
		return args -> {
			
			userService.saveRole(new AppRole(null,"ROLE_USER"));
			userService.saveRole(new AppRole(null,"ROLE_MANAGER"));
			userService.saveRole(new AppRole(null,"ROLE_ADMIN"));
			userService.saveRole(new AppRole(null,"ROLE_SUPER_ADMIN"));
			
			userService.saveUser(new AppUser(null, "Md Murtaza", "murtu", "12345", new ArrayList<>()));
			userService.saveUser(new AppUser(null, "Md Sharif", "sharif", "12345", new ArrayList<>()));
			userService.saveUser(new AppUser(null, "Ishtiyaque", "ishti", "12345", new ArrayList<>()));
			userService.saveUser(new AppUser(null, "Saurabh jaiswal", "saurabh", "12345", new ArrayList<>()));
			
			userService.addRoleToUser("murtu", "ROLE_USER");
			userService.addRoleToUser("sharif", "ROLE_MANAGER");
			userService.addRoleToUser("ishti", "ROLE_ADMIN");
			userService.addRoleToUser("saurabh", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("saurabh", "ROLE_ADMIN");
			userService.addRoleToUser("saurabh", "ROLE_USER");
		};
	}

}
