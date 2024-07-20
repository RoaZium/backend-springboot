package com.company.dms.slide;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface SlideRepository extends JpaRepository<Slide, UUID> {
    List<Slide> findByUserId(UUID userId);
    List<Slide> findByPresentationId(UUID presentationId);
    List<Slide> findBySectionId(UUID sectionId);
    List<Slide> findByPresentationIdOrderByPresentationOrder(UUID presentationId);
    List<Slide> findBySectionIdOrderByMenuOrder(UUID sectionId);
}