package com.company.dms.component;

import com.company.dms.component.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComponentRepository extends JpaRepository<Component, String> {
    List<Component> findByCategory(String category);
}