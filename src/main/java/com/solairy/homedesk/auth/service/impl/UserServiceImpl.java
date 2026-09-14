package com.solairy.homedesk.auth.service.impl;

import com.solairy.homedesk.auth.dto.response.UserResponse;
import com.solairy.homedesk.auth.mapper.AuthMapper;
import com.solairy.homedesk.auth.mapper.UserMapper;
import com.solairy.homedesk.auth.model.User;
import com.solairy.homedesk.auth.repository.UserRepository;
import com.solairy.homedesk.auth.service.UserService;
import com.solairy.homedesk.common.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public PageResponse<UserResponse> findAll(Pageable pageable) {
        Page<User> usersPage = repository.findAll(pageable);
        return PageResponse.from(usersPage, mapper::toUserResponse);
    }
}
