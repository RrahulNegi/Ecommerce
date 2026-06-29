package com.ecom.user.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.user.bean.Role;
import com.ecom.user.bean.User;
import com.ecom.user.dto.LoginRequest;
import com.ecom.user.dto.RegisterRequest;
import com.ecom.user.dto.UpdateProfileRequest;
import com.ecom.user.dto.UserResponse;
import com.ecom.user.exception.UserNotFoundException;
import com.ecom.user.repositroies.UserRepositroies;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepositroies  userRepositories;

	
	@Override
	public UserResponse register(RegisterRequest request) {
		User user = new User(request.getFirstName(), request.getLastName(), request.getEmail(),
				request.getMobile(), request.getPassword(), Role.CUSTOMER, true, true, LocalDateTime.now() );
		user =  userRepositories.save(user);
		UserResponse response = new UserResponse(user.getId(),user.getFirstName(),user.getLastName(),user.getEmail(),user.getMobile(),user.getRole());
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
		User e = userRepositories.findById(id).orElseThrow(()->new UserNotFoundException("User not found") );
		UserResponse userResponse = new UserResponse(e.getId(), e.getFirstName(),
				e.getLastName(), e.getEmail(), e.getMobile(), e.getRole());
		return userResponse;
		
	}

	@Override
	public List<UserResponse> getAllUsers() {
		List<User> userList = userRepositories.findAll();
		List<UserResponse> userResponseList =userList.stream().map(e->new UserResponse(e.getId(), e.getFirstName(),
				e.getLastName(), e.getEmail(), e.getMobile(), e.getRole())).collect(Collectors.toList());
		return userResponseList;
	}

	@Override
	public UserResponse updateProfile(Long id, UpdateProfileRequest request) {
		User user = userRepositories.findById(id).map(e->{
			e.setFirstName(request.getFirstName());
			e.setLastName(request.getLastName());
			e.setMobile(request.getMobile());
			return userRepositories.save(e);
		}).orElseThrow(()->new UserNotFoundException("User not found"));
		
		UserResponse userRes = new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getMobile(), user.getRole());
		
		return userRes;
	}

	@Override
	public void deleteUser(Long id) {
		
	}

}
