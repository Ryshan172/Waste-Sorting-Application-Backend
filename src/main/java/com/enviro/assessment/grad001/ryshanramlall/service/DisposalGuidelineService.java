package com.enviro.assessment.grad001.ryshanramlall.service;

import com.enviro.assessment.grad001.ryshanramlall.dto.DisposalGuidelineDTO;
import com.enviro.assessment.grad001.ryshanramlall.dto.DisposalGuidelineResponseDTO;
import com.enviro.assessment.grad001.ryshanramlall.model.DisposalGuideline;
import com.enviro.assessment.grad001.ryshanramlall.model.WasteCategory;
import com.enviro.assessment.grad001.ryshanramlall.repository.DisposalGuidelineRepository;
import com.enviro.assessment.grad001.ryshanramlall.repository.WasteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DisposalGuidelineService {

    @Autowired
    private DisposalGuidelineRepository repository;

    @Autowired
    private WasteCategoryRepository wasteCategoryRepository;

    // Retrieve all disposal guidelines as a list of Response DTOs
    public List<DisposalGuidelineResponseDTO> getAllGuidelines() {
        List<DisposalGuideline> guidelines = repository.findAll();
        return guidelines.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Save a new disposal guideline from Request DTO
    public DisposalGuidelineResponseDTO saveGuideline(DisposalGuidelineDTO guidelineDTO) {
        // Find the associated waste category by its name
        WasteCategory wasteCategory = wasteCategoryRepository.findById(guidelineDTO.getWasteCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid waste category ID"));

        // Convert DTO to entity and associate with waste category
        DisposalGuideline guideline = toEntity(guidelineDTO);
        guideline.setWasteCategory(wasteCategory);

        // Save and convert to response DTO
        DisposalGuideline savedGuideline = repository.save(guideline);
        return toResponseDTO(savedGuideline);
    }

    // Update a disposal guideline by ID
    public Optional<DisposalGuidelineResponseDTO> updateGuideline(Long id, DisposalGuidelineDTO guidelineDTO) {
        return repository.findById(id).map(existingGuideline -> {
            existingGuideline.setGuideline(guidelineDTO.getGuideline());
            DisposalGuideline updatedGuideline = repository.save(existingGuideline);
            return toResponseDTO(updatedGuideline);
        });
    }

    // Retrieve a single disposal guideline by ID as a Response DTO
    public Optional<DisposalGuidelineResponseDTO> getGuidelineById(Long id) {
        return repository.findById(id)
                .map(this::toResponseDTO);
    }

    // Delete a disposal guideline by ID
    public void deleteGuideline(Long id) {
        repository.deleteById(id);
    }

    // Convert DisposalGuideline entity to Response DTO
    private DisposalGuidelineResponseDTO toResponseDTO(DisposalGuideline guideline) {
        DisposalGuidelineResponseDTO responseDTO = new DisposalGuidelineResponseDTO();
        responseDTO.setId(guideline.getDisposalGuidelineId()); // Updated to use the correct getter
        responseDTO.setGuideline(guideline.getGuideline());
        responseDTO.setWasteCategoryId(guideline.getWasteCategory().getId()); // Assumes WasteCategory has a getId() method
        responseDTO.setWasteCategoryName(guideline.getWasteCategory().getName());
        return responseDTO;
    }

    // Convert Request DTO to DisposalGuideline entity
    private DisposalGuideline toEntity(DisposalGuidelineDTO guidelineDTO) {
        DisposalGuideline guideline = new DisposalGuideline();
        guideline.setGuideline(guidelineDTO.getGuideline());
        return guideline;
    }
}