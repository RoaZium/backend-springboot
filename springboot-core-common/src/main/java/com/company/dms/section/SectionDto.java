package com.company.dms.section;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SectionDto {
    private UUID id;
    private UUID userId;
    private String name;
    private int menuOrder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}