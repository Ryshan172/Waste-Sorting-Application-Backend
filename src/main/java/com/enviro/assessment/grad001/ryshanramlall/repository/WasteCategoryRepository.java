package com.enviro.assessment.grad001.ryshanramlall.repository;

import com.enviro.assessment.grad001.ryshanramlall.model.WasteCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WasteCategoryRepository extends JpaRepository<WasteCategory, Long> {
    // Custom queries can be added here if needed
}