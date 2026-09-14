package com.solairy.homedesk.auth.mapper;

import com.solairy.homedesk.auth.dto.response.UserSummaryResponse;
import com.solairy.homedesk.auth.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    @Mapping(target = "role", source = "role.name")
    UserSummaryResponse toUserSummaryResponse(User user);
}
