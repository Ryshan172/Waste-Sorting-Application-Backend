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

    // Method to fetch all recycling tips and return the response DTOs
    public List<RecyclingTipResponseDTO> getAllTips() {
        List<RecyclingTip> tips = repository.findAll();
        return tips.stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    // Method to save a new recycling tip from the RecyclingTipDTO
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

    // Method to fetch a recycling tip by ID and return an Optional entity
    public Optional<RecyclingTip> getTipById(Long id) {
        return repository.findById(id);
    }

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


    // Helper method to convert RecyclingTip entity to RecyclingTipResponseDTO
    private RecyclingTipResponseDTO toResponseDTO(RecyclingTip tip) {
        RecyclingTipResponseDTO responseDTO = new RecyclingTipResponseDTO();
        responseDTO.setId(tip.getRecyclingTipId());
        responseDTO.setTip(tip.getTip());
        responseDTO.setWasteCategoryId(tip.getWasteCategory().getId());
        responseDTO.setWasteCategoryName(tip.getWasteCategory().getName());
        return responseDTO;
    }

    // Method to delete a recycling tip
    public void deleteTip(Long id) {
        repository.deleteById(id);
    }
}