package com.company.ams.dataitem.repository;

import com.company.ams.dataitem.entity.DataItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DataItemRepository extends JpaRepository<DataItem, UUID> {

    List<DataItem> findByGroupId(UUID groupId);
    List<DataItem> findByCodeContainingIgnoreCase(String code);
    List<DataItem> findByNameContainingIgnoreCase(String name);
    List<DataItem> findByGroupIdAndCodeContainingIgnoreCase(UUID groupId, String code);
    List<DataItem> findByGroupIdAndNameContainingIgnoreCase(UUID groupId, String name);
    List<DataItem> findByCodeContainingIgnoreCaseAndNameContainingIgnoreCase(String code, String name);
    List<DataItem> findByGroupIdAndCodeContainingIgnoreCaseAndNameContainingIgnoreCase(UUID groupId, String code, String name);
}