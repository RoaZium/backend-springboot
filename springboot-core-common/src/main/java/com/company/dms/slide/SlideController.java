package com.company.dms.slide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slides")
public class SlideController {

    @Autowired
    private SlideService slideService;

    @GetMapping
    public ResponseEntity<List<SlideDto>> getAllSlides() {
        return ResponseEntity.ok(slideService.getAllSlides());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SlideDto> getSlideById(@PathVariable String id) {
        return ResponseEntity.ok(slideService.getSlideById(id));
    }

    @PostMapping
    public ResponseEntity<SlideDto> createSlide(@RequestBody SlideDto slideDto) {
        return ResponseEntity.ok(slideService.createSlide(slideDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SlideDto> updateSlide(@PathVariable String id, @RequestBody SlideDto slideDto) {
        return ResponseEntity.ok(slideService.updateSlide(id, slideDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSlide(@PathVariable String id) {
        slideService.deleteSlide(id);
        return ResponseEntity.noContent().build();
    }
}