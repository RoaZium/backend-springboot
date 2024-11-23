package com.company.dms.slide.service;

import com.company.dms.presentation.repository.PresentationRepository;
import com.company.dms.section.repository.SectionRepository;
import com.company.dms.slide.dto.SlideDto;
import com.company.dms.slide.entity.Slide;
import com.company.dms.slide.repository.SlideRepository;
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
    private PresentationRepository presentationRepository;

    @Autowired
    private SectionRepository sectionRepository;

    public List<SlideDto> getSlides(UUID userId, UUID presentationId, UUID sectionId, String name) {
        List<Slide> slideEntities;

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
        Slide slide = slideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Slide not found"));
        return convertToDto(slide);
    }

    public SlideDto createSlide(SlideDto slideDto) {
        if (slideDto.getPresentationId() != null && !presentationRepository.existsById(slideDto.getPresentationId())) {
            throw new RuntimeException("Presentation not found");
        }
        if (slideDto.getSectionId() != null && !sectionRepository.existsById(slideDto.getSectionId())) {
            throw new RuntimeException("Section not found");
        }

        Slide slide = convertToEntity(slideDto);
        slide.setCreatedAt(LocalDateTime.now());
        slide.setUpdatedAt(LocalDateTime.now());
        slide = slideRepository.save(slide);
        return convertToDto(slide);
    }

    public SlideDto updateSlide(UUID id, SlideDto slideDto) {
        Slide existingSlide = slideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Slide not found"));

        existingSlide.setName(slideDto.getName());
        existingSlide.setMenuOrder(slideDto.getMenuOrder());
        existingSlide.setPresentationOrder(slideDto.getPresentationOrder());
        existingSlide.setPropertiesJson(slideDto.getPropertiesJson());
        existingSlide.setSectionId(slideDto.getSectionId());
        existingSlide.setPresentationId(slideDto.getPresentationId());
        existingSlide.setUpdatedAt(LocalDateTime.now());

        Slide updatedSlide = slideRepository.save(existingSlide);
        return convertToDto(updatedSlide);
    }

    public void deleteSlide(UUID id) {
        slideRepository.deleteById(id);
    }

    private SlideDto convertToDto(Slide slide) {
        SlideDto slideDto = new SlideDto();
        slideDto.setId(slide.getId());
        slideDto.setUserId(slide.getUserId());
        slideDto.setPresentationId(slide.getPresentationId());
        slideDto.setSectionId(slide.getSectionId());
        slideDto.setName(slide.getName());
        slideDto.setMenuOrder(slide.getMenuOrder());
        slideDto.setPresentationOrder(slide.getPresentationOrder());
        slideDto.setPropertiesJson(slide.getPropertiesJson());
        slideDto.setCreatedAt(slide.getCreatedAt());
        slideDto.setUpdatedAt(slide.getUpdatedAt());
        return slideDto;
    }

    private Slide convertToEntity(SlideDto slideDto) {
        Slide slide = new Slide();
        slide.setId(slideDto.getId());
        slide.setUserId(slideDto.getUserId());
        slide.setPresentationId(slideDto.getPresentationId());
        slide.setSectionId(slideDto.getSectionId());
        slide.setName(slideDto.getName());
        slide.setMenuOrder(slideDto.getMenuOrder());
        slide.setPresentationOrder(slideDto.getPresentationOrder());
        slide.setPropertiesJson(slideDto.getPropertiesJson());
        return slide;
    }
}