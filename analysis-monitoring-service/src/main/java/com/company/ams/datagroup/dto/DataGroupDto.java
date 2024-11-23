package com.company.ams.datagroup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class DataGroupDto {

    @NotNull(message = "DataGroup ID is required")
    @JsonProperty("id")
    private UUID id;

    @NotBlank(message = "Code is required")
    @JsonProperty("code")
    private String code;

    @NotBlank(message = "Name is required")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "MenuOrder is required")
    @JsonProperty("menuOrder")
    private Integer menuOrder;

    @NotNull(message = "CreatedAt is required")
    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @NotNull(message = "UpdatedAt is required")
    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;
}