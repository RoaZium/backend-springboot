package com.company.dms.component;

import com.company.dms.slide.SlideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ComponentService {
    private final ComponentRepository componentRepository;
    private final SlideRepository slideRepository;

    @Autowired
    public ComponentService(ComponentRepository componentRepository,
                            SlideRepository slideRepository) {
        this.componentRepository = componentRepository;
        this.slideRepository = slideRepository;
    }

    public List<ComponentDto> getAllComponents() {
        List<Component> components = componentRepository.findAll();
        return components.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<ComponentDto> getComponentById(UUID id) {
        return componentRepository.findById(id).map(this::convertToDto);
    }

    @Transactional
    public ComponentDto createComponent(ComponentDto componentDto) {
        if (!slideRepository.existsById(componentDto.getSlideId())) {
            throw new RuntimeException("Slide not found");
        }
        Component component = convertToEntity(componentDto);
        component.setCreatedAt(LocalDateTime.now());
        component.setUpdatedAt(LocalDateTime.now());
        Component savedComponent = componentRepository.save(component);
        return convertToDto(savedComponent);
    }

    @Transactional
    public ComponentDto updateComponent(UUID id, ComponentDto componentDto) {
        Component component = componentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Component not found"));

        if (!component.getSlideId().equals(componentDto.getSlideId()) &&
                !slideRepository.existsById(componentDto.getSlideId())) {
            throw new RuntimeException("Slide not found");
        }
        updateComponentFromDto(component, componentDto);
        component.setUpdatedAt(LocalDateTime.now());
        Component updatedComponent = componentRepository.save(component);
        return convertToDto(updatedComponent);
    }

    @Transactional
    public void deleteComponent(UUID id) {
        componentRepository.deleteById(id);
    }

    private ComponentDto convertToDto(Component component) {
        ComponentDto dto = new ComponentDto();
        dto.setId(component.getId());
        dto.setSlideId(component.getSlideId());
        dto.setCategory(component.getCategory());
        dto.setName(component.getName());
        dto.setPropertiesJson(component.getPropertiesJson());
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