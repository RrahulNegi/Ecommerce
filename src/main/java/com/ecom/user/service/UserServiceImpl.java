package com.ecom.user.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.user.bean.Role;
import com.ecom.user.bean.User;
import com.ecom.user.dto.LoginRequest;
import com.ecom.user.dto.RegisterRequest;
import com.ecom.user.dto.UpdateProfileRequest;
import com.ecom.user.dto.UserResponse;
import com.ecom.user.repositroies.UserRepositroies;

import ch.qos.logback.core.joran.spi.HttpUtil.RequestMethod;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepositroies  userRepositories;

	
	@Override
	public UserResponse register(RegisterRequest request) {
		User user = new User(request.getFirstName(), request.getLastName(), request.getEmail(),
				request.getMobile(), request.getPassword(), Role.CUSTOMER, true, true, LocalDateTime.now() );
		
		user =  userRepositories.save(user);
		UserResponse response = new UserResponse();
		response.setId(user.getId());
		response.setFirstName(user.getFirstName());
		response.setLastName(user.getLastName());
		response.setMobile(user.getMobile());
		response.setRole(user.getRole());
		
		return response;
	}

	@Override
	public String login(LoginRequest request) {
		User user = userRepositories.findByEmailAndPassword(request.getEmail(), request.getPassword());
		if(user!=null) {
			return "Login Successfully";
		}else {
			return "Unsuccessfull login";
		}
	}

	@Override
	public UserResponse getUser(Long id) {
		return null;
	}

	@Override
	public List<UserResponse> getAllUsers() {
		return null;
	}

	@Override
	public UserResponse updateProfile(Long id, UpdateProfileRequest request) {
		return null;
	}

	@Override
	public void deleteUser(Long id) {
		
	}

}
