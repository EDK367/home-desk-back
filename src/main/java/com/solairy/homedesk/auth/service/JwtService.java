package com.solairy.homedesk.auth.service;

import com.solairy.homedesk.auth.model.User;
import io.jsonwebtoken.Claims;

import java.util.List;

public interface JwtService {

    String generateToken(User user, List<String> roles);

    String generateRefreshToken(User user);

    Claims extractAllClaims(String token);

    String extractUserId(String token);

    String extractEmail(String token);

    String extractUsername(String token);

    List<String> extractRoles(String token);

    boolean isTokenValid(String token);

    boolean isRefreshTokenValid(String token);

    long getExpirationSeconds();

    long getRefreshExpirationSeconds();
}
