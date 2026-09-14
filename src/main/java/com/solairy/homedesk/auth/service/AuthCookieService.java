package com.solairy.homedesk.auth.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AuthCookieService {

    private final String cookieName;
    private final String path;
    private final String domain;
    private final boolean secure;
    private final String sameSite;
    private final boolean httpOnly;
    private final long refreshExpirationSeconds;

    // nombres para la cookie y su refresh
    private static final List<String> KNOWN_REFRESH_COOKIE_NAMES = List.of(
            "refreshToken",
            "hd_refresh_token",
            "refresh_token"
    );

    public AuthCookieService(
            @Value("${home-desk.jwt.cookie.name:refreshToken}") String cookieName,
            @Value("${home-desk.jwt.cookie.path:/}") String path,
            @Value("${home-desk.jwt.cookie.domain:}") String domain,
            @Value("${home-desk.jwt.cookie.secure:false}") boolean secure,
            @Value("${home-desk.jwt.cookie.same-site:Lax}") String sameSite,
            @Value("${home-desk.jwt.cookie.http-only:true}") boolean httpOnly,
            JwtService jwtService
    ) {
        this.cookieName = cookieName;
        this.path = path;
        this.domain = domain;
        this.secure = secure;
        this.sameSite = sameSite;
        this.httpOnly = httpOnly;
        this.refreshExpirationSeconds = jwtService.getRefreshExpirationSeconds();
    }

    public ResponseCookie createRefreshTokenCookie(String refreshToken) {
        ResponseCookie.ResponseCookieBuilder builder = ResponseCookie.from(cookieName, refreshToken)
                .httpOnly(httpOnly)
                .secure(secure)
                .path(path)
                .maxAge(refreshExpirationSeconds)
                .sameSite(sameSite);

        if (domain != null && !domain.isBlank()) {
            builder.domain(domain.trim());
        }

        return builder.build();
    }

    public ResponseCookie createCleanRefreshTokenCookie() {
        return createCleanCookie(cookieName);
    }

    public ResponseCookie createCleanCookie(String name) {
        ResponseCookie.ResponseCookieBuilder builder = ResponseCookie.from(name, "")
                .httpOnly(httpOnly)
                .secure(secure)
                .path(path)
                .maxAge(0)
                .sameSite(sameSite);

        if (domain != null && !domain.isBlank()) {
            builder.domain(domain.trim());
        }

        return builder.build();
    }

    public List<ResponseCookie> createAllCleanCookies() {
        List<ResponseCookie> cleanCookies = new ArrayList<>();
        cleanCookies.add(createCleanRefreshTokenCookie());

        for (String alias : KNOWN_REFRESH_COOKIE_NAMES) {
            if (!alias.equalsIgnoreCase(cookieName)) {
                cleanCookies.add(createCleanCookie(alias));
            }
        }
        return cleanCookies;
    }

    public String extractRefreshToken(HttpServletRequest request) {
        if (request == null || request.getCookies() == null) {
            return null;
        }

        Cookie[] cookies = request.getCookies();

        Optional<Cookie> primaryCookie = Arrays.stream(cookies)
                .filter(c -> c.getName().equalsIgnoreCase(cookieName))
                .findFirst();

        if (primaryCookie.isPresent() && primaryCookie.get().getValue() != null && !primaryCookie.get().getValue().isBlank()) {
            return primaryCookie.get().getValue().trim();
        }

        for (String alias : KNOWN_REFRESH_COOKIE_NAMES) {
            if (!alias.equalsIgnoreCase(cookieName)) {
                Optional<Cookie> aliasCookie = Arrays.stream(cookies)
                        .filter(c -> c.getName().equalsIgnoreCase(alias))
                        .findFirst();
                if (aliasCookie.isPresent() && aliasCookie.get().getValue() != null && !aliasCookie.get().getValue().isBlank()) {
                    return aliasCookie.get().getValue().trim();
                }
            }
        }

        return null;
    }

    public String getCookieName() {
        return cookieName;
    }
}
