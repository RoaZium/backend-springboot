package com.company.dms.service;

import com.company.dms.dto.ComponentDto;
import com.company.dms.entity.Component;
import com.company.dms.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComponentService {

    private final ComponentRepository componentRepository;

    @Autowired
    public ComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    @Transactional(readOnly = true)
    public List<ComponentDto> getComponentsByCategory(String category) {
        return componentRepository.findByCategory(category).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ComponentDto createComponent(ComponentDto componentDto) {
        Component component = convertToEntity(componentDto);
        Component savedComponent = componentRepository.save(component);
        return convertToDto(savedComponent);
    }

    private ComponentDto convertToDto(Component component) {
        return new ComponentDto(
                component.getId(),
                component.getCategory(),
                component.getName(),
                component.getPropertiesJson()
        );
    }

    private Component convertToEntity(ComponentDto componentDto) {
        Component component = new Component();
        component.setCategory(componentDto.getCategory());
        component.setName(componentDto.getName());
        component.setPropertiesJson(componentDto.getPropertiesJson());
        return component;
    }
}