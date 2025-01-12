package com.enviro.assessment.grad001.ryshanramlall.dto;

public class DisposalGuidelineResponseDTO {
    // For data being retrieved from Disposal Guidelines Table

    private Long id;
    private String guideline;
    private Long wasteCategoryId;
    private String wasteCategoryName;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getWasteCategoryName() {
        return wasteCategoryName;
    }

    public void setWasteCategoryName(String wasteCategoryName) {
        this.wasteCategoryName = wasteCategoryName;
    }
}