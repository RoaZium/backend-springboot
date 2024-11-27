package com.company.dms.image.controller;

import com.company.dms.image.dto.ImageDto;
import com.company.dms.image.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/images")
@Tag(name = "Images", description = "Images Management APIs")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    @Operation(summary = "전체 이미지 조회", description = "옵션 필터를 사용하여 이미지 목록을 조회합니다.")
    public ResponseEntity<List<ImageDto>> getImages(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String contentType) {
        List<ImageDto> images = imageService.getImages(name, contentType);
        return ResponseEntity.ok(images);
    }

    @GetMapping("/{id}")
    @Operation(summary = "이미지 조회", description = "이미지 ID로 이미지 정보를 검색합니다.")
    public ResponseEntity<ImageDto> getImageById(@PathVariable UUID id) {
        ImageDto image = imageService.getImageById(id);
        return image != null ? ResponseEntity.ok(image) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "이미지 생성", description = "새 이미지를 생성합니다.")
    public ResponseEntity<ImageDto> createImage(@RequestBody ImageDto imageDto) {
        ImageDto createdImage = imageService.createImage(imageDto);
        return new ResponseEntity<>(createdImage, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "이미지 수정", description = "ID로 기존 이미지의 정보를 수정합니다.")
    public ResponseEntity<ImageDto> updateImage(@PathVariable UUID id, @RequestBody ImageDto imageDto) {
        ImageDto updatedImage = imageService.updateImage(id, imageDto);
        return updatedImage != null ? ResponseEntity.ok(updatedImage) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "이미지 삭제", description = "ID로 이미지를 삭제합니다.")
    public ResponseEntity<Void> deleteImage(@PathVariable UUID id) {
        boolean deleted = imageService.deleteImage(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}