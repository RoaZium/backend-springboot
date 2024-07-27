package com.company.dms.slide.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SlideDto {
    private UUID id;
    private UUID userId;
    private UUID presentationId;
    private UUID sectionId;
    private String name;
    private int menuOrder;
    private Integer presentationOrder;
    private String propertiesJson;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}