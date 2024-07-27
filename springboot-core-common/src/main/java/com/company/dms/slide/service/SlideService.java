package com.company.dms.slide.service;

import com.company.dms.slide.dto.SlideDto;
import com.company.dms.slide.entity.SlideEntity;
import com.company.dms.slide.repository.SlideRepository;
import com.company.dms.user.repository.UserRepository;
import com.company.dms.presentation.repository.PresentationRepository;
import com.company.dms.section.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SlideService {
    @Autowired
    private SlideRepository slideRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PresentationRepository presentationRepository;

    @Autowired
    private SectionRepository sectionRepository;

    public List<SlideDto> getSlides(UUID userId, UUID presentationId, UUID sectionId, String name) {
        List<SlideEntity> slideEntities;

        if (userId != null && name != null) {
            slideEntities = slideRepository.findByUserIdAndNameContainingIgnoreCase(userId, name);
        } else if (presentationId != null && name != null) {
            slideEntities = slideRepository.findByPresentationIdAndNameContainingIgnoreCaseOrderByPresentationOrder(presentationId, name);
        } else if (sectionId != null && name != null) {
            slideEntities = slideRepository.findBySectionIdAndNameContainingIgnoreCaseOrderByMenuOrder(sectionId, name);
        } else if (userId != null) {
            slideEntities = slideRepository.findByUserId(userId);
        } else if (presentationId != null) {
            slideEntities = slideRepository.findByPresentationIdOrderByPresentationOrder(presentationId);
        } else if (sectionId != null) {
            slideEntities = slideRepository.findBySectionIdOrderByMenuOrder(sectionId);
        } else if (name != null) {
            slideEntities = slideRepository.findByNameContainingIgnoreCase(name);
        } else {
            slideEntities = slideRepository.findAll();
        }

        return slideEntities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public SlideDto getSlideById(UUID id) {
        SlideEntity slideEntity = slideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Slide not found"));
        return convertToDto(slideEntity);
    }

    public SlideDto createSlide(SlideDto slideDto) {
        if (!userRepository.existsById(slideDto.getUserId())) {
            throw new RuntimeException("User not found");
        }
        if (slideDto.getPresentationId() != null && !presentationRepository.existsById(slideDto.getPresentationId())) {
            throw new RuntimeException("Presentation not found");
        }
        if (slideDto.getSectionId() != null && !sectionRepository.existsById(slideDto.getSectionId())) {
            throw new RuntimeException("Section not found");
        }

        SlideEntity slideEntity = convertToEntity(slideDto);
        slideEntity.setCreatedAt(LocalDateTime.now());
        slideEntity.setUpdatedAt(LocalDateTime.now());
        slideEntity = slideRepository.save(slideEntity);
        return convertToDto(slideEntity);
    }

    public SlideDto updateSlide(UUID id, SlideDto slideDto) {
        SlideEntity existingSlideEntity = slideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Slide not found"));

        existingSlideEntity.setName(slideDto.getName());
        existingSlideEntity.setMenuOrder(slideDto.getMenuOrder());
        existingSlideEntity.setPresentationOrder(slideDto.getPresentationOrder());
        existingSlideEntity.setPropertiesJson(slideDto.getPropertiesJson());
        existingSlideEntity.setUpdatedAt(LocalDateTime.now());

        if (slideDto.getPresentationId() != null && !slideDto.getPresentationId().equals(existingSlideEntity.getPresentationId())) {
            if (!presentationRepository.existsById(slideDto.getPresentationId())) {
                throw new RuntimeException("Presentation not found");
            }
            existingSlideEntity.setPresentationId(slideDto.getPresentationId());
        }

        if (slideDto.getSectionId() != null && !slideDto.getSectionId().equals(existingSlideEntity.getSectionId())) {
            if (!sectionRepository.existsById(slideDto.getSectionId())) {
                throw new RuntimeException("Section not found");
            }
            existingSlideEntity.setSectionId(slideDto.getSectionId());
        }

        existingSlideEntity = slideRepository.save(existingSlideEntity);
        return convertToDto(existingSlideEntity);
    }

    public void deleteSlide(UUID id) {
        slideRepository.deleteById(id);
    }

    private SlideDto convertToDto(SlideEntity slideEntity) {
        SlideDto slideDto = new SlideDto();
        slideDto.setId(slideEntity.getId());
        slideDto.setUserId(slideEntity.getUserId());
        slideDto.setPresentationId(slideEntity.getPresentationId());
        slideDto.setSectionId(slideEntity.getSectionId());
        slideDto.setName(slideEntity.getName());
        slideDto.setMenuOrder(slideEntity.getMenuOrder());
        slideDto.setPresentationOrder(slideEntity.getPresentationOrder());
        slideDto.setPropertiesJson(slideEntity.getPropertiesJson());
        slideDto.setCreatedAt(slideEntity.getCreatedAt());
        slideDto.setUpdatedAt(slideEntity.getUpdatedAt());
        return slideDto;
    }

    private SlideEntity convertToEntity(SlideDto slideDto) {
        SlideEntity slideEntity = new SlideEntity();
        slideEntity.setId(slideDto.getId());
        slideEntity.setUserId(slideDto.getUserId());
        slideEntity.setPresentationId(slideDto.getPresentationId());
        slideEntity.setSectionId(slideDto.getSectionId());
        slideEntity.setName(slideDto.getName());
        slideEntity.setMenuOrder(slideDto.getMenuOrder());
        slideEntity.setPresentationOrder(slideDto.getPresentationOrder());
        slideEntity.setPropertiesJson(slideDto.getPropertiesJson());
        return slideEntity;
    }
}