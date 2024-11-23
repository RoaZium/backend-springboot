package com.company.dms.datagroup.service;

import com.company.dms.datagroup.dto.DataGroupDto;
import com.company.dms.datagroup.entity.DataGroup;
import com.company.dms.datagroup.repository.DataGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DataGroupService {

    private final DataGroupRepository dataGroupRepository;

    @Autowired
    public DataGroupService(DataGroupRepository dataGroupRepository) {
        this.dataGroupRepository = dataGroupRepository;
    }

    public List<DataGroupDto> getDataGroups(String code, String name) {
        List<DataGroup> dataGroups;
        if (code != null && name != null) {
            dataGroups = dataGroupRepository.findByCodeContainingIgnoreCaseAndNameContainingIgnoreCase(code, name);
        } else if (code != null) {
            dataGroups = dataGroupRepository.findByCodeContainingIgnoreCase(code);
        } else if (name != null) {
            dataGroups = dataGroupRepository.findByNameContainingIgnoreCase(name);
        } else {
            dataGroups = dataGroupRepository.findAll();
        }
        return dataGroups.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public DataGroupDto getDataGroupById(UUID id) {
        return dataGroupRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    @Transactional
    public DataGroupDto createDataGroup(DataGroupDto dataGroupDto) {
        DataGroup dataGroup = convertToEntity(dataGroupDto);

        if (dataGroup.getId() == null) {
            dataGroup.setId(UUID.randomUUID());
        }

        dataGroup.setCreatedAt(LocalDateTime.now());
        dataGroup.setUpdatedAt(LocalDateTime.now());
        DataGroup savedDataGroup = dataGroupRepository.save(dataGroup);
        return convertToDto(savedDataGroup);
    }

    @Transactional
    public DataGroupDto updateDataGroup(UUID id, DataGroupDto dataGroupDto) {
        return dataGroupRepository.findById(id)
                .map(dataGroup -> {
                    updateDataGroupFromDto(dataGroup, dataGroupDto);
                    dataGroup.setUpdatedAt(LocalDateTime.now());
                    DataGroup updatedDataGroup = dataGroupRepository.save(dataGroup);
                    return convertToDto(updatedDataGroup);
                })
                .orElse(null);
    }

    @Transactional
    public boolean deleteDataGroup(UUID id) {
        if (dataGroupRepository.existsById(id)) {
            dataGroupRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private DataGroupDto convertToDto(DataGroup dataGroup) {
        DataGroupDto dto = new DataGroupDto();
        dto.setId(dataGroup.getId());
        dto.setCode(dataGroup.getCode());
        dto.setName(dataGroup.getName());
        dto.setMenuOrder(dataGroup.getMenuOrder());
        dto.setCreatedAt(dataGroup.getCreatedAt());
        dto.setUpdatedAt(dataGroup.getUpdatedAt());
        return dto;
    }

    private DataGroup convertToEntity(DataGroupDto dto) {
        DataGroup dataGroup = new DataGroup();
        dataGroup.setId(dto.getId());
        dataGroup.setCode(dto.getCode());
        dataGroup.setName(dto.getName());
        dataGroup.setMenuOrder(dto.getMenuOrder());
        return dataGroup;
    }

    private void updateDataGroupFromDto(DataGroup dataGroup, DataGroupDto dto) {
        dataGroup.setCode(dto.getCode());
        dataGroup.setName(dto.getName());
        dataGroup.setMenuOrder(dto.getMenuOrder());
    }
}