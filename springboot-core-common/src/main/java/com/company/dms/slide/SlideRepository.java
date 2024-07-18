package com.company.dms.slide;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface SlideRepository extends JpaRepository<Slide, UUID> {
    List<Slide> findByPresentationId(UUID presentationId);
    List<Slide> findByUserId(UUID userId);
}