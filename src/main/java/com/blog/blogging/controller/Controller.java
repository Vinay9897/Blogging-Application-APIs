package com.blog.blogging.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogging.payloads.UserDto;
import com.blog.blogging.services.UserService;

@RestController
@RequestMapping("/api/users")
public class Controller {

	@Autowired
	private UserService userService;
	
	//Post create user
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	
	//get user by id
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> oneUser(@PathVariable("userId") Long uId) {
		return new ResponseEntity<>(this.userService.getUserById(uId),HttpStatus.OK);
	}
	
	// get list of all users
	@GetMapping("/")
	public List<UserDto> allUser() {
		return this.userService.getAllUsers();
	}
	
	//Update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable("userId") Long uId){
		UserDto updateduser = this.userService.updateUser(userDto, uId);
		return new ResponseEntity<>(updateduser,HttpStatus.OK);
	}
	
	//delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") Long uId){
		this.userService.deleteUser(uId);
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("message","User deleted successfully");
		return new ResponseEntity<Object>((map),HttpStatus.OK);
	}
	
	
}
