package com.company.dms.slide;

import com.company.dms.user.User;
import com.company.dms.presentation.Presentation;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "slide")
@Data
public class Slide {
    @Id
    @Column(length = 36)
    private String id;

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

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}