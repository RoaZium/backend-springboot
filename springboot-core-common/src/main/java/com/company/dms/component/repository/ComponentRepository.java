package com.company.dms.component.repository;

import com.company.dms.component.entity.ComponentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ComponentRepository extends JpaRepository<ComponentEntity, UUID> {
    List<ComponentEntity> findBySlideId(UUID slideId);
    List<ComponentEntity> findByCategory(String category);
    List<ComponentEntity> findByNameContainingIgnoreCase(String name);
    List<ComponentEntity> findBySlideIdAndCategory(UUID slideId, String category);
    List<ComponentEntity> findBySlideIdAndNameContainingIgnoreCase(UUID slideId, String name);
    List<ComponentEntity> findByCategoryAndNameContainingIgnoreCase(String category, String name);
    List<ComponentEntity> findBySlideIdAndCategoryAndNameContainingIgnoreCase(UUID slideId, String category, String name);
}
