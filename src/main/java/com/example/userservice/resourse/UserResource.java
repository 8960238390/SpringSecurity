package com.example.userservice.resourse;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userservice.domain.AppRole;
import com.example.userservice.domain.AppUser;
import com.example.userservice.service.UserService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserResource {

	private final UserService userService;

	@GetMapping("/users")
	public ResponseEntity<List<AppUser>> getUsers(){
		
		return ResponseEntity.ok().body(userService.getUsers());
	}
	
	@PostMapping("/users/save")
	public ResponseEntity<AppUser> saveUser(@RequestBody AppUser user){
		
		AppUser savedUser = userService.saveUser(user);
		
		return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
	}
	
	@PostMapping("/roles/save")
	public ResponseEntity<AppRole> saveRoles(@RequestBody AppRole role){
		
		AppRole savedRole = userService.saveRole(role);
		
		return new ResponseEntity<>(savedRole,HttpStatus.CREATED);
	}
	
	@PostMapping("role/addtoUser")
	public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
		
		userService.addRoleToUser(form.getUsername(), form.getRolename());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}

@Data
class RoleToUserForm{
	
	private String username;
	private String rolename;
}
