package com.company.dms.component;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ComponentRepository extends JpaRepository<Component, UUID> {
    List<Component> findBySlideId(UUID slideId);
    List<Component> findByCategory(String category);
    List<Component> findByNameContainingIgnoreCase(String name);
    List<Component> findBySlideIdAndCategory(UUID slideId, String category);
    List<Component> findBySlideIdAndNameContainingIgnoreCase(UUID slideId, String name);
    List<Component> findByCategoryAndNameContainingIgnoreCase(String category, String name);
    List<Component> findBySlideIdAndCategoryAndNameContainingIgnoreCase(UUID slideId, String category, String name);
}
