package com.solairy.homedesk.suppliers.dto.request;

import com.solairy.homedesk.common.validation.NoDuplicatePhones;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record SupplierRequest(

        @NotBlank(message = "El nombre del proveedor es obligatorio")
        @Size(max = 150, message = "El nombre del proveedor no puede superar los 150 caracteres")
        String name,

        @NotBlank(message = "El número de identificación tributaria es obligatorio")
        @Size(max = 50, message = "El número tributario no puede superar los 50 caracteres")
        String taxNumber,

        @NotBlank(message = "El nombre del contacto es obligatorio")
        @Size(max = 150, message = "El nombre del contacto no puede superar los 150 caracteres")
        String contactName,

        @NotEmpty(message = "Se necesita al menos un número de teléfono para contacto")
        @NoDuplicatePhones
        @Valid
        List<PhoneNumberRequest> phones,

        @NotBlank(message = "El correo electrónico es obligatorio")
        @Email(message = "El correo electrónico no tiene un formato válido")
        @Size(max = 150, message = "El correo electrónico no puede superar los 150 caracteres")
        String email,

        @NotBlank(message = "La dirección es obligatoria")
        @Size(max = 250, message = "La dirección no puede superar los 250 caracteres")
        String address
) {
}