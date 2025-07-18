package com.company.dms.user.repository;

import com.company.dms.user.entity.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermission, UUID> {

    List<UserPermission> findByUserId(UUID userId);
    List<UserPermission> findByCreatedBy(UUID createdBy);
    List<UserPermission> findByUserIdAndCreatedBy(UUID userId, UUID createdBy);
}