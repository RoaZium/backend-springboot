package com.company.dms.section.service;

import com.company.dms.section.dto.SectionDto;
import com.company.dms.section.entity.SectionEntity;
import com.company.dms.section.repository.SectionRepository;
import com.company.dms.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private UserRepository userRepository;

    public List<SectionDto> getSections(UUID userId, String name) {
        List<SectionEntity> sectionEntities;

        if (userId != null && name != null) {
            sectionEntities = sectionRepository.findByUserIdAndNameContainingIgnoreCaseOrderByMenuOrder(userId, name);
        } else if (userId != null) {
            sectionEntities = sectionRepository.findByUserIdOrderByMenuOrder(userId);
        } else if (name != null) {
            sectionEntities = sectionRepository.findByNameContainingIgnoreCaseOrderByMenuOrder(name);
        } else {
            sectionEntities = sectionRepository.findAll();
        }

        return sectionEntities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public SectionDto getSectionById(UUID id) {
        SectionEntity sectionEntity = sectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Section not found"));
        return convertToDto(sectionEntity);
    }

    @Transactional
    public SectionDto createSection(SectionDto sectionDto) {
        if (!userRepository.existsById(sectionDto.getUserId())) {
            throw new RuntimeException("User not found");
        }

        SectionEntity sectionEntity = convertToEntity(sectionDto);
        sectionEntity.setCreatedAt(LocalDateTime.now());
        sectionEntity.setUpdatedAt(LocalDateTime.now());
        sectionEntity = sectionRepository.save(sectionEntity);
        return convertToDto(sectionEntity);
    }

    @Transactional
    public SectionDto updateSection(UUID id, SectionDto sectionDto) {
        SectionEntity existingSectionEntity = sectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Section not found"));

        existingSectionEntity.setName(sectionDto.getName());
        existingSectionEntity.setMenuOrder(sectionDto.getMenuOrder());
        existingSectionEntity.setUpdatedAt(LocalDateTime.now());

        existingSectionEntity = sectionRepository.save(existingSectionEntity);
        return convertToDto(existingSectionEntity);
    }

    @Transactional
    public void deleteSection(UUID id) {
        sectionRepository.deleteById(id);
    }

    private SectionDto convertToDto(SectionEntity sectionEntity) {
        SectionDto dto = new SectionDto();
        dto.setId(sectionEntity.getId());
        dto.setUserId(sectionEntity.getUserId());
        dto.setName(sectionEntity.getName());
        dto.setMenuOrder(sectionEntity.getMenuOrder());
        dto.setCreatedAt(sectionEntity.getCreatedAt());
        dto.setUpdatedAt(sectionEntity.getUpdatedAt());
        return dto;
    }

    private SectionEntity convertToEntity(SectionDto dto) {
        SectionEntity sectionEntity = new SectionEntity();
        sectionEntity.setId(dto.getId());
        sectionEntity.setUserId(dto.getUserId());
        sectionEntity.setName(dto.getName());
        sectionEntity.setMenuOrder(dto.getMenuOrder());
        return sectionEntity;
    }
}