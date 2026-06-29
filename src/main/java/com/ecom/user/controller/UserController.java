package com.ecom.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.user.dto.LoginRequest;
import com.ecom.user.dto.RegisterRequest;
import com.ecom.user.dto.UpdateProfileRequest;
import com.ecom.user.dto.UserResponse;
import com.ecom.user.service.UserService;

@RestController
@RequestMapping("/user") 
public class UserController {
	
	@Autowired
	UserService userService;
	
	 @PostMapping("/register")
	 public UserResponse register(@RequestBody RegisterRequest registerRequest) {
		 
		return  userService.register(registerRequest);
	 }
	 
	 @PostMapping("/login")
	 public String login(@RequestBody LoginRequest request) {
		 return userService.login(request);
	 }
	 
	 @GetMapping("/{id}")
	 public UserResponse getUserById(@PathVariable Long id) {
		 return userService.getUser(id);
	 }
	 
	 @GetMapping("/")
	 public List<UserResponse> getAllUser(){
		 return userService.getAllUsers();
	 }
	 
	 @PostMapping("/{id}")
	 public UserResponse updateUser(@RequestBody UpdateProfileRequest request, @PathVariable Long id) {
		 return userService.updateProfile(id, request);
	 }
}
