package com.example.api_blog.service.impl;

import com.example.api_blog.jwt.JwtService;
import com.example.api_blog.model.entity.Auth;
import com.example.api_blog.model.request.LoginRequest;
import com.example.api_blog.model.request.RegisterRequest;
import com.example.api_blog.model.response.LoginResponse;
import com.example.api_blog.repository.AuthRepo;
import com.example.api_blog.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepo authRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Auth auth = authRepo.findByEmail(email);
        if (auth == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return auth;
    }

    @Override
    public Auth register(RegisterRequest registerRequest) {
        Auth auth = new Auth();
        auth.setUserName(registerRequest.getUserName());
        auth.setEmail(registerRequest.getEmail());
        auth.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        // FIXED: createdAt set by DB default, not from request
        auth.setTokenVersion(0);
        return authRepo.register(auth);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Auth auth = authRepo.findByEmail(loginRequest.getEmail());
        if (auth == null) {
            throw new UsernameNotFoundException("User not found with email: " + loginRequest.getEmail());
        }
        // FIXED: use BadCredentialsException for wrong password
        if (!passwordEncoder.matches(loginRequest.getPassword(), auth.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        String token = jwtService.generateToken(auth);
        return LoginResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public int logoutAll(String email) {
        Auth auth = authRepo.findByEmail(email);
        if (auth == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return authRepo.incrementTokenVersion(auth.getUserId());
    }
}