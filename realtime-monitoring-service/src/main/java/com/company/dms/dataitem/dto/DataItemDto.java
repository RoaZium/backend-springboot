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
    @JsonProperty("id")
    private UUID id;

    @NotNull(message = "GroupId is required")
    @JsonProperty("groupId")
    private UUID groupId;

    @NotBlank(message = "Code is required")
    @JsonProperty("code")
    private String code;

    @NotBlank(message = "Name is required")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "MenuOrder is required")
    @JsonProperty("menuOrder")
    private Integer menuOrder;

    @JsonProperty("datasourceProperties")
    private String datasourceProperties;

    @NotNull(message = "CreatedAt is required")
    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @NotNull(message = "UpdatedAt is required")
    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;
}