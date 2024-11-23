package com.company.dms.component.service;

import com.company.dms.component.dto.ComponentDto;
import com.company.dms.component.entity.Component;
import com.company.dms.component.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        List<Component> components;

        if (slideId != null && category != null && name != null) {
            components = componentRepository.findBySlideIdAndCategoryAndNameContainingIgnoreCase(slideId, category, name);
        } else if (slideId != null && category != null) {
            components = componentRepository.findBySlideIdAndCategory(slideId, category);
        } else if (slideId != null && name != null) {
            components = componentRepository.findBySlideIdAndNameContainingIgnoreCase(slideId, name);
        } else if (category != null && name != null) {
            components = componentRepository.findByCategoryAndNameContainingIgnoreCase(category, name);
        } else if (slideId != null) {
            components = componentRepository.findBySlideId(slideId);
        } else if (category != null) {
            components = componentRepository.findByCategory(category);
        } else if (name != null) {
            components = componentRepository.findByNameContainingIgnoreCase(name);
        } else {
            components = componentRepository.findAll();
        }

        return components.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public ComponentDto getComponentById(UUID id) {
        return componentRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    @Transactional
    public ComponentDto createComponent(ComponentDto componentDto) {
        if (componentDto.getId() == null) {
            componentDto.setId(UUID.randomUUID());
        }

        Component component = convertToEntity(componentDto);
        Component savedComponent = componentRepository.save(component);
        return convertToDto(savedComponent);
    }

    @Transactional
    public ComponentDto updateComponent(UUID id, ComponentDto componentDto) {
        return componentRepository.findById(id)
                .map(component -> {
                    updateComponentFromDto(component, componentDto);
                    Component updatedComponent = componentRepository.save(component);
                    return convertToDto(updatedComponent);
                })
                .orElse(null);
    }

    @Transactional
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
        dto.setArrangeJson(component.getArrangeJson());
        dto.setDataJson(component.getDataJson());
        dto.setStyleJson(component.getStyleJson());
        dto.setTextJson(component.getTextJson());
        dto.setCreatedAt(component.getCreatedAt());
        dto.setUpdatedAt(component.getUpdatedAt());
        return dto;
    }

    private Component convertToEntity(ComponentDto dto) {
        Component component = new Component();
        component.setId(dto.getId());
        component.setSlideId(dto.getSlideId());
        component.setCategory(dto.getCategory());
        component.setName(dto.getName());
        component.setArrangeJson(dto.getArrangeJson());
        component.setDataJson(dto.getDataJson());
        component.setStyleJson(dto.getStyleJson());
        component.setTextJson(dto.getTextJson());
        return component;
    }

    private void updateComponentFromDto(Component component, ComponentDto dto) {
        component.setSlideId(dto.getSlideId());
        component.setCategory(dto.getCategory());
        component.setName(dto.getName());
        component.setArrangeJson(dto.getArrangeJson());
        component.setDataJson(dto.getDataJson());
        component.setStyleJson(dto.getStyleJson());
        component.setTextJson(dto.getTextJson());
    }
}