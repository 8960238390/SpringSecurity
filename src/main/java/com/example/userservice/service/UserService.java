package com.example.userservice.service;

import java.util.List;

import com.example.userservice.domain.AppRole;
import com.example.userservice.domain.AppUser;

public interface UserService {

	AppUser saveUser(AppUser user);
	AppRole saveRole(AppRole role);
	void addRoleToUser(String userName, String roleName);
	AppUser getUser(String userName);
	List<AppUser> getUsers();
	
}
