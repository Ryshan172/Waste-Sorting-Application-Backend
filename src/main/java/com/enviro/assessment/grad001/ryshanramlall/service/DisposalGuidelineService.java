package com.enviro.assessment.grad001.ryshanramlall.service;

import com.enviro.assessment.grad001.ryshanramlall.model.DisposalGuideline;
import com.enviro.assessment.grad001.ryshanramlall.model.WasteCategory;
import com.enviro.assessment.grad001.ryshanramlall.repository.DisposalGuidelineRepository;
import com.enviro.assessment.grad001.ryshanramlall.repository.WasteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisposalGuidelineService {

    @Autowired
    private DisposalGuidelineRepository repository;

    @Autowired
    private WasteCategoryRepository wasteCategoryRepository;

    public List<DisposalGuideline> getAllGuidelines() {
        return repository.findAll();
    }

    public DisposalGuideline saveGuideline(DisposalGuideline guideline) {
        Long wasteCategoryId = guideline.getWasteCategory().getId();
        WasteCategory wasteCategory = wasteCategoryRepository.findById(wasteCategoryId).orElse(null);

        if (wasteCategory != null) {
            guideline.setWasteCategory(wasteCategory);
            return repository.save(guideline);
        }

        throw new IllegalArgumentException("Invalid waste category ID");
    }

    public DisposalGuideline updateGuideline(Long id, DisposalGuideline updatedGuideline) {
        // Fetch the existing guideline
        DisposalGuideline existingGuideline = repository.findById(id).orElse(null);

        if (existingGuideline != null) {
            // Only update the guideline text, not the waste category
            existingGuideline.setGuideline(updatedGuideline.getGuideline());

            // Save and return the updated guideline
            return repository.save(existingGuideline);
        }

        throw new IllegalArgumentException("Disposal guideline not found for the provided ID");
    }

    // Fetch a guideline by ID (used for checking existence)
    public Optional<DisposalGuideline> getGuidelineById(Long id) {
        return repository.findById(id);
    }

    // Delete a guideline by ID
    public void deleteGuideline(Long id) {
        repository.deleteById(id);
    }
}
