package com.cabbookingsystem.service.impl;

import com.cabbookingsystem.dto.*;
import com.cabbookingsystem.entity.User;
import com.cabbookingsystem.enums.Role;
import com.cabbookingsystem.repository.UserRepository;
import com.cabbookingsystem.service.UserService;
import com.cabbookingsystem.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public String register(UserRegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .createdAt(LocalDateTime.now().toString())
                .build();
        userRepository.save(user);
        return "User registered successfully!";
    }

    @Override
    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmailOrPhone())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid password");
        }
        return jwtUtil.generateToken(user.getEmail(), user.getRole());
    }

    @Override
    public User getProfile(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
