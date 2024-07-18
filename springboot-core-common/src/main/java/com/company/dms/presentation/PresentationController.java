package com.company.dms.presentation;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/presentations")
@Tag(name = "Presentations", description = "Presentations Management APIs")
public class PresentationController {

    @Autowired
    private PresentationService presentationService;

    @GetMapping
    public ResponseEntity<List<PresentationDto>> getAllPresentations() {
        return ResponseEntity.ok(presentationService.getAllPresentations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PresentationDto> getPresentationById(@PathVariable UUID id) {
        return ResponseEntity.ok(presentationService.getPresentationById(id));
    }

    @PostMapping
    public ResponseEntity<PresentationDto> createPresentation(@RequestBody PresentationDto presentationDto) {
        return ResponseEntity.ok(presentationService.createPresentation(presentationDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PresentationDto> updatePresentation(@PathVariable UUID id, @RequestBody PresentationDto presentationDto) {
        return ResponseEntity.ok(presentationService.updatePresentation(id, presentationDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePresentation(@PathVariable UUID id) {
        presentationService.deletePresentation(id);
        return ResponseEntity.noContent().build();
    }
}