package com.enviro.assessment.grad001.ryshanramlall.service;

import com.enviro.assessment.grad001.ryshanramlall.model.RecyclingTip;
import com.enviro.assessment.grad001.ryshanramlall.model.WasteCategory;
import com.enviro.assessment.grad001.ryshanramlall.repository.RecyclingTipRepository;
import com.enviro.assessment.grad001.ryshanramlall.repository.WasteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecyclingTipService {

    @Autowired
    private RecyclingTipRepository repository;

    @Autowired
    private WasteCategoryRepository wasteCategoryRepository;

    public List<RecyclingTip> getAllTips() {
        return repository.findAll();
    }

    public RecyclingTip saveTip(RecyclingTip tip) {
        Long wasteCategoryId = tip.getWasteCategory().getId();
        WasteCategory wasteCategory = wasteCategoryRepository.findById(wasteCategoryId).orElse(null);

        if (wasteCategory != null) {
            tip.setWasteCategory(wasteCategory);
            return repository.save(tip);
        }

        throw new IllegalArgumentException("Invalid waste category ID");
    }

    public Optional<RecyclingTip> getTipById(Long id) {
        return repository.findById(id);
    }

    public void deleteTip(Long id) {
        repository.deleteById(id);
    }

}
