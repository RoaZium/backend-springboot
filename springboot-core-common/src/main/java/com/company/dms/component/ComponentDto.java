package com.company.dms.component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComponentDto {
    private String id;
    private String category;
    private String name;
    private String propertiesJson;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}