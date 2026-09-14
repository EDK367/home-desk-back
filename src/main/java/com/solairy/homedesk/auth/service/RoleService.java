package com.solairy.homedesk.auth.service;

import com.solairy.homedesk.auth.dto.response.RoleResponse;
import com.solairy.homedesk.auth.model.Role;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleService {

    List<RoleResponse> findAll();
}
