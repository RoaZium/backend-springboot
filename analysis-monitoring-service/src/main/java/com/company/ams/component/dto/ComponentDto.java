package com.company.ams.component.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ComponentDto {

    @NotNull(message = "Component ID is required")
    @JsonProperty("id")
    private UUID id;

    @NotNull(message = "Slide ID is required")
    @JsonProperty("slideId")
    private UUID slideId;

    @NotBlank(message = "Category is required")
    @JsonProperty("category")
    private String category;

    @NotBlank(message = "Name is required")
    @JsonProperty("name")
    private String name;

    @JsonProperty("arrangeJson")
    private String arrangeJson;

    @JsonProperty("dataJson")
    private String dataJson;

    @JsonProperty("styleJson")
    private String styleJson;

    @JsonProperty("textJson")
    private String textJson;

    @NotNull(message = "CreatedAt is required")
    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @NotNull(message = "UpdatedAt is required")
    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;
}