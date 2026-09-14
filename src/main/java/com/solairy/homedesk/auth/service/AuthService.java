package com.solairy.homedesk.auth.service;

import com.solairy.homedesk.auth.dto.LoginRequest;
import com.solairy.homedesk.auth.dto.LoginResponse;
import com.solairy.homedesk.auth.dto.UserSummaryResponse;

import java.util.UUID;

public interface AuthService {

    LoginResponse login(LoginRequest request);

    LoginResponse refreshToken(String refreshToken);

    void logout(String refreshToken, UUID authenticatedUserId);

    UserSummaryResponse getCurrentUser(UUID userId);
}
