package com.company.dms.slide;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/slides")
@Tag(name = "Slides", description = "Slides Management APIs")
public class SlideController {

    @Autowired
    private SlideService slideService;

    @GetMapping
    public ResponseEntity<List<SlideDto>> getAllSlides() {
        return ResponseEntity.ok(slideService.getAllSlides());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SlideDto> getSlideById(@PathVariable UUID id) {
        return ResponseEntity.ok(slideService.getSlideById(id));
    }

    @PostMapping
    public ResponseEntity<SlideDto> createSlide(@RequestBody SlideDto slideDto) {
        return ResponseEntity.ok(slideService.createSlide(slideDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SlideDto> updateSlide(@PathVariable UUID id, @RequestBody SlideDto slideDto) {
        return ResponseEntity.ok(slideService.updateSlide(id, slideDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSlide(@PathVariable UUID id) {
        slideService.deleteSlide(id);
        return ResponseEntity.noContent().build();
    }
}