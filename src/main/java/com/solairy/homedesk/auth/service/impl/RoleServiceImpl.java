package com.solairy.homedesk.auth.service.impl;

import com.solairy.homedesk.auth.dto.response.RoleResponse;
import com.solairy.homedesk.auth.mapper.RoleMapper;
import com.solairy.homedesk.auth.model.Role;
import com.solairy.homedesk.auth.repository.RoleRepository;
import com.solairy.homedesk.auth.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;
    private final RoleMapper mapper;

    @Override
    public List<RoleResponse> findAll() {
        List<Role> roles = repository.findAllByIsActiveTrue();

        return roles
                .stream()
                .map(mapper::toRoleResponse)
                .toList();
    }
}
