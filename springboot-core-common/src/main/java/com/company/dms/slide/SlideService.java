package com.company.dms.slide;

import com.company.dms.user.User;
import com.company.dms.presentation.Presentation;
import com.company.dms.user.UserRepository;
import com.company.dms.presentation.PresentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public List<SlideDto> getAllSlides() {
        return slideRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public SlideDto getSlideById(UUID id) {
        Slide slide = slideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Slide not found"));
        return convertToDto(slide);
    }

    public SlideDto createSlide(SlideDto slideDto) {
        Slide slide = convertToEntity(slideDto);
        if (slide.getId() == null) {
            slide.setId(UUID.randomUUID());
        }
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

        existingSlide = slideRepository.save(existingSlide);
        return convertToDto(existingSlide);
    }

    public void deleteSlide(UUID id) {
        slideRepository.deleteById(id);
    }

    private SlideDto convertToDto(Slide slide) {
        SlideDto slideDto = new SlideDto();
        slideDto.setId(slide.getId());
        slideDto.setUserId(slide.getUser().getId());
        slideDto.setPresentationId(slide.getPresentation().getId());
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
        slide.setName(slideDto.getName());
        slide.setMenuOrder(slideDto.getMenuOrder());
        slide.setPresentationOrder(slideDto.getPresentationOrder());
        slide.setPropertiesJson(slideDto.getPropertiesJson());

        User user = userRepository.findById(slideDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        slide.setUser(user);

        Presentation presentation = presentationRepository.findById(slideDto.getPresentationId())
                .orElseThrow(() -> new RuntimeException("Presentation not found"));
        slide.setPresentation(presentation);

        return slide;
    }
}