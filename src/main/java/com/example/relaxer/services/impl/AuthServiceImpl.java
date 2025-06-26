package com.example.relaxer.services.impl;

import com.example.relaxer.DTO.RegisterRequest;
import com.example.relaxer.entity.Credentials;
import com.example.relaxer.entity.Role;
import com.example.relaxer.entity.User;
import com.example.relaxer.repositories.CredentialsRepository;
import com.example.relaxer.repositories.RoleRepository;
import com.example.relaxer.repositories.UserRepository;
import com.example.relaxer.services.AuthService;
import com.example.relaxer.services.PassportService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CredentialsRepository credentialsRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterRequest registerRequest) {
        User user = new User();
        user.setName(registerRequest.name());
        user.setAge(registerRequest.age());

        Credentials credential = new Credentials();
        credential.setUsername(registerRequest.name());
        credential.setPassword(passwordEncoder.encode(registerRequest.password()));
        credential.setRole(new Role().builder().name("ROLE_USER").build());
        credential.setUser(user);
        userRepository.save(user);
        credentialsRepository.save(credential);
    }
}
