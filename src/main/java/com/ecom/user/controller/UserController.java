package com.ecom.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.user.dto.LoginRequest;
import com.ecom.user.dto.RegisterRequest;
import com.ecom.user.dto.UserResponse;
import com.ecom.user.service.UserService;

@RestController
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
}
