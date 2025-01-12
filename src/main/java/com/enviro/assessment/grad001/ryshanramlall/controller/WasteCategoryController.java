package com.enviro.assessment.grad001.ryshanramlall.controller;

import com.enviro.assessment.grad001.ryshanramlall.dto.WasteCategoryDTO;
import com.enviro.assessment.grad001.ryshanramlall.dto.WasteCategoryResponseDTO;
import com.enviro.assessment.grad001.ryshanramlall.service.WasteCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/waste-categories")
public class WasteCategoryController {
    /*
     A RESTful controller that provides endpoints for managing Waste Categories.
     Interacts with WasteCategoryService.
     */

    @Autowired
    private WasteCategoryService service;

    // Retrieve all waste categories
    @GetMapping
    public List<WasteCategoryResponseDTO> getAllCategories() {
        // The service returns a list of DTOs instead of entities
        return service.getAllCategories();
    }

    // Create a new waste category
    @PostMapping
    public ResponseEntity<WasteCategoryResponseDTO> createCategory(@RequestBody WasteCategoryDTO categoryDTO) {
        // Accepts a DTO as input and returns a response DTO
        WasteCategoryResponseDTO createdCategory = service.saveCategory(categoryDTO);
        return ResponseEntity.ok(createdCategory);
    }

    // Update an existing waste category
    @PutMapping("/{id}")
    public ResponseEntity<WasteCategoryResponseDTO> updateCategory(@PathVariable Long id, @RequestBody WasteCategoryDTO categoryDTO) {
        // The service handles updating the entity and returns a response DTO
        return service.updateCategory(id, categoryDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a waste category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (service.getCategoryById(id).isPresent()) {
            service.deleteCategory(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}