package com.company.dms.section;

import com.company.dms.user.UserRepository;
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
        List<Section> sections;

        if (userId != null && name != null) {
            sections = sectionRepository.findByUserIdAndNameContainingIgnoreCaseOrderByMenuOrder(userId, name);
        } else if (userId != null) {
            sections = sectionRepository.findByUserIdOrderByMenuOrder(userId);
        } else if (name != null) {
            sections = sectionRepository.findByNameContainingIgnoreCaseOrderByMenuOrder(name);
        } else {
            sections = sectionRepository.findAll();
        }

        return sections.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public SectionDto getSectionById(UUID id) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Section not found"));
        return convertToDto(section);
    }

    @Transactional
    public SectionDto createSection(SectionDto sectionDto) {
        if (!userRepository.existsById(sectionDto.getUserId())) {
            throw new RuntimeException("User not found");
        }

        Section section = convertToEntity(sectionDto);
        section.setCreatedAt(LocalDateTime.now());
        section.setUpdatedAt(LocalDateTime.now());
        section = sectionRepository.save(section);
        return convertToDto(section);
    }

    @Transactional
    public SectionDto updateSection(UUID id, SectionDto sectionDto) {
        Section existingSection = sectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Section not found"));

        existingSection.setName(sectionDto.getName());
        existingSection.setMenuOrder(sectionDto.getMenuOrder());
        existingSection.setUpdatedAt(LocalDateTime.now());

        existingSection = sectionRepository.save(existingSection);
        return convertToDto(existingSection);
    }

    @Transactional
    public void deleteSection(UUID id) {
        sectionRepository.deleteById(id);
    }

    private SectionDto convertToDto(Section section) {
        SectionDto dto = new SectionDto();
        dto.setId(section.getId());
        dto.setUserId(section.getUserId());
        dto.setName(section.getName());
        dto.setMenuOrder(section.getMenuOrder());
        dto.setCreatedAt(section.getCreatedAt());
        dto.setUpdatedAt(section.getUpdatedAt());
        return dto;
    }

    private Section convertToEntity(SectionDto dto) {
        Section section = new Section();
        section.setId(dto.getId());
        section.setUserId(dto.getUserId());
        section.setName(dto.getName());
        section.setMenuOrder(dto.getMenuOrder());
        return section;
    }
}