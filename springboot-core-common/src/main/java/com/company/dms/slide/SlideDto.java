package com.company.dms.slide;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SlideDto {
    private String id;
    private String userId;
    private String presentationId;
    private String name;
    private int menuOrder;
    private int presentationOrder;
    private String propertiesJson;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}