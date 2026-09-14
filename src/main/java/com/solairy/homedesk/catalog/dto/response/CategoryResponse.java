package com.solairy.homedesk.catalog.dto.response;

import java.time.OffsetDateTime;

public record CategoryResponse(
        Long id,
        String name,
        String description,
        Boolean isActive,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
