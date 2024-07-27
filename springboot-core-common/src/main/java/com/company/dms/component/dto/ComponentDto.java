package com.company.dms.component.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ComponentDto {

    @NotNull(message = "Component ID is required")
    private UUID id;

    @NotNull(message = "Slide ID is required")
    private UUID slideId;

    @NotNull(message = "Category is required")
    private String category;

    @NotNull(message = "Name is required")
    private String name;

    private String propertiesJson;

    @NotNull(message = "CreatedAt is required")
    private LocalDateTime createdAt;

    @NotNull(message = "UpdatedAt is required")
    private LocalDateTime updatedAt;
}