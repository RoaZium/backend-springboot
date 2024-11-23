package com.company.ams.slide.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SlideDto {

    @NotNull(message = "Slide ID is required")
    @JsonProperty("id")
    private UUID id;

    @NotNull(message = "User ID is required")
    @JsonProperty("userId")
    private UUID userId;

    @JsonProperty("presentationId")
    private UUID presentationId;

    @JsonProperty("sectionId")
    private UUID sectionId;

    @NotBlank(message = "Name is required")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "MenuOrder is required")
    @JsonProperty("menuOrder")
    private int menuOrder;

    @JsonProperty("presentationOrder")
    private Integer presentationOrder;

    @JsonProperty("propertiesJson")
    private String propertiesJson;

    @NotNull(message = "CreatedAt is required")
    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @NotNull(message = "UpdatedAt is required")
    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;
}