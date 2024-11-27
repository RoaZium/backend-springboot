package com.company.dms.image.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ImageDto {
    @NotNull(message = "Image ID is required")
    private UUID id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Path is required")
    private String path;

    @NotNull(message = "Size is required")
    private Long size;

    private byte[] data;

    @NotBlank(message = "Content type is required")
    private String contentType;

    @NotNull(message = "CreatedAt is required")
    private LocalDateTime createdAt;

    @NotNull(message = "UpdatedAt is required")
    private LocalDateTime updatedAt;
}