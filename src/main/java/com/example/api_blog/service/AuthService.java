package com.example.api_blog.service;

import com.example.api_blog.model.entity.Auth;
import com.example.api_blog.model.request.LoginRequest;
import com.example.api_blog.model.request.RegisterRequest;
import com.example.api_blog.model.response.LoginResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

     Auth register(RegisterRequest registerRequest);

     LoginResponse login(LoginRequest loginRequest);

     int logoutAll(String email);
}