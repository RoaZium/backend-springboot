package com.company.ams.presentation.repository;

import com.company.ams.presentation.entity.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PresentationRepository extends JpaRepository<Presentation, UUID> {
    List<Presentation> findByUserIdOrderByMenuOrder(UUID userId);
    List<Presentation> findByNameContainingIgnoreCase(String name);
}