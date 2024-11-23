package com.company.dms.section.repository;

import com.company.dms.section.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SectionRepository extends JpaRepository<Section, UUID> {
    List<Section> findByUserIdOrderByMenuOrder(UUID userId);
    List<Section> findByUserIdAndNameContainingIgnoreCaseOrderByMenuOrder(UUID userId, String name);
    List<Section> findByNameContainingIgnoreCaseOrderByMenuOrder(String name);
}