package com.solairy.homedesk.auth.repository;

import com.solairy.homedesk.auth.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT u FROM User u JOIN FETCH u.role WHERE LOWER(u.username) = LOWER(:identifier) OR LOWER(u.email) = LOWER(:identifier)")
    Optional<User> findByUsernameOrEmailIgnoreCase(@Param("identifier") String identifier);

    @Query("SELECT u FROM User u JOIN FETCH u.role WHERE u.id = :id")
    Optional<User> findByIdWithRole(@Param("id") UUID id);

    // atributo de manera eficiente sin N + 1
    @EntityGraph(attributePaths = {"role"})
    Page<User> findAll(Pageable pageable);

    // usaurios activos
    @EntityGraph(attributePaths = {"role"})
    Page<User> findAllByIsActiveTrue(Pageable pageable);

    // verificar si el email ya exixte
    boolean existsByEmail(String email);

    // verificar el email ingresado ignorando el actual
    boolean existsByEmailAndIdNot(String email, UUID id);

    // si hay colision con el username (complicado)
    boolean existsByUsername(String username);

    // numero de empleado
    @Query(value = "SELECT NEXTVAL('seq_employee_number')", nativeQuery = true)
    Integer nextEmployeeNumber();
}
