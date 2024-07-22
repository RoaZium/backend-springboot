package com.company.dms.slide;

import com.company.dms.user.UserRepository;
import com.company.dms.presentation.PresentationRepository;
import com.company.dms.section.SectionRepository;
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
        List<Slide> slides;

        if (userId != null && name != null) {
            slides = slideRepository.findByUserIdAndNameContainingIgnoreCase(userId, name);
        } else if (presentationId != null && name != null) {
            slides = slideRepository.findByPresentationIdAndNameContainingIgnoreCaseOrderByPresentationOrder(presentationId, name);
        } else if (sectionId != null && name != null) {
            slides = slideRepository.findBySectionIdAndNameContainingIgnoreCaseOrderByMenuOrder(sectionId, name);
        } else if (userId != null) {
            slides = slideRepository.findByUserId(userId);
        } else if (presentationId != null) {
            slides = slideRepository.findByPresentationIdOrderByPresentationOrder(presentationId);
        } else if (sectionId != null) {
            slides = slideRepository.findBySectionIdOrderByMenuOrder(sectionId);
        } else if (name != null) {
            slides = slideRepository.findByNameContainingIgnoreCase(name);
        } else {
            slides = slideRepository.findAll();
        }

        return slides.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public SlideDto getSlideById(UUID id) {
        Slide slide = slideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Slide not found"));
        return convertToDto(slide);
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
        existingSlide.setUpdatedAt(LocalDateTime.now());

        if (slideDto.getPresentationId() != null && !slideDto.getPresentationId().equals(existingSlide.getPresentationId())) {
            if (!presentationRepository.existsById(slideDto.getPresentationId())) {
                throw new RuntimeException("Presentation not found");
            }
            existingSlide.setPresentationId(slideDto.getPresentationId());
        }

        if (slideDto.getSectionId() != null && !slideDto.getSectionId().equals(existingSlide.getSectionId())) {
            if (!sectionRepository.existsById(slideDto.getSectionId())) {
                throw new RuntimeException("Section not found");
            }
            existingSlide.setSectionId(slideDto.getSectionId());
        }

        existingSlide = slideRepository.save(existingSlide);
        return convertToDto(existingSlide);
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