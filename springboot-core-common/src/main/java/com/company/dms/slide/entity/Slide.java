package com.company.dms.slide.entity;

import com.company.dms.component.entity.Component;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "slide")
@Getter
@Setter
public class Slide {

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

    @Column(name = "properties_json", columnDefinition = "LONGTEXT")
    private String propertiesJson;

    @OneToMany(mappedBy = "slide", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Component> components = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public void addComponent(Component component) {
        if (component != null && !this.components.contains(component)) {
            this.components.add(component);
            component.setSlide(this);
        }
    }

    public void removeComponent(Component component) {
        if (component != null && this.components.contains(component)) {
            this.components.remove(component);
            component.setSlide(null);
        }
    }
}