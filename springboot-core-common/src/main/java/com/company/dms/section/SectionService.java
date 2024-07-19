package com.company.dms.section;

import com.company.dms.user.User;
import com.company.dms.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private UserRepository userRepository;

    public List<SectionDto> getAllSections() {
        return sectionRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<SectionDto> getSectionsByUserId(UUID userId) {
        return sectionRepository.findByUserIdOrderByMenuOrder(userId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public SectionDto getSectionById(UUID id) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Section not found"));
        return convertToDto(section);
    }

    @Transactional
    public SectionDto createSection(SectionDto sectionDto) {
        User user = userRepository.findById(sectionDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Section section = convertToEntity(sectionDto);
        section.setUser(user);
        section = sectionRepository.save(section);
        return convertToDto(section);
    }

    @Transactional
    public SectionDto updateSection(UUID id, SectionDto sectionDto) {
        Section existingSection = sectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Section not found"));

        existingSection.setName(sectionDto.getName());
        existingSection.setMenuOrder(sectionDto.getMenuOrder());

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
        dto.setUserId(section.getUser().getId());
        dto.setName(section.getName());
        dto.setMenuOrder(section.getMenuOrder());
        dto.setCreatedAt(section.getCreatedAt());
        dto.setUpdatedAt(section.getUpdatedAt());
        return dto;
    }

    private Section convertToEntity(SectionDto dto) {
        Section section = new Section();
        section.setId(dto.getId());
        section.setName(dto.getName());
        section.setMenuOrder(dto.getMenuOrder());
        return section;
    }
}