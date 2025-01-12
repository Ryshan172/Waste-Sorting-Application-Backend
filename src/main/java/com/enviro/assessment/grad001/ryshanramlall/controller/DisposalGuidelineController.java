package com.enviro.assessment.grad001.ryshanramlall.controller;

import com.enviro.assessment.grad001.ryshanramlall.dto.DisposalGuidelineDTO;
import com.enviro.assessment.grad001.ryshanramlall.dto.DisposalGuidelineResponseDTO;
import com.enviro.assessment.grad001.ryshanramlall.service.DisposalGuidelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/disposal-guidelines")
public class DisposalGuidelineController {

    @Autowired
    private DisposalGuidelineService service;

    // Retrieve all disposal guidelines as Response DTOs
    @GetMapping
    public List<DisposalGuidelineResponseDTO> getAllGuidelines() {
        return service.getAllGuidelines();
    }

    // Create a new disposal guideline using a DTO
    @PostMapping
    public ResponseEntity<DisposalGuidelineResponseDTO> createGuideline(@RequestBody DisposalGuidelineDTO guidelineDTO) {
        try {
            DisposalGuidelineResponseDTO responseDTO = service.saveGuideline(guidelineDTO);
            return ResponseEntity.ok(responseDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Return 400 for invalid input
        }
    }

    // Update an existing disposal guideline by ID
    @PutMapping("/{id}")
    public ResponseEntity<DisposalGuidelineResponseDTO> updateGuideline(
            @PathVariable Long id, @RequestBody DisposalGuidelineDTO updatedGuidelineDTO) {
        try {
            Optional<DisposalGuidelineResponseDTO> updated = service.updateGuideline(id, updatedGuidelineDTO);
            return updated.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build()); // Return 404 if ID not found
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Return 400 for invalid input
        }
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