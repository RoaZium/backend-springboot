package com.company.dms.presentation;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PresentationDto {
    private String id;
    private String userId;
    private String name;
    private String description;
    private int menuOrder;
    private String propertiesJson;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}