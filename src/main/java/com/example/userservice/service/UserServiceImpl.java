package com.example.userservice.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.userservice.domain.AppRole;
import com.example.userservice.domain.AppUser;
import com.example.userservice.repo.RolesRepo;
import com.example.userservice.repo.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

	private UserRepo userRepo;
	private RolesRepo roleRepo;
	private PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepo userRepo, RolesRepo roleRepo,PasswordEncoder passwordEncoder) {
		super();
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public AppUser saveUser(AppUser user) {

		log.info("saving new user {} to the database", user.getName());

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

	@Override
	public AppRole saveRole(AppRole role) {

		log.info("saving new role to {} the dadabase", role.getName());

		return roleRepo.save(role);
	}

	@Override
	public void addRoleToUser(String userName, String roleName) {

		log.info("adding role {} to user {}", roleName, userName);

		AppUser user = userRepo.findByUsername(userName);
		AppRole role = roleRepo.findByName(roleName);

		if (user != null && role != null) {
			user.getRoles().add(role);
		}
	}

	@Override
	public AppUser getUser(String userName) {

		log.info("Fetching user {}", userName);
		return userRepo.findByUsername(userName);
	}

	@Override
	public List<AppUser> getUsers() {

		log.info("Fetching all users");
		return userRepo.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		AppUser user = userRepo.findByUsername(username);

		if (user == null) {
			log.error("User not found in the database");
			throw new UsernameNotFoundException("User not found in the database");
		}

		log.info("User found in database : {}", username);

		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});

		return new User(user.getUsername(), user.getPassword(), authorities);
	}

}
