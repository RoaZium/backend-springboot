package com.company.dms.section.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SectionDto {

    @NotNull(message = "Section ID is required")
    private UUID id;

    @NotNull(message = "User ID is required")
    private UUID userId;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "MenuOrder is required")
    private int menuOrder;

    @NotNull(message = "CreatedAt is required")
    private LocalDateTime createdAt;

    @NotNull(message = "UpdatedAt is required")
    private LocalDateTime updatedAt;
}