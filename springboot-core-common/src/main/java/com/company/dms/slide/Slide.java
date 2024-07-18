package com.company.dms.slide;

import com.company.dms.user.User;
import com.company.dms.presentation.Presentation;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "slide")
@Data
public class Slide {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "presentation_id")
    private Presentation presentation;

    @Column(nullable = false)
    private String name;

    @Column(name = "menu_order", nullable = false)
    private int menuOrder;

    @Column(name = "presentation_order", nullable = false)
    private int presentationOrder;

    @Column(name = "properties_json", columnDefinition = "NVARCHAR(MAX)")
    private String propertiesJson;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}