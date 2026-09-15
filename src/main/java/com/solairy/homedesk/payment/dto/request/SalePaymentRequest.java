package com.solairy.homedesk.payment.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record SalePaymentRequest(
        Long saleId,

        @NotNull(message = "El ID del método de pago es obligatorio")
        Long paymentMethodId,

        @NotNull(message = "El monto del pago es obligatorio")
        @DecimalMin(value = "0.01", message = "El monto del pago debe ser mayor a 0")
        BigDecimal amount,

        OffsetDateTime paymentDate,

        @Size(max = 100, message = "El número de referencia no puede superar los 100 caracteres")
        String referenceNumber,

        @Size(max = 255, message = "Las notas no pueden superar los 255 caracteres")
        String notes
) {
}
