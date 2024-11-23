package com.company.ams.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PresentationDto {

    @NotNull(message = "Presentation ID is required")
    @JsonProperty("id")
    private UUID id;

    @NotNull(message = "User ID is required")
    @JsonProperty("userId")
    private UUID userId;

    @NotBlank(message = "Name is required")
    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @NotNull(message = "MenuOrder ID is required")
    @JsonProperty("menuOrder")
    private int menuOrder;

    @JsonProperty("propertiesJson")
    private String propertiesJson;

    @NotNull(message = "CreatedAt is required")
    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @NotNull(message = "UpdatedAt is required")
    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;
}