package com.company.dms.presentation;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PresentationRepository extends JpaRepository<Presentation, String> {
    List<Presentation> findByUserId(String userId);
}