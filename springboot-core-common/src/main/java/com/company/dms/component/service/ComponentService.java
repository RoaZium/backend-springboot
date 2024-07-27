package com.company.dms.component.service;

import com.company.dms.component.dto.ComponentDto;
import com.company.dms.component.entity.ComponentEntity;
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
        List<ComponentEntity> componentEntities;

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
        ComponentEntity componentEntity = convertToEntity(componentDto);
        ComponentEntity savedComponentEntity = componentRepository.save(componentEntity);
        return convertToDto(savedComponentEntity);
    }

    public ComponentDto updateComponent(UUID id, ComponentDto componentDto) {
        return componentRepository.findById(id)
                .map(componentEntity -> {
                    updateComponentFromDto(componentEntity, componentDto);
                    ComponentEntity updatedComponentEntity = componentRepository.save(componentEntity);
                    return convertToDto(updatedComponentEntity);
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

    private ComponentDto convertToDto(ComponentEntity componentEntity) {
        ComponentDto dto = new ComponentDto();
        dto.setId(componentEntity.getId());
        dto.setSlideId(componentEntity.getSlideId());
        dto.setCategory(componentEntity.getCategory());
        dto.setName(componentEntity.getName());
        dto.setPropertiesJson(componentEntity.getPropertiesJson());
        return dto;
    }

    private ComponentEntity convertToEntity(ComponentDto dto) {
        ComponentEntity componentEntity = new ComponentEntity();
        componentEntity.setSlideId(dto.getSlideId());
        componentEntity.setCategory(dto.getCategory());
        componentEntity.setName(dto.getName());
        componentEntity.setPropertiesJson(dto.getPropertiesJson());
        return componentEntity;
    }

    private void updateComponentFromDto(ComponentEntity componentEntity, ComponentDto dto) {
        componentEntity.setSlideId(dto.getSlideId());
        componentEntity.setCategory(dto.getCategory());
        componentEntity.setName(dto.getName());
        componentEntity.setPropertiesJson(dto.getPropertiesJson());
    }
}