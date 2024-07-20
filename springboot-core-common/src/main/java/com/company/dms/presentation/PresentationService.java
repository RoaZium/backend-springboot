package com.company.dms.presentation;

import com.company.dms.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PresentationService {
    @Autowired
    private PresentationRepository presentationRepository;

    @Autowired
    private UserRepository userRepository;

    public List<PresentationDto> getAllPresentations() {
        return presentationRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PresentationDto getPresentationById(UUID id) {
        Presentation presentation = presentationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Presentation not found"));
        return convertToDto(presentation);
    }

    public PresentationDto createPresentation(PresentationDto presentationDto) {
        if (!userRepository.existsById(presentationDto.getUserId())) {
            throw new RuntimeException("User not found");
        }

        Presentation presentation = convertToEntity(presentationDto);
        presentation.setCreatedAt(LocalDateTime.now());
        presentation.setUpdatedAt(LocalDateTime.now());
        presentation = presentationRepository.save(presentation);
        return convertToDto(presentation);
    }

    public PresentationDto updatePresentation(UUID id, PresentationDto presentationDto) {
        Presentation existingPresentation = presentationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Presentation not found"));

        existingPresentation.setName(presentationDto.getName());
        existingPresentation.setDescription(presentationDto.getDescription());
        existingPresentation.setMenuOrder(presentationDto.getMenuOrder());
        existingPresentation.setPropertiesJson(presentationDto.getPropertiesJson());
        existingPresentation.setUpdatedAt(LocalDateTime.now());

        existingPresentation = presentationRepository.save(existingPresentation);
        return convertToDto(existingPresentation);
    }

    public void deletePresentation(UUID id) {
        presentationRepository.deleteById(id);
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
}