package com.blog.blogappapis.controllers;

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

import com.blog.blogappapis.payloads.ApiResponse;
import com.blog.blogappapis.payloads.UserDto;
import com.blog.blogappapis.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		
		UserDto createUserDto = this.userService.createUser(userDto);
		
		return new ResponseEntity<UserDto>(createUserDto, HttpStatus.CREATED);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Integer userId ){
		UserDto updatedUserDto = this.userService.updateUser(userDto, userId);
		return new ResponseEntity<UserDto>(updatedUserDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId ){
		this.userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Entity Deleted Successfully",true),HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable Integer userId){
		UserDto userDto = this.userService.getUserById(userId);
		return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> allUsers = this.userService.getAllUsers();
		return new ResponseEntity<List<UserDto>>(allUsers,HttpStatus.OK);
	}
}
