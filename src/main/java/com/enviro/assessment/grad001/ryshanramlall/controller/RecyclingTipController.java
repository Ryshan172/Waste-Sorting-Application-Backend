package com.enviro.assessment.grad001.ryshanramlall.controller;

import com.enviro.assessment.grad001.ryshanramlall.model.RecyclingTip;
import com.enviro.assessment.grad001.ryshanramlall.service.RecyclingTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recycling-tips")
public class RecyclingTipController {

    @Autowired
    private RecyclingTipService service;

    @GetMapping
    public List<RecyclingTip> getAllTips() {
        return service.getAllTips();
    }

    @PostMapping
    public ResponseEntity<RecyclingTip> createTip(@RequestBody RecyclingTip tip) {
        return ResponseEntity.ok(service.saveTip(tip));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecyclingTip> updateTip(@PathVariable Long id, @RequestBody RecyclingTip tip) {
        return service.getTipById(id).map(existingTip -> {
            existingTip.setTip(tip.getTip());
            // Do not update the waste category here
            RecyclingTip updatedTip = service.saveTip(existingTip);
            return ResponseEntity.ok(updatedTip);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTip(@PathVariable Long id) {
        if (service.getTipById(id).isPresent()) {
            // If the tip exists, delete it
            service.deleteTip(id);
            return ResponseEntity.noContent().build(); // Return 204 No Content on successful deletion
        }
        return ResponseEntity.notFound().build(); // Return 404 if not found
    }


}
