package com.company.dms.controller;

import com.company.dms.dto.ComponentDto;
import com.company.dms.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dms/components")
public class ComponentController {

    private final ComponentService componentService;

    @Autowired
    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;
    }

    @GetMapping
    public ResponseEntity<Page<ComponentDto>> getAllComponents(Pageable pageable) {
        Page<ComponentDto> components = componentService.getAllComponents(pageable);
        return ResponseEntity.ok(components);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComponentDto> getComponentById(@PathVariable String id) {
        return componentService.getComponentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ComponentDto> createComponent(@RequestBody ComponentDto componentDto) {
        ComponentDto createdComponent = componentService.createComponent(componentDto);
        return ResponseEntity.ok(createdComponent);
    }
}