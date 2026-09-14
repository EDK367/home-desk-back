package com.solairy.homedesk.auth.repository;

import com.solairy.homedesk.auth.model.Role;
import com.solairy.homedesk.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
