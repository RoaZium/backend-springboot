package com.company.dms.presentation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PresentationDto {

    @NotNull(message = "Presentation ID is required")
    private UUID id;

    @NotNull(message = "User ID is required")
    private UUID userId;

    @NotNull(message = "Name is required")
    private String name;

    private String description;

    @NotNull(message = "MenuOrder ID is required")
    private int menuOrder;

    private String propertiesJson;

    @NotNull(message = "CreatedAt is required")
    private LocalDateTime createdAt;

    @NotNull(message = "UpdatedAt is required")
    private LocalDateTime updatedAt;
}