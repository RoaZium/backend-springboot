package com.company.dms.presentation.repository;

import com.company.dms.presentation.entity.PresentationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PresentationRepository extends JpaRepository<PresentationEntity, UUID> {
    List<PresentationEntity> findByUserIdOrderByMenuOrder(UUID userId);
    List<PresentationEntity> findByNameContainingIgnoreCase(String name);
}