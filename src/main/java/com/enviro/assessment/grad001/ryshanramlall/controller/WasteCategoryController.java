package com.enviro.assessment.grad001.ryshanramlall.controller;

import com.enviro.assessment.grad001.ryshanramlall.dto.WasteCategoryDTO;
import com.enviro.assessment.grad001.ryshanramlall.dto.WasteCategoryResponseDTO;
import com.enviro.assessment.grad001.ryshanramlall.service.WasteCategoryService;
import jakarta.validation.Valid;
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

    // Retrieve a waste category by ID
    @GetMapping("/{id}")
    public ResponseEntity<WasteCategoryResponseDTO> getCategoryById(@PathVariable Long id) {
        // The service handles retrieving the category by ID and returns a response DTO
        return service.getCategoryById(id)
                .map(ResponseEntity::ok)  // Return 200 OK with the found category
                .orElseGet(() -> ResponseEntity.notFound().build());  // Return 404 if not found
    }

    // Create a new waste category
    @PostMapping
    public ResponseEntity<WasteCategoryResponseDTO> createCategory(@Valid @RequestBody WasteCategoryDTO categoryDTO) {
        // Accepts a validated DTO as input and returns a response DTO
        WasteCategoryResponseDTO createdCategory = service.saveCategory(categoryDTO);
        return ResponseEntity.ok(createdCategory);
    }

    // Update an existing waste category
    @PutMapping("/{id}")
    public ResponseEntity<WasteCategoryResponseDTO> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody WasteCategoryDTO categoryDTO) {
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