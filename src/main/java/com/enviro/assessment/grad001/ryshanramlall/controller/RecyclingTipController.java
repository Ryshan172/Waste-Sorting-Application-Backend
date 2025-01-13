package com.enviro.assessment.grad001.ryshanramlall.controller;

import com.enviro.assessment.grad001.ryshanramlall.dto.DisposalGuidelineResponseDTO;
import com.enviro.assessment.grad001.ryshanramlall.dto.RecyclingTipDTO;
import com.enviro.assessment.grad001.ryshanramlall.dto.RecyclingTipResponseDTO;
import com.enviro.assessment.grad001.ryshanramlall.model.RecyclingTip;
import com.enviro.assessment.grad001.ryshanramlall.service.RecyclingTipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recycling-tips")
public class RecyclingTipController {
    /*
     A RESTful controller that provides endpoints for managing Recycling Tips.
     Interacts with RecyclingTipService.
     */

    @Autowired
    private RecyclingTipService service;

    @GetMapping
    public ResponseEntity<List<RecyclingTipResponseDTO>> getAllTips() {
        List<RecyclingTipResponseDTO> response = service.getAllTips();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecyclingTipResponseDTO> getTipById(@PathVariable Long id) {
        // Call the service method to retrieve the RecyclingTipResponseDTO by ID.
        RecyclingTipResponseDTO responseDTO = service.getResponseDTOById(id);

        // If the RecyclingTipResponseDTO is found, return it with a 200 OK status.
        if (responseDTO != null) {
            return ResponseEntity.ok(responseDTO);
        }

        // If the RecyclingTipResponseDTO is not found, return a 404 Not Found status.
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<RecyclingTipResponseDTO> createTip(@Valid @RequestBody RecyclingTipDTO tipDTO) {
        // Accepts a validated DTO as input and returns a response DTO
        RecyclingTipResponseDTO response = service.saveTip(tipDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecyclingTipResponseDTO> updateTip(
            @PathVariable Long id,
            @Valid @RequestBody RecyclingTipDTO tipDTO) {
        // The service handles updating the entity and returns a response DTO
        return service.updateTip(id, tipDTO)
                .map(ResponseEntity::ok) // Return 200 OK with updated tip
                .orElseGet(() -> ResponseEntity.notFound().build()); // Return 404 if not found
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTip(@PathVariable Long id) {
        Optional<RecyclingTip> recyclingTip = service.getTipById(id);

        if (recyclingTip.isPresent()) {
            service.deleteTip(id);
            return ResponseEntity.noContent().build();  // Return 204 No Content
        }

        return ResponseEntity.notFound().build();  // Return 404 if not found
    }
}