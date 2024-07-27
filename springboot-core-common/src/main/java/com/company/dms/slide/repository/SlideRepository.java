package com.company.dms.slide.repository;

import com.company.dms.slide.entity.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface SlideRepository extends JpaRepository<Slide, UUID> {
    List<Slide> findByUserId(UUID userId);
    List<Slide> findByPresentationIdOrderByPresentationOrder(UUID presentationId);
    List<Slide> findBySectionIdOrderByMenuOrder(UUID sectionId);
    List<Slide> findByUserIdAndNameContainingIgnoreCase(UUID userId, String name);
    List<Slide> findByPresentationIdAndNameContainingIgnoreCaseOrderByPresentationOrder(UUID presentationId, String name);
    List<Slide> findBySectionIdAndNameContainingIgnoreCaseOrderByMenuOrder(UUID sectionId, String name);
    List<Slide> findByNameContainingIgnoreCase(String name);
}