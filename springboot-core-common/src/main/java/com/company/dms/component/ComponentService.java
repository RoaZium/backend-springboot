package com.company.dms.component;

import com.company.dms.component.ComponentDto;
import com.company.dms.component.Component;
import com.company.dms.component.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class ComponentService {

    private final ComponentRepository componentRepository;

    @Autowired
    public ComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    public Page<ComponentDto> getAllComponents(Pageable pageable) {
        Page<Component> components = componentRepository.findAll(pageable);
        return components.map(this::convertToDto);
    }

    public Optional<ComponentDto> getComponentById(String id) {
        return componentRepository.findById(id).map(this::convertToDto);
    }

    public ComponentDto createComponent(ComponentDto componentDto) {
        Component component = convertToEntity(componentDto);
        component.setId(UUID.randomUUID().toString());
        Component savedComponent = componentRepository.save(component);
        return convertToDto(savedComponent);
    }

    public ComponentDto updateComponent(String id, ComponentDto componentDto) {
        Component component = componentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Component not found"));
        updateComponentFromDto(component, componentDto);
        Component updatedComponent = componentRepository.save(component);
        return convertToDto(updatedComponent);
    }

    public void deleteComponent(String id) {
        componentRepository.deleteById(id);
    }

    private ComponentDto convertToDto(Component component) {
        ComponentDto dto = new ComponentDto();
        dto.setId(component.getId());
        dto.setCategory(component.getCategory());
        dto.setName(component.getName());
        dto.setPropertiesJson(component.getPropertiesJson());
        dto.setCreatedAt(component.getCreatedAt());
        dto.setUpdatedAt(component.getUpdatedAt());
        return dto;
    }

    private Component convertToEntity(ComponentDto dto) {
        Component component = new Component();
        component.setCategory(dto.getCategory());
        component.setName(dto.getName());
        component.setPropertiesJson(dto.getPropertiesJson());
        return component;
    }

    private void updateComponentFromDto(Component component, ComponentDto dto) {
        component.setCategory(dto.getCategory());
        component.setName(dto.getName());
        component.setPropertiesJson(dto.getPropertiesJson());
    }
}