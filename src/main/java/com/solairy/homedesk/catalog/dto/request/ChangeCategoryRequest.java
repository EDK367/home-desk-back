package com.solairy.homedesk.catalog.dto.request;

import jakarta.validation.constraints.NotNull;

public record ChangeCategoryRequest(

        @NotNull(message = "El identificador de la categoría es obligatorio")
        Long categoryId
) {
}
