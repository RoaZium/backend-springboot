package com.company.dms.component;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/components")
@Tag(name = "Components", description = "Components Management APIs")
public class ComponentController {

    private final ComponentService componentService;

    @Autowired
    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;
    }

    @GetMapping
    @Operation(summary = "전체 컴포넌트 조회", description = "옵션 필터를 사용하여 컴포넌트 목록을 조회합니다.")
    public ResponseEntity<List<ComponentDto>> getComponents(
            @Parameter(description = "Component categories: 'shape', 'chart', 'table', 'image'") @RequestParam(required = false) UUID slideId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String name) {
        List<ComponentDto> components = componentService.getComponents(slideId, category, name);
        return ResponseEntity.ok(components);
    }

    @GetMapping("/{id}")
    @Operation(summary = "컴포넌트 조회", description = "컴포넌트 ID로 컴포넌트 정보를 검색합니다.")
    public ResponseEntity<ComponentDto> getComponent(@PathVariable UUID id) {
        ComponentDto component = componentService.getComponent(id);
        return component != null ? ResponseEntity.ok(component) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "컴포넌트 생성", description = "새 컴포넌트를 생성합니다.")
    public ResponseEntity<ComponentDto> createComponent(@RequestBody ComponentDto componentDto) {
        ComponentDto createdComponent = componentService.createComponent(componentDto);
        return new ResponseEntity<>(createdComponent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "컴포넌트 수정", description = "ID로 기존 컴포넌트의 정보를 수정합니다.")
    public ResponseEntity<ComponentDto> updateComponent(@PathVariable UUID id, @RequestBody ComponentDto componentDto) {
        ComponentDto updatedComponent = componentService.updateComponent(id, componentDto);
        return updatedComponent != null ? ResponseEntity.ok(updatedComponent) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "컴포넌트 삭제", description = "ID로 컴포넌트를 삭제합니다.")
    public ResponseEntity<Void> deleteComponent(@PathVariable UUID id) {
        boolean deleted = componentService.deleteComponent(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}