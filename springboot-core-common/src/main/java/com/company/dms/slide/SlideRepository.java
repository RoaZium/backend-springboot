package com.company.dms.slide;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SlideRepository extends JpaRepository<Slide, String> {
    List<Slide> findByPresentationId(String presentationId);
    List<Slide> findByUserId(String userId);
}