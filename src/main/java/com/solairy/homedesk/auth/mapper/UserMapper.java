package com.solairy.homedesk.auth.mapper;

import com.solairy.homedesk.auth.dto.response.UserResponse;
import com.solairy.homedesk.auth.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {

    UserResponse toUserResponse(User user);
}

