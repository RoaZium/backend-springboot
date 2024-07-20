package com.company.dms.presentation;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface PresentationRepository extends JpaRepository<Presentation, UUID> {
    List<Presentation> findByUserId(UUID userId);
    List<Presentation> findByUserIdOrderByMenuOrder(UUID userId);
}