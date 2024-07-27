package com.company.dms.slide.repository;

import com.company.dms.slide.entity.SlideEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface SlideRepository extends JpaRepository<SlideEntity, UUID> {
    List<SlideEntity> findByUserId(UUID userId);
    List<SlideEntity> findByPresentationIdOrderByPresentationOrder(UUID presentationId);
    List<SlideEntity> findBySectionIdOrderByMenuOrder(UUID sectionId);
    List<SlideEntity> findByUserIdAndNameContainingIgnoreCase(UUID userId, String name);
    List<SlideEntity> findByPresentationIdAndNameContainingIgnoreCaseOrderByPresentationOrder(UUID presentationId, String name);
    List<SlideEntity> findBySectionIdAndNameContainingIgnoreCaseOrderByMenuOrder(UUID sectionId, String name);
    List<SlideEntity> findByNameContainingIgnoreCase(String name);
}