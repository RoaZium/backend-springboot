package com.company.dms.image.repository;

import com.company.dms.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {
    List<Image> findByNameContainingIgnoreCase(String name);
    List<Image> findByContentType(String contentType);
    List<Image> findByNameContainingIgnoreCaseAndContentType(String name, String contentType);
}