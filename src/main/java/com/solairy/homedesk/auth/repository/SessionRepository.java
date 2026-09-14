package com.solairy.homedesk.auth.repository;

import com.solairy.homedesk.auth.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {

    @Query("SELECT s FROM Session s JOIN FETCH s.user u JOIN FETCH u.role WHERE s.tokenHash = :tokenHash")
    Optional<Session> findByTokenHash(@Param("tokenHash") String tokenHash);

    List<Session> findByUserIdAndRevokedAtIsNull(UUID userId);
}
