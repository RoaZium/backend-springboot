package com.company.dms.dataitem.controller;

import com.company.dms.dataitem.dto.DataItemDto;
import com.company.dms.dataitem.service.DataItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/dataitems")
@Tag(name = "DataItems", description = "DataItems Management APIs")
public class DataItemController {

    private final DataItemService dataItemService;

    @Autowired
    public DataItemController(DataItemService dataItemService) {
        this.dataItemService = dataItemService;
    }

    @GetMapping
    @Operation(summary = "전체 데이터 아이템 조회", description = "옵션 필터를 사용하여 데이터 아이템 목록을 조회합니다.")
    public ResponseEntity<List<DataItemDto>> getDataItems(
            @RequestParam(required = false) UUID groupId,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name) {
        List<DataItemDto> dataItems = dataItemService.getDataItems(groupId, code, name);
        return ResponseEntity.ok(dataItems);
    }

    @GetMapping("/{id}")
    @Operation(summary = "데이터 아이템 조회", description = "데이터 아이템 ID로 데이터 아이템 정보를 검색합니다.")
    public ResponseEntity<DataItemDto> getDataItemById(@PathVariable UUID id) {
        DataItemDto dataItem = dataItemService.getDataItemById(id);
        return dataItem != null ? ResponseEntity.ok(dataItem) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "데이터 아이템 생성", description = "새 데이터 아이템을 생성합니다.")
    public ResponseEntity<DataItemDto> createDataItem(@RequestBody DataItemDto dataItemDto) {
        DataItemDto createdDataItem = dataItemService.createDataItem(dataItemDto);
        return new ResponseEntity<>(createdDataItem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "데이터 아이템 수정", description = "ID로 기존 데이터 아이템의 정보를 수정합니다.")
    public ResponseEntity<DataItemDto> updateDataItem(@PathVariable UUID id, @Valid @RequestBody DataItemDto dataItemDto) {
        DataItemDto updatedDataItem = dataItemService.updateDataItem(id, dataItemDto);
        return updatedDataItem != null ? ResponseEntity.ok(updatedDataItem) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "데이터 아이템 삭제", description = "ID로 데이터 아이템을 삭제합니다.")
    public ResponseEntity<Void> deleteDataItem(@PathVariable UUID id) {
        boolean deleted = dataItemService.deleteDataItem(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}