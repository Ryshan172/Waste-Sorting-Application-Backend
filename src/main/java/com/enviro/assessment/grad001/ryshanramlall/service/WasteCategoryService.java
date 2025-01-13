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

    /**
     * Retrieves all waste categories and returns as a list of response DTOs.
     * @return a list of WasteCategoryResponseDTO objects representing all waste categories.
     */
    public List<WasteCategoryResponseDTO> getAllCategories() {
        List<WasteCategory> categories = repository.findAll();
        return categories.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }


    /**
     * Adds a new waste category by converting the provided Request DTO to an entity,
     * Adds to repository, and returns the saved category as a response.
     * @param categoryDTO the WasteCategoryDTO object containing the data to be saved.
     * @return a WasteCategoryResponseDTO object representing the saved waste category.
     */
    public WasteCategoryResponseDTO saveCategory(WasteCategoryDTO categoryDTO) {
        WasteCategory category = toEntity(categoryDTO);
        WasteCategory savedCategory = repository.save(category);
        return toResponseDTO(savedCategory);
    }

    /**
     * Retrieves a waste category by ID and returns it as a response DTO.
     * @param id the ID of the waste category to retrieve.
     * @return Optional with WasteCategoryResponseDTO object if found,
     *         or empty Optional if no category found
     */
    public Optional<WasteCategoryResponseDTO> getCategoryById(Long id) {
        // Optional is a container object
        return repository.findById(id)
                .map(this::toResponseDTO);
    }

    /**
     * Updates existing waste category by ID and field data
     * @param id of the waste category to update.
     * @param categoryDTO the WasteCategoryDTO containing the updated data e.g. name
     * @return an Optional (Object) containing updated WasteCategoryResponseDTO object if updated or
     *         empty Optional if the category was not found.
     */
    public Optional<WasteCategoryResponseDTO> updateCategory(Long id, WasteCategoryDTO categoryDTO) {
        return repository.findById(id).map(existingCategory -> {
            existingCategory.setName(categoryDTO.getName());
            existingCategory.setDescription(categoryDTO.getDescription());
            WasteCategory updatedCategory = repository.save(existingCategory);
            return toResponseDTO(updatedCategory);
        });
    }

    /**
     * Deletes waste category by its ID.
     * @param id the ID of the waste category to delete.
     */
    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }

    /**
     * Converts a WasteCategory entity to a WasteCategoryResponseDTO.
     * @param category the WasteCategory entity to convert.
     * @return a WasteCategoryResponseDTO representing the entity.
     */
    private WasteCategoryResponseDTO toResponseDTO(WasteCategory category) {
        WasteCategoryResponseDTO responseDTO = new WasteCategoryResponseDTO();
        responseDTO.setId(category.getId());
        responseDTO.setName(category.getName());
        responseDTO.setDescription(category.getDescription());
        return responseDTO;
    }

    /**
     * Converts a WasteCategoryDTO (Request DTO) to a WasteCategory entity.
     * @param categoryDTO the WasteCategoryDTO object to convert.
     * @return a WasteCategory entity.
     */
    private WasteCategory toEntity(WasteCategoryDTO categoryDTO) {
        WasteCategory category = new WasteCategory();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return category;
    }
}