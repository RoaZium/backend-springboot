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

    @Transactional(readOnly = true)
    public Page<ComponentDto> getAllComponents(Pageable pageable) {
        return componentRepository.findAll(pageable).map(this::convertToDto);
    }

    @Transactional(readOnly = true)
    public Optional<ComponentDto> getComponentById(String id) {
        return componentRepository.findById(id).map(this::convertToDto);
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
        if (componentDto.getId() != null) {
            component.setId(componentDto.getId());
        } else {
            component.setId(UUID.randomUUID().toString());  // 새로운 UUID 생성
        }
        component.setCategory(componentDto.getCategory());
        component.setName(componentDto.getName());
        component.setPropertiesJson(componentDto.getPropertiesJson());
        return component;
    }
}