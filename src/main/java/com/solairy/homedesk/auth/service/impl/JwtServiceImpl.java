package com.solairy.homedesk.auth.service.impl;

import com.solairy.homedesk.auth.model.User;
import com.solairy.homedesk.auth.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class JwtServiceImpl implements JwtService {

    private final SecretKey key;
    private final long expirationMs;
    private final long refreshExpirationMs;

    public JwtServiceImpl(
            @Value("${home-desk.jwt.secret}") String secret,
            @Value("${home-desk.jwt.expiracion-ms:3600000}") long expirationMs,
            @Value("${home-desk.jwt.refresh-expiracion-ms:2592000000}") long refreshExpirationMs
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMs = expirationMs;
        this.refreshExpirationMs = refreshExpirationMs;
    }

    @Override
    public String generateToken(User user, List<String> roles) {
        Date now = new Date();
        Date expire = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("username", user.getUsername())
                .claim("email", user.getEmail())
                .claim("employeeNumber", user.getEmployeeNumber())
                .claim("roles", roles)
                .claim("type", "access")
                .issuedAt(now)
                .expiration(expire)
                .signWith(key)
                .compact();
    }

    @Override
    public String generateRefreshToken(User user) {
        Date now = new Date();
        Date expire = new Date(now.getTime() + refreshExpirationMs);

        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("type", "refresh")
                .claim("jti", UUID.randomUUID().toString())
                .issuedAt(now)
                .expiration(expire)
                .signWith(key)
                .compact();
    }

    @Override
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public String extractUserId(String token) {
        return extractAllClaims(token).getSubject();
    }

    @Override
    public String extractEmail(String token) {
        return extractAllClaims(token).get("email", String.class);
    }

    @Override
    public String extractUsername(String token) {
        return extractAllClaims(token).get("username", String.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> extractRoles(String token) {
        Object rolesObj = extractAllClaims(token).get("roles");
        if (rolesObj instanceof List<?> list) {
            return list.stream().map(Object::toString).toList();
        }
        return List.of();
    }

    @Override
    public boolean isTokenValid(String token) {
        try {
            Claims claims = extractAllClaims(token);
            return !claims.getExpiration().before(new Date()) && "access".equals(claims.get("type"));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean isRefreshTokenValid(String token) {
        try {
            Claims claims = extractAllClaims(token);
            return !claims.getExpiration().before(new Date()) && "refresh".equals(claims.get("type"));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public long getExpirationSeconds() {
        return expirationMs / 1000;
    }

    @Override
    public long getRefreshExpirationSeconds() {
        return refreshExpirationMs / 1000;
    }
}
