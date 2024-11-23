package com.company.ams.presentation.controller;

import com.company.ams.presentation.dto.PresentationDto;
import com.company.ams.presentation.service.PresentationService;
import io.swagger.v3.oas.annotations.Operation;
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

    private final PresentationService presentationService;

    @Autowired
    public PresentationController(PresentationService presentationService) {
        this.presentationService = presentationService;
    }

    @GetMapping
    @Operation(summary = "전체 프레젠테이션 조회", description = "옵션 필터를 사용하여 프레젠테이션 목록을 조회합니다.")
    public ResponseEntity<List<PresentationDto>> getPresentations(
            @RequestParam(required = false) UUID userId,
            @RequestParam(required = false) String name) {
        List<PresentationDto> presentations = presentationService.getPresentations(userId, name);
        return ResponseEntity.ok(presentations);
    }

    @GetMapping("/{id}")
    @Operation(summary = "프레젠테이션 조회", description = "프레젠테이션 ID로 프레젠테이션 정보를 검색합니다.")
    public ResponseEntity<PresentationDto> getPresentationById(@PathVariable UUID id) {
        return ResponseEntity.ok(presentationService.getPresentationById(id));
    }

    @PostMapping
    @Operation(summary = "프레젠테이션 생성", description = "새 프레젠테이션 생성합니다.")
    public ResponseEntity<PresentationDto> createPresentation(@RequestBody PresentationDto presentationDto) {
        return ResponseEntity.ok(presentationService.createPresentation(presentationDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "프레젠테이션 수정", description = "ID로 기존 프레젠테이션의 정보를 수정합니다.")
    public ResponseEntity<PresentationDto> updatePresentation(@PathVariable UUID id, @RequestBody PresentationDto presentationDto) {
        return ResponseEntity.ok(presentationService.updatePresentation(id, presentationDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "프레젠테이션 삭제", description = "ID로 프레젠테이션을 삭제합니다.")
    public ResponseEntity<Void> deletePresentation(@PathVariable UUID id) {
        boolean deleted = presentationService.deletePresentation(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}