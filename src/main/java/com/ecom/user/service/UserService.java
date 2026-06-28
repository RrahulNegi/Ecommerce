package com.ecom.user.service;

import java.util.List;

import com.ecom.user.dto.LoginRequest;
import com.ecom.user.dto.RegisterRequest;
import com.ecom.user.dto.UpdateProfileRequest;
import com.ecom.user.dto.UserResponse;

public interface UserService {

    UserResponse register(RegisterRequest request);

    String login(LoginRequest request);

    UserResponse getUser(Long id);

    List<UserResponse> getAllUsers();

    UserResponse updateProfile(
            Long id,
            UpdateProfileRequest request);

    void deleteUser(Long id);
}