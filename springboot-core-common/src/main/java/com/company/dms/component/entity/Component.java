package com.company.dms.component.entity;

import com.company.dms.slide.entity.Slide;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "component")
@Getter
@Setter
public class Component {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "slide_id", nullable = false)
    private UUID slideId;

    @Column(name = "category", nullable = false, length = 20)
    private String category;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "arrange_json", columnDefinition = "LONGTEXT")
    private String arrangeJson;

    @Column(name = "data_json", columnDefinition = "LONGTEXT")
    private String dataJson;

    @Column(name = "style_json", columnDefinition = "LONGTEXT")
    private String styleJson;

    @Column(name = "text_json", columnDefinition = "LONGTEXT")
    private String textJson;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}