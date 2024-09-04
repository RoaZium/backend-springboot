package com.company.dms.dataitem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class DataItemDto {

    @NotNull(message = "DataItem ID is required")
    @JsonProperty("Id")
    private UUID id;

    @NotNull(message = "GroupId is required")
    @JsonProperty("GroupId")
    private UUID groupId;

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