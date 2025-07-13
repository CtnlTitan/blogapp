package com.example.blogapp.security;

import com.example.blogapp.model.entity.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

     private final String SECRET_KEY;
    private final Key key;

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24L; // 24 hours

    public JwtUtil() {
        this.SECRET_KEY = System.getenv("JWT_SECRET");

        if (this.SECRET_KEY == null || this.SECRET_KEY.isEmpty()) {
            throw new IllegalStateException("JWT_SECRET environment variable not set");
        }

        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(UserDetails userDetails) {
        String username = userDetails.getUsername();

        // Cast if needed to access your User or CustomUserDetails
        Role role = ((CustomUserDetails) userDetails).getUser().getRole();

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role.name());  // e.g., "ROLE_ADMIN"

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract username from token
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // Validate token
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Helper to extract claims
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}