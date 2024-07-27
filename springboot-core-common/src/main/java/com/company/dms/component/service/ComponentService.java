package com.company.dms.component.service;

import com.company.dms.component.dto.ComponentDto;
import com.company.dms.component.entity.Component;
import com.company.dms.component.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ComponentService {

    private final ComponentRepository componentRepository;

    @Autowired
    public ComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    public List<ComponentDto> getComponents(UUID slideId, String category, String name) {
        List<Component> componentEntities;

        if (slideId != null && category != null && name != null) {
            componentEntities = componentRepository.findBySlideIdAndCategoryAndNameContainingIgnoreCase(slideId, category, name);
        } else if (slideId != null && category != null) {
            componentEntities = componentRepository.findBySlideIdAndCategory(slideId, category);
        } else if (slideId != null && name != null) {
            componentEntities = componentRepository.findBySlideIdAndNameContainingIgnoreCase(slideId, name);
        } else if (category != null && name != null) {
            componentEntities = componentRepository.findByCategoryAndNameContainingIgnoreCase(category, name);
        } else if (slideId != null) {
            componentEntities = componentRepository.findBySlideId(slideId);
        } else if (category != null) {
            componentEntities = componentRepository.findByCategory(category);
        } else if (name != null) {
            componentEntities = componentRepository.findByNameContainingIgnoreCase(name);
        } else {
            componentEntities = componentRepository.findAll();
        }

        return componentEntities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public ComponentDto getComponent(UUID id) {
        return componentRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    public ComponentDto createComponent(ComponentDto componentDto) {
        Component component = convertToEntity(componentDto);
        Component savedComponent = componentRepository.save(component);
        return convertToDto(savedComponent);
    }

    public ComponentDto updateComponent(UUID id, ComponentDto componentDto) {
        return componentRepository.findById(id)
                .map(component -> {
                    updateComponentFromDto(component, componentDto);
                    Component updatedComponent = componentRepository.save(component);
                    return convertToDto(updatedComponent);
                })
                .orElse(null);
    }

    public boolean deleteComponent(UUID id) {
        if (componentRepository.existsById(id)) {
            componentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private ComponentDto convertToDto(Component component) {
        ComponentDto dto = new ComponentDto();
        dto.setId(component.getId());
        dto.setSlideId(component.getSlideId());
        dto.setCategory(component.getCategory());
        dto.setName(component.getName());
        dto.setPropertiesJson(component.getPropertiesJson());
        return dto;
    }

    private Component convertToEntity(ComponentDto dto) {
        Component component = new Component();
        component.setSlideId(dto.getSlideId());
        component.setCategory(dto.getCategory());
        component.setName(dto.getName());
        component.setPropertiesJson(dto.getPropertiesJson());
        return component;
    }

    private void updateComponentFromDto(Component component, ComponentDto dto) {
        component.setSlideId(dto.getSlideId());
        component.setCategory(dto.getCategory());
        component.setName(dto.getName());
        component.setPropertiesJson(dto.getPropertiesJson());
    }
}