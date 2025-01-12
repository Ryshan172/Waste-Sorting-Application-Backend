package com.enviro.assessment.grad001.ryshanramlall.controller;

import com.enviro.assessment.grad001.ryshanramlall.model.DisposalGuideline;
import com.enviro.assessment.grad001.ryshanramlall.service.DisposalGuidelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disposal-guidelines")
public class DisposalGuidelineController {

    @Autowired
    private DisposalGuidelineService service;

    @GetMapping
    public List<DisposalGuideline> getAllGuidelines() {
        return service.getAllGuidelines();
    }

    @PostMapping
    public ResponseEntity<DisposalGuideline> createGuideline(@RequestBody DisposalGuideline guideline) {
        return ResponseEntity.ok(service.saveGuideline(guideline));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisposalGuideline> updateGuideline(
            @PathVariable Long id, @RequestBody DisposalGuideline updatedGuideline) {
        try {
            DisposalGuideline updated = service.updateGuideline(id, updatedGuideline);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);  // Invalid data, return 400
        }
    }


    // Delete a disposal guideline by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuideline(@PathVariable Long id) {
        if (service.getGuidelineById(id).isPresent()) {
            // If the guideline exists, delete it
            service.deleteGuideline(id);
            return ResponseEntity.noContent().build(); // Return 204 No Content on successful deletion
        }
        return ResponseEntity.notFound().build(); // Return 404 if not found
    }

}
