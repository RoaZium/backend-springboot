package com.company.dms.presentation;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PresentationDto {
    private UUID id;
    private UUID userId;
    private String name;
    private String description;
    private int menuOrder;
    private String propertiesJson;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}