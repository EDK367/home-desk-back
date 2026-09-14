package com.solairy.homedesk.auth.mapper;

import com.solairy.homedesk.auth.dto.response.RoleResponse;
import com.solairy.homedesk.auth.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleResponse toRoleResponse(Role role);

}
