package com.company.dms.component.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ComponentDto {

    @NotNull(message = "Component ID is required")
    @JsonProperty("Id")
    private UUID id;

    @NotNull(message = "Slide ID is required")
    @JsonProperty("SlideId")
    private UUID slideId;

    @NotBlank(message = "Category is required")
    @JsonProperty("Category")
    private String category;

    @NotBlank(message = "Name is required")
    @JsonProperty("Name")
    private String name;

    @JsonProperty("TextDataJson")
    private String textDataJson;

    @JsonProperty("textEffectJson")
    private String textEffectJson;

    @JsonProperty("TextStyleJson")
    private String textStyleJson;

    @JsonProperty("ShapeArrangementJson")
    private String shapeArrangementJson;

    @JsonProperty("ShapeEffectJson")
    private String shapeEffectJson;

    @JsonProperty("ShapeStyleJson")
    private String shapeStyleJson;

    @NotNull(message = "CreatedAt is required")
    @JsonProperty("CreatedAt")
    private LocalDateTime createdAt;

    @NotNull(message = "UpdatedAt is required")
    @JsonProperty("UpdatedAt")
    private LocalDateTime updatedAt;
}