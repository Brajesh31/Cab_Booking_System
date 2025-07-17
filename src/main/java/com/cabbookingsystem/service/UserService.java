package com.cabbookingsystem.service;

import com.cabbookingsystem.dto.LoginRequest;
import com.cabbookingsystem.dto.UserRegisterRequest;
import com.cabbookingsystem.entity.User;

public interface UserService {
    String register(UserRegisterRequest request);
    String login(LoginRequest request);
    User getProfile(Long userId);
}
