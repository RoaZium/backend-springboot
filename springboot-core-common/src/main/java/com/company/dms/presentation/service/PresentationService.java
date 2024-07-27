package com.company.dms.presentation.service;

import com.company.dms.presentation.dto.PresentationDto;
import com.company.dms.presentation.entity.PresentationEntity;
import com.company.dms.presentation.repository.PresentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PresentationService {
    private final PresentationRepository presentationRepository;

    @Autowired
    public PresentationService(PresentationRepository presentationRepository) {
        this.presentationRepository = presentationRepository;
    }

    public List<PresentationDto> getPresentations(UUID userId, String name) {
        List<PresentationEntity> presentationEntities;

        if (userId != null) {
            presentationEntities = presentationRepository.findByUserIdOrderByMenuOrder(userId);
        } else if (name != null) {
            presentationEntities = presentationRepository.findByNameContainingIgnoreCase(name);
        } else {
            presentationEntities = presentationRepository.findAll();
        }

        return presentationEntities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public PresentationDto getPresentationById(UUID id) {
        PresentationEntity presentationEntity = presentationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Presentation not found"));
        return convertToDto(presentationEntity);
    }

    public PresentationDto createPresentation(PresentationDto presentationDto) {
        PresentationEntity presentationEntity = convertToEntity(presentationDto);
        presentationEntity.setCreatedAt(LocalDateTime.now());
        presentationEntity.setUpdatedAt(LocalDateTime.now());
        presentationEntity = presentationRepository.save(presentationEntity);
        return convertToDto(presentationEntity);
    }

    public PresentationDto updatePresentation(UUID id, PresentationDto presentationDto) {
        PresentationEntity existingPresentationEntity = presentationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Presentation not found"));

        updatePresentationFromDto(existingPresentationEntity, presentationDto);
        existingPresentationEntity.setUpdatedAt(LocalDateTime.now());

        existingPresentationEntity = presentationRepository.save(existingPresentationEntity);
        return convertToDto(existingPresentationEntity);
    }

    public boolean deletePresentation(UUID id) {
        if (presentationRepository.existsById(id)) {
            presentationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private PresentationDto convertToDto(PresentationEntity presentationEntity) {
        PresentationDto presentationDto = new PresentationDto();
        presentationDto.setId(presentationEntity.getId());
        presentationDto.setUserId(presentationEntity.getUserId());
        presentationDto.setName(presentationEntity.getName());
        presentationDto.setDescription(presentationEntity.getDescription());
        presentationDto.setMenuOrder(presentationEntity.getMenuOrder());
        presentationDto.setPropertiesJson(presentationEntity.getPropertiesJson());
        presentationDto.setCreatedAt(presentationEntity.getCreatedAt());
        presentationDto.setUpdatedAt(presentationEntity.getUpdatedAt());
        return presentationDto;
    }

    private PresentationEntity convertToEntity(PresentationDto presentationDto) {
        PresentationEntity presentationEntity = new PresentationEntity();
        presentationEntity.setId(presentationDto.getId());
        presentationEntity.setUserId(presentationDto.getUserId());
        presentationEntity.setName(presentationDto.getName());
        presentationEntity.setDescription(presentationDto.getDescription());
        presentationEntity.setMenuOrder(presentationDto.getMenuOrder());
        presentationEntity.setPropertiesJson(presentationDto.getPropertiesJson());
        return presentationEntity;
    }

    private void updatePresentationFromDto(PresentationEntity presentationEntity, PresentationDto presentationDto) {
        presentationEntity.setName(presentationDto.getName());
        presentationEntity.setDescription(presentationDto.getDescription());
        presentationEntity.setMenuOrder(presentationDto.getMenuOrder());
        presentationEntity.setPropertiesJson(presentationDto.getPropertiesJson());
    }
}