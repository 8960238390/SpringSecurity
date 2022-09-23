package com.example.userservice.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.userservice.domain.AppUser;

public interface UserRepo extends JpaRepository<AppUser, Serializable>{

	AppUser findByUsername(String userName);
}

