package com.example.relaxer.services.impl;

import com.example.relaxer.DTO.JwtResponse;
import com.example.relaxer.DTO.LoginRequest;
import com.example.relaxer.DTO.RefreshRequest;
import com.example.relaxer.DTO.RegisterRequest;
import com.example.relaxer.config.JwtUtils;
import com.example.relaxer.entity.Credentials;
import com.example.relaxer.entity.Role;
import com.example.relaxer.entity.User;
import com.example.relaxer.repositories.CredentialsRepository;
import com.example.relaxer.repositories.RoleRepository;
import com.example.relaxer.repositories.UserRepository;
import com.example.relaxer.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final CredentialsRepository credentialsRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public void register(RegisterRequest registerRequest) {
        User user = new User();
        user.setName(registerRequest.name());
        user.setAge(registerRequest.age());

        Role role = roleRepository.findByName("ROLE_USER").orElseThrow(()->new RuntimeException("Role not found"));

        Credentials credential = new Credentials();
        credential.setUsername(registerRequest.name());
        credential.setPassword(passwordEncoder.encode(registerRequest.password()));
        credential.setRole(role);
        credential.setUser(user);
//        userRepository.save(user);
        credentialsRepository.save(credential);
    }
    @Override
    public JwtResponse login(LoginRequest request){
        Credentials credentials = credentialsRepository.findByUserName(request.username())
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.password(),credentials.getPassword())){
            throw new BadCredentialsException("Invalid password ");
        }
        String assess = jwtUtils.generateAccessToken(request.username());
        String refresh = jwtUtils.generateRefreshToken(request.username());
        return new JwtResponse(assess, refresh);
    }

    @Override
    public JwtResponse refreshToken(RefreshRequest request){
        if (!jwtUtils.isTokenValid(request.refreshToken())){
            throw new RuntimeException("Invalid password ");
        }
        String assess = jwtUtils.generateAccessToken(request.username());
        String refresh = jwtUtils.generateRefreshToken(request.username());
        return new JwtResponse(assess, refresh);
    }


}