package com.hotelbooker.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour
    private Key key;

    @PostConstruct
    public void init() {
        key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Создаём ключ при старте приложения
    }

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
