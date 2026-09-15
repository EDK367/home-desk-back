package com.solairy.homedesk.sales.mapper;

import com.solairy.homedesk.sales.dto.response.SaleStatusResponse;
import com.solairy.homedesk.sales.model.SaleStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaleStatusMapper {

    SaleStatusResponse toResponse(SaleStatus status);
}
