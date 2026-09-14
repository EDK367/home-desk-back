package com.solairy.homedesk.auth.controller;

import com.solairy.homedesk.auth.dto.request.UserRequest;
import com.solairy.homedesk.auth.dto.response.UserResponse;
import com.solairy.homedesk.auth.service.UserService;
import com.solairy.homedesk.common.response.ApiResponse;
import com.solairy.homedesk.common.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Endpoints para la gestión de usuarios")
public class UserController {

    private final UserService service;

    @GetMapping
    @PreAuthorize("hasRole('ADMINISTRATION')")
    public ResponseEntity<ApiResponse<PageResponse<UserResponse>>> findAll(
            @ParameterObject
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(defaultValue = "false") boolean includeInactive
    ) {
        PageResponse<UserResponse> response = service.findAll(pageable, includeInactive);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Usuarios obtenidos exitosamente",
                response
        ));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRATION')")
    public ResponseEntity<ApiResponse<UserResponse>> create(
            @Valid @RequestBody UserRequest request
    ) {
        UserResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(
                HttpStatus.CREATED.value(),
                "Usuario creado exitosamente",
                response
        ));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATION')")
    public ResponseEntity<ApiResponse<UserResponse>> update(
            @PathVariable UUID id,
            @Valid @RequestBody UserRequest request
    ) {
        UserResponse response = service.update(id, request);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Usuario actualizado exitosamente",
                response
        ));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATION')")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable UUID id
    ) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Usuario desactivado exitosamente",
                null
        ));
    }
}
