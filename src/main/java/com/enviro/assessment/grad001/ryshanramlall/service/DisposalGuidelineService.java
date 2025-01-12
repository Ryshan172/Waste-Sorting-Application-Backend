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

    /**
     * Retrieves all disposal guidelines and returns them as a list of response DTOs.
     * @return a list of DisposalGuidelineResponseDTO objects representing all disposal guidelines.
     */
    public List<DisposalGuidelineResponseDTO> getAllGuidelines() {
        List<DisposalGuideline> guidelines = repository.findAll();
        return guidelines.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Adds a new disposal guideline using the provided DisposalGuidelineDTO
     * All disposal guidelines are associated with a Waste Category.
     * @param guidelineDTO the DisposalGuidelineDTO object containing the data to be saved.
     * @return a DisposalGuidelineResponseDTO object representing the saved disposal guideline.
     */
    public DisposalGuidelineResponseDTO saveGuideline(DisposalGuidelineDTO guidelineDTO) {
        // Find associated waste category by its ID
        WasteCategory wasteCategory = wasteCategoryRepository.findById(guidelineDTO.getWasteCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid waste category ID"));

        // Convert DTO to entity and link with waste category
        DisposalGuideline guideline = toEntity(guidelineDTO);
        guideline.setWasteCategory(wasteCategory);

        // Save and convert to response DTO
        DisposalGuideline savedGuideline = repository.save(guideline);
        return toResponseDTO(savedGuideline);
    }

    /**
     * Updates existing disposal guideline ID.
     * Uses the data from provided DisposalGuidelineDTO.
     * @param id the ID of the disposal guideline to update.
     * @param guidelineDTO the DisposalGuidelineDTO containing the updated data.
     * @return Optional containing the updated DisposalGuidelineResponseDTO if updated,
     *         or empty Optional if the disposal guideline doesn't exist.
     */
    public Optional<DisposalGuidelineResponseDTO> updateGuideline(Long id, DisposalGuidelineDTO guidelineDTO) {
        return repository.findById(id).map(existingGuideline -> {
            // Update existing disposal guideline with the new data from guidelineDTO
            existingGuideline.setGuideline(guidelineDTO.getGuideline());
            DisposalGuideline updatedGuideline = repository.save(existingGuideline);
            return toResponseDTO(updatedGuideline);
        });
    }

    /**
     * Gets disposal guideline by ID and returns it as a response DTO.
     *
     * @param id the ID of the disposal guideline to retrieve.
     * @return an Optional containing DisposalGuidelineResponseDTO if found, or empty Optional if not found.
     */
    public Optional<DisposalGuidelineResponseDTO> getGuidelineById(Long id) {
        return repository.findById(id)
                .map(this::toResponseDTO);
    }

    /**
     * Deletes a disposal guideline by its ID
     * @param id the ID of the disposal guideline to delete.
     */
    public void deleteGuideline(Long id) {
        repository.deleteById(id);
    }

    /**
     * Converts a DisposalGuideline entity to a DisposalGuidelineResponseDTO.
     * @param guideline the DisposalGuideline entity to convert.
     * @return a DisposalGuidelineResponseDTO representing the entity.
     */
    private DisposalGuidelineResponseDTO toResponseDTO(DisposalGuideline guideline) {
        DisposalGuidelineResponseDTO responseDTO = new DisposalGuidelineResponseDTO();
        responseDTO.setId(guideline.getDisposalGuidelineId());
        responseDTO.setGuideline(guideline.getGuideline());
        responseDTO.setWasteCategoryId(guideline.getWasteCategory().getId());
        responseDTO.setWasteCategoryName(guideline.getWasteCategory().getName());
        return responseDTO;
    }

    /**
     * Converts a DisposalGuidelineDTO to a DisposalGuideline entity.
     * @param guidelineDTO the DisposalGuidelineDTO to convert.
     * @return a DisposalGuideline entity based on the provided DTO.
     */
    private DisposalGuideline toEntity(DisposalGuidelineDTO guidelineDTO) {
        DisposalGuideline guideline = new DisposalGuideline();
        guideline.setGuideline(guidelineDTO.getGuideline());
        return guideline;
    }
}