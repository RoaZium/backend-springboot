package com.company.dms.controller;

import com.company.dms.dto.ComponentDto;
import com.company.dms.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dms/components")
public class ComponentController {

    private final ComponentService componentService;

    @Autowired
    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ComponentDto>> getComponentsByCategory(@PathVariable String category) {
        List<ComponentDto> components = componentService.getComponentsByCategory(category);
        return ResponseEntity.ok(components);
    }

    @PostMapping
    public ResponseEntity<ComponentDto> createComponent(@RequestBody ComponentDto componentDto) {
        ComponentDto createdComponent = componentService.createComponent(componentDto);
        return ResponseEntity.ok(createdComponent);
    }
}