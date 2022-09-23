package com.example.userservice.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.userservice.domain.AppRole;

public interface RolesRepo extends JpaRepository<AppRole, Serializable>{

	AppRole findByName(String name);
}
