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
}
