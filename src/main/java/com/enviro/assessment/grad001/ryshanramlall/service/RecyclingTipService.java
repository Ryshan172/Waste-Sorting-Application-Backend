package com.enviro.assessment.grad001.ryshanramlall.service;

import com.enviro.assessment.grad001.ryshanramlall.dto.RecyclingTipDTO;
import com.enviro.assessment.grad001.ryshanramlall.dto.RecyclingTipResponseDTO;
import com.enviro.assessment.grad001.ryshanramlall.model.RecyclingTip;
import com.enviro.assessment.grad001.ryshanramlall.model.WasteCategory;
import com.enviro.assessment.grad001.ryshanramlall.repository.RecyclingTipRepository;
import com.enviro.assessment.grad001.ryshanramlall.repository.WasteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecyclingTipService {

    @Autowired
    private RecyclingTipRepository repository;

    @Autowired
    private WasteCategoryRepository wasteCategoryRepository;

    /**
     * Gets all recycling tips and returns them as a list of response DTOs.
     * @return list of RecyclingTipResponseDTO objects representing all recycling tips.
     */
    public List<RecyclingTipResponseDTO> getAllTips() {
        List<RecyclingTip> tips = repository.findAll();
        return tips.stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    /**
     * Adds a new recycling tip using the provided RecyclingTipDTO
     * @param tipDTO the RecyclingTipDTO object containing the data to be saved.
     * @return a RecyclingTipResponseDTO object representing the saved recycling tip.
     * @throws IllegalArgumentException if provided waste category ID is invalid.
     */
    public RecyclingTipResponseDTO saveTip(RecyclingTipDTO tipDTO) {
        Long wasteCategoryId = tipDTO.getWasteCategoryId();
        WasteCategory wasteCategory = wasteCategoryRepository.findById(wasteCategoryId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid waste category ID"));

        // Create a new RecyclingTip entity
        RecyclingTip tip = new RecyclingTip();
        tip.setTip(tipDTO.getTip());
        tip.setWasteCategory(wasteCategory);

        // Save the RecyclingTip and return the corresponding response DTO
        RecyclingTip savedTip = repository.save(tip);
        return toResponseDTO(savedTip);
    }

    /**
     * Gets a recycling tip by its ID.
     * @param id the ID of the recycling tip to retrieve.
     * @return an Optional containing the RecyclingTip entity if found, or an empty Optional if not found.
     */
    public Optional<RecyclingTip> getTipById(Long id) {
        return repository.findById(id);
    }

    /**
     * Updates recycling tip by its ID with the data from the provided RecyclingTipDTO.
     * @param id the ID of the recycling tip to update.
     * @param tipDTO the RecyclingTipDTO containing the updated data.
     * @return an Optional containing the updated RecyclingTipResponseDTO or empty Optional
     */
    public Optional<RecyclingTipResponseDTO> updateTip(Long id, RecyclingTipDTO tipDTO) {
        return repository.findById(id).map(existingTip -> {
            // Update the existing recycling tip with the new data from tipDTO
            existingTip.setTip(tipDTO.getTip());

            // Since waste category shouldn't change, we don't modify it

            // Save the updated recycling tip
            RecyclingTip updatedTip = repository.save(existingTip);

            // Return the updated tip as a response DTO
            return toResponseDTO(updatedTip);
        });
    }

    /**
     * Converts a RecyclingTip entity to a RecyclingTipResponseDTO.
     * @param tip the RecyclingTip entity to convert.
     * @return a RecyclingTipResponseDTO representing the entity.
     */
    private RecyclingTipResponseDTO toResponseDTO(RecyclingTip tip) {
        RecyclingTipResponseDTO responseDTO = new RecyclingTipResponseDTO();
        responseDTO.setId(tip.getRecyclingTipId());
        responseDTO.setTip(tip.getTip());
        responseDTO.setWasteCategoryId(tip.getWasteCategory().getId());
        responseDTO.setWasteCategoryName(tip.getWasteCategory().getName());
        return responseDTO;
    }

    /**
     * Deletes a recycling tip by its ID.
     * @param id the ID of the recycling tip to delete.
     */
    public void deleteTip(Long id) {
        repository.deleteById(id);
    }
}