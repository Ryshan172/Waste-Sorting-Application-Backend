package com.enviro.assessment.grad001.ryshanramlall.service;

import com.enviro.assessment.grad001.ryshanramlall.dto.WasteCategoryDTO;
import com.enviro.assessment.grad001.ryshanramlall.dto.WasteCategoryResponseDTO;
import com.enviro.assessment.grad001.ryshanramlall.model.WasteCategory;
import com.enviro.assessment.grad001.ryshanramlall.repository.WasteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WasteCategoryService {

    @Autowired
    private WasteCategoryRepository repository;

    // Retrieve all waste categories as a list of Response DTOs
    public List<WasteCategoryResponseDTO> getAllCategories() {
        List<WasteCategory> categories = repository.findAll();
        return categories.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Save a new waste category from Request DTO
    public WasteCategoryResponseDTO saveCategory(WasteCategoryDTO categoryDTO) {
        WasteCategory category = toEntity(categoryDTO);
        WasteCategory savedCategory = repository.save(category);
        return toResponseDTO(savedCategory);
    }

    // Retrieve a single waste category by ID as a Response DTO
    public Optional<WasteCategoryResponseDTO> getCategoryById(Long id) {
        return repository.findById(id)
                .map(this::toResponseDTO);
    }

    // Update a waste category by ID
    public Optional<WasteCategoryResponseDTO> updateCategory(Long id, WasteCategoryDTO categoryDTO) {
        return repository.findById(id).map(existingCategory -> {
            existingCategory.setName(categoryDTO.getName());
            existingCategory.setDescription(categoryDTO.getDescription());
            WasteCategory updatedCategory = repository.save(existingCategory);
            return toResponseDTO(updatedCategory);
        });
    }

    // Delete a waste category by ID
    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }

    // Convert WasteCategory entity to Response DTO
    private WasteCategoryResponseDTO toResponseDTO(WasteCategory category) {
        WasteCategoryResponseDTO responseDTO = new WasteCategoryResponseDTO();
        responseDTO.setId(category.getId());
        responseDTO.setName(category.getName());
        responseDTO.setDescription(category.getDescription());
        return responseDTO;
    }

    // Convert Request DTO to WasteCategory entity
    private WasteCategory toEntity(WasteCategoryDTO categoryDTO) {
        WasteCategory category = new WasteCategory();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return category;
    }
}