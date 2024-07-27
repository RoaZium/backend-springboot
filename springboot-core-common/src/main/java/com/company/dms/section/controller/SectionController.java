package com.company.dms.section.controller;

import com.company.dms.section.dto.SectionDto;
import com.company.dms.section.service.SectionService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "전체 섹션 조회", description = "옵션 필터를 사용하여 섹션 목록을 조회합니다.")
    public ResponseEntity<List<SectionDto>> getSections(
            @RequestParam(required = false) UUID userId,
            @RequestParam(required = false) String name) {
        return ResponseEntity.ok(sectionService.getSections(userId, name));
    }

    @GetMapping("/{id}")
    @Operation(summary = "섹션 조회", description = "섹션 ID로 섹션 정보를 검색합니다.")
    public ResponseEntity<SectionDto> getSectionById(@PathVariable UUID id) {
        return ResponseEntity.ok(sectionService.getSectionById(id));
    }

    @PostMapping
    @Operation(summary = "섹션 생성", description = "새 섹션을 생성합니다.")
    public ResponseEntity<SectionDto> createSection(@RequestBody SectionDto sectionDto) {
        return ResponseEntity.ok(sectionService.createSection(sectionDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "섹션 수정", description = "ID로 기존 섹션의 정보를 수정합니다.")
    public ResponseEntity<SectionDto> updateSection(@PathVariable UUID id, @RequestBody SectionDto sectionDto) {
        return ResponseEntity.ok(sectionService.updateSection(id, sectionDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "섹션 삭제", description = "ID로 섹션을 삭제합니다.")
    public ResponseEntity<Void> deleteSection(@PathVariable UUID id) {
        sectionService.deleteSection(id);
        return ResponseEntity.noContent().build();
    }
}