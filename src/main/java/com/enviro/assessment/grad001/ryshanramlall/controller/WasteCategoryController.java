package com.enviro.assessment.grad001.ryshanramlall.controller;

import com.enviro.assessment.grad001.ryshanramlall.model.WasteCategory;
import com.enviro.assessment.grad001.ryshanramlall.service.WasteCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/waste-categories")
public class WasteCategoryController {

    @Autowired
    private WasteCategoryService service;

    @GetMapping
    public List<WasteCategory> getAllCategories() {
        return service.getAllCategories();
    }

    @PostMapping
    public ResponseEntity<WasteCategory> createCategory(@RequestBody WasteCategory category) {
        return ResponseEntity.ok(service.saveCategory(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WasteCategory> updateCategory(@PathVariable Long id, @RequestBody WasteCategory category) {
        return service.getCategoryById(id).map(existingCategory -> {
            existingCategory.setName(category.getName());
            existingCategory.setDescription(category.getDescription());
            WasteCategory updatedCategory = service.saveCategory(existingCategory);
            return ResponseEntity.ok(updatedCategory);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (service.getCategoryById(id).isPresent()) {
            // If the category exists, delete it
            service.deleteCategory(id);
            return ResponseEntity.noContent().build(); // Return 204 No Content on successful deletion
        }
        return ResponseEntity.notFound().build(); // Return 404 if not found
    }

}