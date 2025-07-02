package com.example.relaxer.config;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Component;

import java.lang.classfile.attribute.SignatureAttribute;
import java.util.Date;

@Component
public class JwtUtils {
    @Value("${jwt.secret")
    String jwtSecret;
    private final long accessTokenExpiresIn = 60000 * 15;
    private final long refreshTokenExpiresIn = 60000 * 60 * 24;

    String generateAccessToken(String username) {
        return generateToken(username, accessTokenExpiresIn);
    }

    String generateRefreshToken(String username) {

        return generateToken(username, refreshTokenExpiresIn);
    }

    private String generateToken(String username, long tokenExpiresIn) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + tokenExpiresIn))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    private String extractUsername(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e){
            return false;
        }
    }


}
