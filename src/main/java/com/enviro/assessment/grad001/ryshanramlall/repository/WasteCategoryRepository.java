package com.enviro.assessment.grad001.ryshanramlall.repository;

import com.enviro.assessment.grad001.ryshanramlall.model.WasteCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WasteCategoryRepository extends JpaRepository<WasteCategory, Long> {
    /*
        Repository interface for managing WasteCategory entities.
        Extends JpaRepository to provide methods for database operations (save, update, delete).
        Operations for database table Waste_Categories
     */
}