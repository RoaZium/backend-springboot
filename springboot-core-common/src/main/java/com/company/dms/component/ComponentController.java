package com.company.dms.component;

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
    public ResponseEntity<List<ComponentDto>> getComponents(
            @Parameter(description = "Component categories: 'shape', 'chart', 'table', 'image'") @RequestParam(required = false) UUID slideId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String name) {
        List<ComponentDto> components = componentService.getComponents(slideId, category, name);
        return ResponseEntity.ok(components);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComponentDto> getComponent(@PathVariable UUID id) {
        ComponentDto component = componentService.getComponent(id);
        return component != null ? ResponseEntity.ok(component) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ComponentDto> createComponent(@RequestBody ComponentDto componentDto) {
        ComponentDto createdComponent = componentService.createComponent(componentDto);
        return new ResponseEntity<>(createdComponent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComponentDto> updateComponent(@PathVariable UUID id, @RequestBody ComponentDto componentDto) {
        ComponentDto updatedComponent = componentService.updateComponent(id, componentDto);
        return updatedComponent != null ? ResponseEntity.ok(updatedComponent) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComponent(@PathVariable UUID id) {
        boolean deleted = componentService.deleteComponent(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}