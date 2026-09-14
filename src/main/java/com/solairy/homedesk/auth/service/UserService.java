package com.solairy.homedesk.auth.service;

import com.solairy.homedesk.auth.dto.response.UserResponse;
import com.solairy.homedesk.common.response.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    PageResponse<UserResponse> findAll(Pageable pageable);
}

