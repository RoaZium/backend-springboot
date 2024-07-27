package com.company.dms.section.repository;

import com.company.dms.section.entity.SectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface SectionRepository extends JpaRepository<SectionEntity, UUID> {
    List<SectionEntity> findByUserIdOrderByMenuOrder(UUID userId);
    List<SectionEntity> findByUserIdAndNameContainingIgnoreCaseOrderByMenuOrder(UUID userId, String name);
    List<SectionEntity> findByNameContainingIgnoreCaseOrderByMenuOrder(String name);
}