package com.company.ams.dataitem.service;

import com.company.ams.datagroup.repository.DataGroupRepository;
import com.company.ams.dataitem.repository.DataItemRepository;
import com.company.ams.dataitem.dto.DataItemDto;
import com.company.ams.dataitem.entity.DataItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DataItemService {

    private final DataItemRepository dataItemRepository;
    private final DataGroupRepository dataGroupRepository;

    @Autowired
    public DataItemService(DataItemRepository dataItemRepository, DataGroupRepository dataGroupRepository) {
        this.dataItemRepository = dataItemRepository;
        this.dataGroupRepository = dataGroupRepository;
    }

    public List<DataItemDto> getDataItems(UUID groupId, String code, String name) {
        List<DataItem> dataItems;
        if (groupId != null && code != null && name != null) {
            dataItems = dataItemRepository.findByGroupIdAndCodeContainingIgnoreCaseAndNameContainingIgnoreCase(groupId, code, name);
        } else if (groupId != null && code != null) {
            dataItems = dataItemRepository.findByGroupIdAndCodeContainingIgnoreCase(groupId, code);
        } else if (groupId != null && name != null) {
            dataItems = dataItemRepository.findByGroupIdAndNameContainingIgnoreCase(groupId, name);
        } else if (groupId != null) {
            dataItems = dataItemRepository.findByGroupId(groupId);
        } else if (code != null && name != null) {
            dataItems = dataItemRepository.findByCodeContainingIgnoreCaseAndNameContainingIgnoreCase(code, name);
        } else if (code != null) {
            dataItems = dataItemRepository.findByCodeContainingIgnoreCase(code);
        } else if (name != null) {
            dataItems = dataItemRepository.findByNameContainingIgnoreCase(name);
        } else {
            dataItems = dataItemRepository.findAll();
        }
        return dataItems.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public DataItemDto getDataItemById(UUID id) {
        return dataItemRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    @Transactional
    public DataItemDto createDataItem(DataItemDto dataItemDto) {
        if (!dataGroupRepository.existsById(dataItemDto.getGroupId())) {
            throw new RuntimeException("DataGroup not found");
        }

        DataItem dataItem = convertToEntity(dataItemDto);

        if (dataItem.getId() == null) {
            dataItem.setId(UUID.randomUUID());
        }

        dataItem.setCreatedAt(LocalDateTime.now());
        dataItem.setUpdatedAt(LocalDateTime.now());

        DataItem savedDataItem = dataItemRepository.save(dataItem);
        return convertToDto(savedDataItem);
    }

    @Transactional
    public DataItemDto updateDataItem(UUID id, DataItemDto dataItemDto) {
        return dataItemRepository.findById(id)
                .map(dataItem -> {
                    updateDataItemFromDto(dataItem, dataItemDto);
                    dataItem.setUpdatedAt(LocalDateTime.now());
                    DataItem updatedDataItem = dataItemRepository.save(dataItem);
                    return convertToDto(updatedDataItem);
                })
                .orElse(null);
    }

    @Transactional
    public boolean deleteDataItem(UUID id) {
        if (dataItemRepository.existsById(id)) {
            dataItemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private DataItemDto convertToDto(DataItem dataItem) {
        DataItemDto dto = new DataItemDto();
        dto.setId(dataItem.getId());
        dto.setGroupId(dataItem.getGroupId());
        dto.setCode(dataItem.getCode());
        dto.setName(dataItem.getName());
        dto.setMenuOrder(dataItem.getMenuOrder());
        dto.setCreatedAt(dataItem.getCreatedAt());
        dto.setUpdatedAt(dataItem.getUpdatedAt());
        return dto;
    }

    private DataItem convertToEntity(DataItemDto dto) {
        DataItem dataItem = new DataItem(
                dto.getGroupId(),
                dto.getCode(),
                dto.getName(),
                dto.getMenuOrder()
        );
        dataItem.setId(dto.getId());
        return dataItem;
    }

    private void updateDataItemFromDto(DataItem dataItem, DataItemDto dto) {
        if (!dataItem.getGroupId().equals(dto.getGroupId())) {
            if (!dataGroupRepository.existsById(dto.getGroupId())) {
                throw new RuntimeException("DataGroup not found");
            }
            dataItem.setGroupId(dto.getGroupId());
        }
        dataItem.setCode(dto.getCode());
        dataItem.setName(dto.getName());
        dataItem.setMenuOrder(dto.getMenuOrder());
    }
}