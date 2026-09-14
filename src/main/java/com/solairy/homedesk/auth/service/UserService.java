package com.solairy.homedesk.auth.service;

import com.solairy.homedesk.auth.dto.request.UserRequest;
import com.solairy.homedesk.auth.dto.response.UserResponse;
import com.solairy.homedesk.common.response.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {

    PageResponse<UserResponse> findAll(Pageable pageable, boolean includeInactive);

    UserResponse create(UserRequest request);

    UserResponse update(UUID id, UserRequest request);

    void delete(UUID id);
}
