package com.example.relaxer.config;

import com.example.relaxer.entity.Credentials;
//import com.example.relaxer.entity.User;
import com.example.relaxer.repositories.CredentialsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(CredentialsRepository credentialsRepository) {
        return username -> {
            Credentials credentials = credentialsRepository.findByUserName(username)
                    .orElseThrow(() -> new UsernameNotFoundException(username));
            return User.builder()
                    .username(username)
                    .password(credentials.getPassword())
                    .roles(credentials.getRole().getName().replace("ROLE_", ""))
                    .build();
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(auth -> auth
                        .requestMatchers("/auth/register", "/v3/api/docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers("/user/**").hasRole("USER")
                ).httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
        return http.build();
    }
}
