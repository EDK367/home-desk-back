package com.solairy.homedesk.catalog.mapper;

import com.solairy.homedesk.catalog.dto.response.CategoryResponse;
import com.solairy.homedesk.catalog.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponse toCategoryResponse(Category category);
}
