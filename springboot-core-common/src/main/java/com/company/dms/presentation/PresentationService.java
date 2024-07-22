package com.company.dms.presentation;

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
        List<Presentation> presentations;

        if (userId != null) {
            presentations = presentationRepository.findByUserIdOrderByMenuOrder(userId);
        } else if (name != null) {
            presentations = presentationRepository.findByNameContainingIgnoreCase(name);
        } else {
            presentations = presentationRepository.findAll();
        }

        return presentations.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public PresentationDto getPresentationById(UUID id) {
        Presentation presentation = presentationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Presentation not found"));
        return convertToDto(presentation);
    }

    public PresentationDto createPresentation(PresentationDto presentationDto) {
        Presentation presentation = convertToEntity(presentationDto);
        presentation.setCreatedAt(LocalDateTime.now());
        presentation.setUpdatedAt(LocalDateTime.now());
        presentation = presentationRepository.save(presentation);
        return convertToDto(presentation);
    }

    public PresentationDto updatePresentation(UUID id, PresentationDto presentationDto) {
        Presentation existingPresentation = presentationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Presentation not found"));

        updatePresentationFromDto(existingPresentation, presentationDto);
        existingPresentation.setUpdatedAt(LocalDateTime.now());

        existingPresentation = presentationRepository.save(existingPresentation);
        return convertToDto(existingPresentation);
    }

    public boolean deletePresentation(UUID id) {
        if (presentationRepository.existsById(id)) {
            presentationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private PresentationDto convertToDto(Presentation presentation) {
        PresentationDto presentationDto = new PresentationDto();
        presentationDto.setId(presentation.getId());
        presentationDto.setUserId(presentation.getUserId());
        presentationDto.setName(presentation.getName());
        presentationDto.setDescription(presentation.getDescription());
        presentationDto.setMenuOrder(presentation.getMenuOrder());
        presentationDto.setPropertiesJson(presentation.getPropertiesJson());
        presentationDto.setCreatedAt(presentation.getCreatedAt());
        presentationDto.setUpdatedAt(presentation.getUpdatedAt());
        return presentationDto;
    }

    private Presentation convertToEntity(PresentationDto presentationDto) {
        Presentation presentation = new Presentation();
        presentation.setId(presentationDto.getId());
        presentation.setUserId(presentationDto.getUserId());
        presentation.setName(presentationDto.getName());
        presentation.setDescription(presentationDto.getDescription());
        presentation.setMenuOrder(presentationDto.getMenuOrder());
        presentation.setPropertiesJson(presentationDto.getPropertiesJson());
        return presentation;
    }

    private void updatePresentationFromDto(Presentation presentation, PresentationDto presentationDto) {
        presentation.setName(presentationDto.getName());
        presentation.setDescription(presentationDto.getDescription());
        presentation.setMenuOrder(presentationDto.getMenuOrder());
        presentation.setPropertiesJson(presentationDto.getPropertiesJson());
    }
}