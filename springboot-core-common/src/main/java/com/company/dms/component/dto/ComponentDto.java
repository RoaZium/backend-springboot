package com.company.dms.component.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ComponentDto {
    private UUID id;
    private UUID slideId;
    private String category;
    private String name;
    private String propertiesJson;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}