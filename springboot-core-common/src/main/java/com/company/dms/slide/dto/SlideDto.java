package com.company.dms.slide.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SlideDto {

    @NotNull(message = "Slide ID is required")
    @JsonProperty("Id")
    private UUID id;

    @NotNull(message = "User ID is required")
    @JsonProperty("UserId")
    private UUID userId;

    @JsonProperty("PresentationId")
    private UUID presentationId;

    @JsonProperty("SectionId")
    private UUID sectionId;

    @NotBlank(message = "Name is required")
    @JsonProperty("Name")
    private String name;

    @NotNull(message = "MenuOrder is required")
    @JsonProperty("MenuOrder")
    private int menuOrder;

    @JsonProperty("PresentationOrder")
    private Integer presentationOrder;

    @JsonProperty("PropertiesJson")
    private String propertiesJson;

    @NotNull(message = "CreatedAt is required")
    @JsonProperty("CreatedAt")
    private LocalDateTime createdAt;

    @NotNull(message = "UpdatedAt is required")
    @JsonProperty("UpdatedAt")
    private LocalDateTime updatedAt;
}