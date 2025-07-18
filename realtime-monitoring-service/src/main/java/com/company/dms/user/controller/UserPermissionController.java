package com.company.dms.user.controller;

import com.company.dms.user.dto.UserPermissionDto;
import com.company.dms.user.service.UserPermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user-permissions")
@Tag(name = "User Permissions", description = "사용자 권한 관리 APIs")
public class UserPermissionController {

    private final UserPermissionService userPermissionService;

    @Autowired
    public UserPermissionController(UserPermissionService userPermissionService) {
        this.userPermissionService = userPermissionService;
    }

    @GetMapping
    @Operation(summary = "전체 사용자 권한 조회", description = "옵션 필터를 사용하여 사용자 권한 목록을 조회합니다.")
    public ResponseEntity<List<UserPermissionDto>> getUserPermissions(
            @Parameter(description = "사용자 ID") @RequestParam(required = false) UUID userId,
            @Parameter(description = "생성자 사용자 ID") @RequestParam(required = false) UUID createdBy) {
        List<UserPermissionDto> userPermissions = userPermissionService.getUserPermissions(userId, createdBy);
        return ResponseEntity.ok(userPermissions);
    }

    @GetMapping("/{id}")
    @Operation(summary = "사용자 권한 조회", description = "사용자 권한 ID로 사용자 권한 정보를 검색합니다.")
    public ResponseEntity<UserPermissionDto> getUserPermissionById(@PathVariable UUID id) {
        UserPermissionDto userPermission = userPermissionService.getUserPermissionById(id);
        return userPermission != null ? ResponseEntity.ok(userPermission) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "사용자 권한 생성", description = "새 사용자 권한을 생성합니다.")
    public ResponseEntity<UserPermissionDto> createUserPermission(@RequestBody UserPermissionDto userPermissionDto) {
        UserPermissionDto createdUserPermission = userPermissionService.createUserPermission(userPermissionDto);
        return new ResponseEntity<>(createdUserPermission, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "사용자 권한 수정", description = "ID로 기존 사용자 권한의 정보를 수정합니다.")
    public ResponseEntity<UserPermissionDto> updateUserPermission(@PathVariable UUID id, @RequestBody UserPermissionDto userPermissionDto) {
        UserPermissionDto updatedUserPermission = userPermissionService.updateUserPermission(id, userPermissionDto);
        return updatedUserPermission != null ? ResponseEntity.ok(updatedUserPermission) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "사용자 권한 삭제", description = "ID로 사용자 권한을 삭제합니다.")
    public ResponseEntity<Void> deleteUserPermission(@PathVariable UUID id) {
        boolean deleted = userPermissionService.deleteUserPermission(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}