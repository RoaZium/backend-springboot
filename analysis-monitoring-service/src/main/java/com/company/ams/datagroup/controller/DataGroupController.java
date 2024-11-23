package com.company.ams.datagroup.controller;

import com.company.ams.datagroup.dto.DataGroupDto;
import com.company.ams.datagroup.service.DataGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/datagroups")
@Tag(name = "DataGroups", description = "DataGroups Management APIs")
public class DataGroupController {

    private final DataGroupService dataGroupService;

    @Autowired
    public DataGroupController(DataGroupService dataGroupService) {
        this.dataGroupService = dataGroupService;
    }

    @GetMapping
    @Operation(summary = "전체 데이터 그룹 조회", description = "옵션 필터를 사용하여 데이터 그룹 목록을 조회합니다.")
    public ResponseEntity<List<DataGroupDto>> getDataGroups(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name) {
        List<DataGroupDto> dataGroups = dataGroupService.getDataGroups(code, name);
        return ResponseEntity.ok(dataGroups);
    }

    @GetMapping("/{id}")
    @Operation(summary = "데이터 그룹 조회", description = "데이터 그룹 ID로 데이터 그룹 정보를 검색합니다.")
    public ResponseEntity<DataGroupDto> getDataGroupById(@PathVariable UUID id) {
        DataGroupDto dataGroup = dataGroupService.getDataGroupById(id);
        return dataGroup != null ? ResponseEntity.ok(dataGroup) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "데이터 그룹 생성", description = "새 데이터 그룹을 생성합니다.")
    public ResponseEntity<DataGroupDto> createDataGroup(@RequestBody DataGroupDto dataGroupDto) {
        DataGroupDto createdDataGroup = dataGroupService.createDataGroup(dataGroupDto);
        return new ResponseEntity<>(createdDataGroup, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "데이터 그룹 수정", description = "ID로 기존 데이터 그룹의 정보를 수정합니다.")
    public ResponseEntity<DataGroupDto> updateDataGroup(@PathVariable UUID id, @RequestBody DataGroupDto dataGroupDto) {
        DataGroupDto updatedDataGroup = dataGroupService.updateDataGroup(id, dataGroupDto);
        return updatedDataGroup != null ? ResponseEntity.ok(updatedDataGroup) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "데이터 그룹 삭제", description = "ID로 데이터 그룹을 삭제합니다.")
    public ResponseEntity<Void> deleteDataGroup(@PathVariable UUID id) {
        boolean deleted = dataGroupService.deleteDataGroup(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}