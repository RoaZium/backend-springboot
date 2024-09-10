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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slide_id", nullable = false)
    private Slide slide;

    @Column(name = "category", nullable = false, length = 20)
    private String category;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "properties_json", columnDefinition = "LONGTEXT")
    private String propertiesJson;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public void setSlide(Slide slide) {
        if (this.slide != slide) {
            Slide oldSlide = this.slide;
            this.slide = slide;
            if (oldSlide != null) {
                oldSlide.removeComponent(this);
            }
            if (slide != null) {
                slide.addComponent(this);
            }
        }
    }
}