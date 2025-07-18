package com.company.dms.user.service;

import com.company.dms.user.dto.UserPermissionDto;
import com.company.dms.user.entity.UserPermission;
import com.company.dms.user.repository.UserPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserPermissionService {

    private final UserPermissionRepository userPermissionRepository;

    @Autowired
    public UserPermissionService(UserPermissionRepository userPermissionRepository) {
        this.userPermissionRepository = userPermissionRepository;
    }

    /**
     * 사용자 권한 목록 조회
     */
    public List<UserPermissionDto> getUserPermissions(UUID userId, UUID createdBy) {
        List<UserPermission> userPermissions;

        if (userId != null && createdBy != null) {
            userPermissions = userPermissionRepository.findByUserIdAndCreatedBy(userId, createdBy);
        } else if (userId != null) {
            userPermissions = userPermissionRepository.findByUserId(userId);
        } else if (createdBy != null) {
            userPermissions = userPermissionRepository.findByCreatedBy(createdBy);
        } else {
            userPermissions = userPermissionRepository.findAll();
        }

        return userPermissions.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    /**
     * ID로 사용자 권한 조회
     */
    public UserPermissionDto getUserPermissionById(UUID id) {
        return userPermissionRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    /**
     * 사용자 권한 생성
     */
    @Transactional
    public UserPermissionDto createUserPermission(UserPermissionDto userPermissionDto) {
        if (userPermissionDto.getId() == null) {
            userPermissionDto.setId(UUID.randomUUID());
        }

        UserPermission userPermission = convertToEntity(userPermissionDto);
        UserPermission savedUserPermission = userPermissionRepository.save(userPermission);
        return convertToDto(savedUserPermission);
    }

    /**
     * 사용자 권한 수정
     */
    @Transactional
    public UserPermissionDto updateUserPermission(UUID id, UserPermissionDto userPermissionDto) {
        return userPermissionRepository.findById(id)
                .map(userPermission -> {
                    updateUserPermissionFromDto(userPermission, userPermissionDto);
                    UserPermission updatedUserPermission = userPermissionRepository.save(userPermission);
                    return convertToDto(updatedUserPermission);
                })
                .orElse(null);
    }

    /**
     * 사용자 권한 삭제
     */
    @Transactional
    public boolean deleteUserPermission(UUID id) {
        if (userPermissionRepository.existsById(id)) {
            userPermissionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Entity를 DTO로 변환
     */
    private UserPermissionDto convertToDto(UserPermission userPermission) {
        UserPermissionDto dto = new UserPermissionDto();
        dto.setId(userPermission.getId());
        dto.setUserId(userPermission.getUserId());
        dto.setPermissionsJson(userPermission.getPermissionsJson());
        dto.setCreatedAt(userPermission.getCreatedAt());
        dto.setCreatedBy(userPermission.getCreatedBy());
        dto.setUpdatedAt(userPermission.getUpdatedAt());
        dto.setUpdatedBy(userPermission.getUpdatedBy());
        return dto;
    }

    /**
     * DTO를 Entity로 변환
     */
    private UserPermission convertToEntity(UserPermissionDto dto) {
        UserPermission userPermission = new UserPermission();
        userPermission.setId(dto.getId());
        userPermission.setUserId(dto.getUserId());
        userPermission.setPermissionsJson(dto.getPermissionsJson());
        userPermission.setCreatedBy(dto.getCreatedBy());
        userPermission.setUpdatedBy(dto.getUpdatedBy());
        return userPermission;
    }

    /**
     * DTO 데이터로 Entity 업데이트
     */
    private void updateUserPermissionFromDto(UserPermission userPermission, UserPermissionDto dto) {
        userPermission.setUserId(dto.getUserId());
        userPermission.setPermissionsJson(dto.getPermissionsJson());
        userPermission.setUpdatedBy(dto.getUpdatedBy());
    }
}