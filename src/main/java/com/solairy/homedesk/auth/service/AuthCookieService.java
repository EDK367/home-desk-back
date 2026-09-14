package com.solairy.homedesk.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseCookie;

import java.util.List;

public interface AuthCookieService {

    ResponseCookie createRefreshTokenCookie(String refreshToken);

    ResponseCookie createCleanRefreshTokenCookie();

    ResponseCookie createCleanCookie(String name);

    List<ResponseCookie> createAllCleanCookies();

    String extractRefreshToken(HttpServletRequest request);

    String getCookieName();
}
