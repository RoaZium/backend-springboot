package com.company.dms.image.service;

import com.company.dms.image.dto.ImageDto;
import com.company.dms.image.entity.Image;
import com.company.dms.image.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<ImageDto> getImages(String name, String contentType) {
        List<Image> images;

        if (name != null && contentType != null) {
            images = imageRepository.findByNameContainingIgnoreCaseAndContentType(name, contentType);
        } else if (name != null) {
            images = imageRepository.findByNameContainingIgnoreCase(name);
        } else if (contentType != null) {
            images = imageRepository.findByContentType(contentType);
        } else {
            images = imageRepository.findAll();
        }

        return images.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public ImageDto getImageById(UUID id) {
        return imageRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    @Transactional
    public ImageDto createImage(ImageDto imageDto) {
        if (imageDto.getId() == null) {
            imageDto.setId(UUID.randomUUID());
        }

        Image image = convertToEntity(imageDto);
        Image savedImage = imageRepository.save(image);
        return convertToDto(savedImage);
    }

    @Transactional
    public ImageDto updateImage(UUID id, ImageDto imageDto) {
        return imageRepository.findById(id)
                .map(image -> {
                    updateImageFromDto(image, imageDto);
                    Image updatedImage = imageRepository.save(image);
                    return convertToDto(updatedImage);
                })
                .orElse(null);
    }

    @Transactional
    public boolean deleteImage(UUID id) {
        if (imageRepository.existsById(id)) {
            imageRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private ImageDto convertToDto(Image image) {
        ImageDto dto = new ImageDto();
        dto.setId(image.getId());
        dto.setName(image.getName());
        dto.setPath(image.getPath());
        dto.setSize(image.getSize());
        dto.setData(image.getData());
        dto.setContentType(image.getContentType());
        dto.setCreatedAt(image.getCreatedAt());
        dto.setUpdatedAt(image.getUpdatedAt());
        return dto;
    }

    private Image convertToEntity(ImageDto dto) {
        Image image = new Image();
        image.setId(dto.getId());
        image.setName(dto.getName());
        image.setPath(dto.getPath());
        image.setSize(dto.getSize());
        image.setData(dto.getData());
        image.setContentType(dto.getContentType());
        return image;
    }

    private void updateImageFromDto(Image image, ImageDto dto) {
        image.setName(dto.getName());
        image.setPath(dto.getPath());
        image.setSize(dto.getSize());
        image.setData(dto.getData());
        image.setContentType(dto.getContentType());
    }
}