package com.example.dms.component.service;

import com.example.dms.component.dto.ComponentDto;
import com.example.dms.component.entity.Component;
import com.example.dms.component.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ComponentService {

    @Autowired
    private final ComponentRepository componentRepository;

    public ComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    public void saveComponent(Component component) {
        componentRepository.save(component);
    }

    public Component findComponentById(int id) {

        return componentRepository.findById(id).orElse(null);
    }

    public List<ComponentDto> findAllComponent() {

        return componentRepository.findAll();
    }

    public void deleteComponentById(int id) {

        componentRepository.deleteById(id);
    }
}
