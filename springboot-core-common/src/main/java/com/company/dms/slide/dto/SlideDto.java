package com.company.dms.slide.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SlideDto {

    @NotNull(message = "Slide ID is required")
    private UUID id;

    @NotNull(message = "User ID is required")
    private UUID userId;

    private UUID presentationId;

    private UUID sectionId;

    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "MenuOrder is required")
    private int menuOrder;

    private Integer presentationOrder;

    private String propertiesJson;

    @NotNull(message = "CreatedAt is required")
    private LocalDateTime createdAt;

    @NotNull(message = "UpdatedAt is required")
    private LocalDateTime updatedAt;
}