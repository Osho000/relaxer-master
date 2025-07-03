package com.example.relaxer.controllers;



import com.example.relaxer.DTO.JwtResponse;
import com.example.relaxer.DTO.LoginRequest;
import com.example.relaxer.DTO.RefreshRequest;
import com.example.relaxer.DTO.RegisterRequest;
import com.example.relaxer.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth API", description = "Регистрация, логин и обновление токенов")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Регистрация")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("Пользователь зарегистрирован");
    }

    @Operation(summary = "Аутентификация и получение токенов")
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @Operation(summary = "Обновление access токена")
    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refresh(@RequestBody RefreshRequest request) {
        return ResponseEntity.ok(authService.refreshToken(request));
    }
}

