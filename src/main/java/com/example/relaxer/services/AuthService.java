package com.example.relaxer.services;

import com.example.relaxer.DTO.JwtResponse;
import com.example.relaxer.DTO.LoginRequest;
import com.example.relaxer.DTO.RefreshRequest;
import com.example.relaxer.DTO.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest request);

    JwtResponse login(LoginRequest request);

    JwtResponse refreshToken(RegisterRequest request);

    JwtResponse refreshToken(RefreshRequest request);
}
