package com.enviro.assessment.grad001.ryshanramlall.controller;

import com.enviro.assessment.grad001.ryshanramlall.dto.DisposalGuidelineDTO;
import com.enviro.assessment.grad001.ryshanramlall.dto.DisposalGuidelineResponseDTO;
import com.enviro.assessment.grad001.ryshanramlall.service.DisposalGuidelineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/disposal-guidelines")
public class DisposalGuidelineController {
    /*
     A RESTful controller that provides endpoints for managing Disposal Guidelines.
     Interacts with DisposalGuidelineService.
     */

    @Autowired
    private DisposalGuidelineService service;

    // Retrieve all disposal guidelines as Response DTOs
    @GetMapping
    public List<DisposalGuidelineResponseDTO> getAllGuidelines() {
        return service.getAllGuidelines();
    }

    // Retrieve a disposal guideline by id
    @GetMapping("/{id}")
    public ResponseEntity<DisposalGuidelineResponseDTO> getGuidelineById(@PathVariable Long id) {
        Optional<DisposalGuidelineResponseDTO> guideline = service.getGuidelineById(id);
        return guideline
                .map(ResponseEntity::ok)  // Return 200 OK with the found guideline
                .orElseGet(() -> ResponseEntity.notFound().build());  // Return 404 Not Found if not found
    }

    // Create a new disposal guideline using a DTO with validation
    @PostMapping
    public ResponseEntity<DisposalGuidelineResponseDTO> createGuideline(@Valid @RequestBody DisposalGuidelineDTO guidelineDTO) {
        DisposalGuidelineResponseDTO responseDTO = service.saveGuideline(guidelineDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // Update an existing disposal guideline by ID with validation
    @PutMapping("/{id}")
    public ResponseEntity<DisposalGuidelineResponseDTO> updateGuideline(
            @PathVariable Long id, @Valid @RequestBody DisposalGuidelineDTO updatedGuidelineDTO) {
        Optional<DisposalGuidelineResponseDTO> updated = service.updateGuideline(id, updatedGuidelineDTO);
        return updated.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // Return 404 if ID not found
    }

    // Delete a disposal guideline by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuideline(@PathVariable Long id) {
        if (service.getGuidelineById(id).isPresent()) {
            service.deleteGuideline(id);
            return ResponseEntity.noContent().build(); // Return 204 No Content
        }
        return ResponseEntity.notFound().build(); // Return 404 if not found
    }
}