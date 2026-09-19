package com.solairy.homedesk.auth.service.impl;

import com.solairy.homedesk.auth.component.PepperPasswordEncoder;
import com.solairy.homedesk.auth.dto.request.UserRequest;
import com.solairy.homedesk.auth.dto.response.UserResponse;
import com.solairy.homedesk.auth.mapper.UserMapper;
import com.solairy.homedesk.auth.model.Role;
import com.solairy.homedesk.auth.model.User;
import com.solairy.homedesk.auth.repository.RoleRepository;
import com.solairy.homedesk.auth.repository.UserRepository;
import com.solairy.homedesk.auth.service.UserService;
import com.solairy.homedesk.audit.service.AuditLogService;
import com.solairy.homedesk.common.exception.GlobalException;
import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.enums.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final UserMapper mapper;
    private final PepperPasswordEncoder passwordEncoder;
    private final AuditLogService auditLogService;

    @Override
    @Transactional(readOnly = true)
    public PageResponse<UserResponse> findAll(Pageable pageable, boolean includeInactive) {
        Page<User> usersPage;

        if (includeInactive && callerIsAdmin()) {
            usersPage = repository.findAll(pageable);
            return PageResponse.from(usersPage, mapper::toUserResponse);
        }

        usersPage = repository.findAllByIsActiveTrue(pageable);

        return PageResponse.from(usersPage, mapper::toUserResponse);
    }

    @Override
    @Transactional
    public UserResponse create(UserRequest request) {

        Role role = roleRepository.findById(request.roleId())
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Rol no encontrado"));

        if (role.getName() == RoleName.ADMINISTRATION) {
            throw new GlobalException(HttpStatus.FORBIDDEN, "No es posible asignar el rol de administrador");
        }

        if (repository.existsByEmail(request.email())) {
            throw new GlobalException(HttpStatus.CONFLICT, "El correo electrónico ya está registrado");
        }

        Integer employeeNumber = repository.nextEmployeeNumber();

        // sigue la secuencia de first name + employee number
        String username = buildUsername(request.fullName(), employeeNumber);

        User user = new User();
        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setRole(role);
        user.setEmployeeNumber(employeeNumber);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setIsActive(true);
        user.setCreatedAt(OffsetDateTime.now());
        user.setUpdatedAt(OffsetDateTime.now());

        User saved = repository.save(user);
        auditLogService.log("CREATE", "USUARIOS", "User", saved.getId().toString(), "Usuario creado: " + saved.getUsername());
        return mapper.toUserResponse(saved);
    }

    @Override
    @Transactional
    public UserResponse update(UUID id, UserRequest request) {

        Role role = roleRepository.findById(request.roleId())
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Rol no encontrado"));

        if (role.getName() == RoleName.ADMINISTRATION || role.getId() == 1L) {
            throw new GlobalException(HttpStatus.FORBIDDEN, "No es posible asignar el rol de administrador");
        }

        User user = repository.findByIdWithRole(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        if (!user.getIsActive()) {
            throw new GlobalException(HttpStatus.CONFLICT, "No se puede actualizar un usuario desactivado");
        }

        if (repository.existsByEmailAndIdNot(request.email(), id)) {
            throw new GlobalException(HttpStatus.CONFLICT, "El correo electrónico ya está registrado");
        }

        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setRole(role);
        user.setUpdatedAt(OffsetDateTime.now());

        if (request.password() != null && !request.password().isBlank() && !request.password().equalsIgnoreCase("notpassword")) {
            user.setPassword(passwordEncoder.encode(request.password()));
        }

        User saved = repository.save(user);
        auditLogService.log("UPDATE", "USUARIOS", "User", saved.getId().toString(), "Usuario actualizado: " + saved.getUsername());
        return mapper.toUserResponse(saved);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        if (!user.getIsActive()) {
            throw new GlobalException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }

        user.setIsActive(false);
        user.setUpdatedAt(OffsetDateTime.now());

        repository.save(user);
        auditLogService.log("DELETE", "USUARIOS", "User", user.getId().toString(), "Usuario desactivado: " + user.getUsername());
    }

    // construccion del username
    private String buildUsername(String fullName, Integer employeeNumber) {
        String firstName = fullName.trim().split("\\s+")[0].toLowerCase();
        return firstName + employeeNumber;
    }

    // verificar si es admin // verificacion extra
    private boolean callerIsAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) return false;
        return auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMINISTRATION"));
    }
}
