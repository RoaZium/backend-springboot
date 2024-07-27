package com.company.dms.slide.controller;

import com.company.dms.slide.dto.SlideDto;
import com.company.dms.slide.service.SlideService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "전체 슬라이드 조회", description = "옵션 필터를 사용하여 슬라이드 목록을 조회합니다.")
    public ResponseEntity<List<SlideDto>> getSlides(
            @RequestParam(required = false) UUID userId,
            @RequestParam(required = false) UUID presentationId,
            @RequestParam(required = false) UUID sectionId,
            @RequestParam(required = false) String name) {
        return ResponseEntity.ok(slideService.getSlides(userId, presentationId, sectionId, name));
    }

    @GetMapping("/{id}")
    @Operation(summary = "슬라이드 조회", description = "슬라이드 ID로 슬라이드 정보를 검색합니다.")
    public ResponseEntity<SlideDto> getSlideById(@PathVariable UUID id) {
        return ResponseEntity.ok(slideService.getSlideById(id));
    }

    @PostMapping
    @Operation(summary = "슬라이드 생성", description = "새 슬라이드를 생성합니다.")
    public ResponseEntity<SlideDto> createSlide(@RequestBody SlideDto slideDto) {
        return ResponseEntity.ok(slideService.createSlide(slideDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "슬라이드 수정", description = "ID로 기존 슬라이드의 정보를 수정합니다.")
    public ResponseEntity<SlideDto> updateSlide(@PathVariable UUID id, @RequestBody SlideDto slideDto) {
        return ResponseEntity.ok(slideService.updateSlide(id, slideDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "슬라이드 삭제", description = "ID로 슬라이드를 삭제합니다.")
    public ResponseEntity<Void> deleteSlide(@PathVariable UUID id) {
        slideService.deleteSlide(id);
        return ResponseEntity.noContent().build();
    }
}