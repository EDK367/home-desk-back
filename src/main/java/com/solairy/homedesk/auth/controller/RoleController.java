package com.solairy.homedesk.auth.controller;

import com.solairy.homedesk.auth.dto.response.RoleResponse;
import com.solairy.homedesk.auth.service.RoleService;
import com.solairy.homedesk.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
@Tag(name = "Roles", description = "Endpoints para la gestión de roles")
public class RoleController {

    private final RoleService service;

    @GetMapping
    @PreAuthorize("hasRole('ADMINISTRATION')")
    public ResponseEntity<ApiResponse<List<RoleResponse>>> findAll() {
        List<RoleResponse> response = service.findAll();
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK.value(),
                "Roles obtenidos exitosamente",
                response
        ));
    }
}
