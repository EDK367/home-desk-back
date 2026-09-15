package com.solairy.homedesk.purchases.mapper;

import com.solairy.homedesk.purchases.dto.response.PurchaseStatusResponse;
import com.solairy.homedesk.purchases.model.PurchaseStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseStatusMapper {

    PurchaseStatusResponse toResponse(PurchaseStatus status);
}
