package com.company.dms.section;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sections")
@Tag(name = "Sections", description = "Sections Management APIs")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @GetMapping
    public ResponseEntity<List<SectionDto>> getSections(
            @RequestParam(required = false) UUID userId,
            @RequestParam(required = false) String name) {
        return ResponseEntity.ok(sectionService.getSections(userId, name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectionDto> getSectionById(@PathVariable UUID id) {
        return ResponseEntity.ok(sectionService.getSectionById(id));
    }

    @PostMapping
    public ResponseEntity<SectionDto> createSection(@RequestBody SectionDto sectionDto) {
        return ResponseEntity.ok(sectionService.createSection(sectionDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SectionDto> updateSection(@PathVariable UUID id, @RequestBody SectionDto sectionDto) {
        return ResponseEntity.ok(sectionService.updateSection(id, sectionDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable UUID id) {
        sectionService.deleteSection(id);
        return ResponseEntity.noContent().build();
    }
}