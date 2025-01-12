package com.enviro.assessment.grad001.ryshanramlall.controller;

import com.enviro.assessment.grad001.ryshanramlall.dto.RecyclingTipDTO;
import com.enviro.assessment.grad001.ryshanramlall.dto.RecyclingTipResponseDTO;
import com.enviro.assessment.grad001.ryshanramlall.model.RecyclingTip;
import com.enviro.assessment.grad001.ryshanramlall.service.RecyclingTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recycling-tips")
public class RecyclingTipController {

    @Autowired
    private RecyclingTipService service;

    @GetMapping
    public ResponseEntity<List<RecyclingTipResponseDTO>> getAllTips() {
        List<RecyclingTipResponseDTO> response = service.getAllTips();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<RecyclingTipResponseDTO> createTip(@RequestBody RecyclingTipDTO tipDTO) {
        RecyclingTipResponseDTO response = service.saveTip(tipDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecyclingTipResponseDTO> updateTip(@PathVariable Long id, @RequestBody RecyclingTipDTO tipDTO) {
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
