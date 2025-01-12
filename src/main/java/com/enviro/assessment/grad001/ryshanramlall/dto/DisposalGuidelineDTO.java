package com.enviro.assessment.grad001.ryshanramlall.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class DisposalGuidelineDTO {

    @NotEmpty(message = "Guideline must not be empty")
    private String guideline;

    @NotNull(message = "Waste category ID must not be null")
    private Long wasteCategoryId;

    // Getters and Setters
    public String getGuideline() {
        return guideline;
    }

    public void setGuideline(String guideline) {
        this.guideline = guideline;
    }

    public Long getWasteCategoryId() {
        return wasteCategoryId;
    }

    public void setWasteCategoryId(Long wasteCategoryId) {
        this.wasteCategoryId = wasteCategoryId;
    }
}