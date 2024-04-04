package com.example.dms.component.controller;

import com.example.dms.component.dto.ComponentDto;
import com.example.dms.component.service.ComponentService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ComponentController {

    private final ComponentService componentService;

    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;
    }

    public List<ComponentDto> findAll() {
        return componentService.findAllComponent();
    }
}
