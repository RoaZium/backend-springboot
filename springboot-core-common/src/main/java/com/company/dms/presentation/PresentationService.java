package com.company.dms.presentation;

import com.company.dms.user.User;
import com.company.dms.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

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

    public PresentationDto getPresentationById(String id) {
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

    public PresentationDto updatePresentation(String id, PresentationDto presentationDto) {
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

    public void deletePresentation(String id) {
        presentationRepository.deleteById(id);
    }

    private PresentationDto convertToDto(Presentation presentation) {
        PresentationDto presentationDto = new PresentationDto();
        presentationDto.setId(presentation.getId());
        presentationDto.setUserId(presentation.getUser().getId());
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
        presentation.setName(presentationDto.getName());
        presentation.setDescription(presentationDto.getDescription());
        presentation.setMenuOrder(presentationDto.getMenuOrder());
        presentation.setPropertiesJson(presentationDto.getPropertiesJson());

        User user = userRepository.findById(presentationDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        presentation.setUser(user);

        return presentation;
    }
}