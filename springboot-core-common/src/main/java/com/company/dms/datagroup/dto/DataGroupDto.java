package com.company.dms.datagroup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class DataGroupDto {

    @NotNull(message = "DataGroup ID is required")
    @JsonProperty("Id")
    private UUID id;

    @NotBlank(message = "Code is required")
    @JsonProperty("Code")
    private String code;

    @NotBlank(message = "Name is required")
    @JsonProperty("Name")
    private String name;

    @NotNull(message = "MenuOrder is required")
    @JsonProperty("MenuOrder")
    private Integer menuOrder;

    @NotNull(message = "CreatedAt is required")
    @JsonProperty("CreatedAt")
    private LocalDateTime createdAt;

    @NotNull(message = "UpdatedAt is required")
    @JsonProperty("UpdatedAt")
    private LocalDateTime updatedAt;
}