package com.company.dms.slide.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "slide")
@Data
public class SlideEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "presentation_id")
    private UUID presentationId;

    @Column(name = "section_id")
    private UUID sectionId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "menu_order", nullable = false)
    private int menuOrder;

    @Column(name = "presentation_order")
    private Integer presentationOrder;

    @Column(name = "properties_json", columnDefinition = "NVARCHAR(MAX)")
    private String propertiesJson;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}