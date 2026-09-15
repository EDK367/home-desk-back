package com.solairy.homedesk.sales.dto.request;

import com.solairy.homedesk.payment.dto.request.SalePaymentRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;
import java.util.List;

public record SaleRequest(
        @NotNull(message = "El ID del cliente es obligatorio")
        Long customerId,

        @Size(max = 30, message = "El número de factura no puede superar 30 caracteres")
        String invoiceNumber,

        Long statusId,

        OffsetDateTime saleDate,

        @Size(max = 255, message = "La ruta del PDF no puede superar 255 caracteres")
        String invoicePdfPath,

        @NotEmpty(message = "La venta debe tener al menos un ítem")
        @Valid
        List<SaleItemRequest> items,

        @Valid
        List<SalePaymentRequest> payments
) {
}
