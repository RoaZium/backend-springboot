package com.company.dms.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserPermissionDto {

    @NotNull(message = "사용자 권한 ID는 필수입니다")
    @JsonProperty("id")
    private UUID id;

    @NotNull(message = "사용자 ID는 필수입니다")
    @JsonProperty("userId")
    private UUID userId;

    @NotBlank(message = "권한 JSON은 필수입니다")
    @JsonProperty("permissionsJson")
    private String permissionsJson;

    @NotNull(message = "생성일시는 필수입니다")
    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @NotNull(message = "생성자는 필수입니다")
    @JsonProperty("createdBy")
    private UUID createdBy;

    @NotNull(message = "수정일시는 필수입니다")
    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

    @JsonProperty("updatedBy")
    private UUID updatedBy;
}