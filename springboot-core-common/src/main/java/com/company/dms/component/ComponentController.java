package com.company.dms.component;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/components")
@Tag(name = "Components", description = "Component Management APIs")
public class ComponentController {
    private final ComponentService componentService;

    @Autowired
    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;
    }

    @GetMapping
    public ResponseEntity<List<ComponentDto>> getAllComponents() {
        List<ComponentDto> components = componentService.getAllComponents();
        return ResponseEntity.ok(components);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComponentDto> getComponentById(@PathVariable UUID id) {
        return componentService.getComponentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ComponentDto> createComponent(@RequestBody ComponentDto componentDto) {
        ComponentDto createdComponent = componentService.createComponent(componentDto);
        return ResponseEntity.ok(createdComponent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComponentDto> updateComponent(@PathVariable UUID id, @RequestBody ComponentDto componentDto) {
        ComponentDto updatedComponent = componentService.updateComponent(id, componentDto);
        return ResponseEntity.ok(updatedComponent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComponent(@PathVariable UUID id) {
        componentService.deleteComponent(id);
        return ResponseEntity.noContent().build();
    }
}