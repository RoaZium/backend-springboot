package com.company.dms.datagroup.repository;

import com.company.dms.datagroup.entity.DataGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DataGroupRepository extends JpaRepository<DataGroup, UUID> {

    List<DataGroup> findByCodeContainingIgnoreCase(String code);
    List<DataGroup> findByNameContainingIgnoreCase(String name);
    List<DataGroup> findByCodeContainingIgnoreCaseAndNameContainingIgnoreCase(String code, String name);
}