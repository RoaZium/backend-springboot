package com.company.dms.dataitem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "data_item")
@Getter
@Setter
public class DataItem {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "group_id", nullable = false)
    private UUID groupId;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "menu_order", nullable = false)
    private Integer menuOrder;

    @Column(name = "datasource_properties", columnDefinition = "LONGTEXT")
    private String datasourceProperties;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public DataItem() {
    }

    public DataItem(UUID groupId, String code, String name, Integer menuOrder, String datasourceProperties) {
        this.groupId = groupId;
        this.code = code;
        this.name = name;
        this.menuOrder = menuOrder;
        this.datasourceProperties = datasourceProperties;
    }
}