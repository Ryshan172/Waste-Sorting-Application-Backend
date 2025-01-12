package com.enviro.assessment.grad001.ryshanramlall.service;

import com.enviro.assessment.grad001.ryshanramlall.model.DisposalGuideline;
import com.enviro.assessment.grad001.ryshanramlall.model.WasteCategory;
import com.enviro.assessment.grad001.ryshanramlall.repository.DisposalGuidelineRepository;
import com.enviro.assessment.grad001.ryshanramlall.repository.WasteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}