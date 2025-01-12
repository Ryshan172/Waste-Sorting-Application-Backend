package com.enviro.assessment.grad001.ryshanramlall.dto;

public class RecyclingTipDTO {
    private String tip;
    private Long wasteCategoryId;

    // Getters and Setters
    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Long getWasteCategoryId() {
        return wasteCategoryId;
    }

    public void setWasteCategoryId(Long wasteCategoryId) {
        this.wasteCategoryId = wasteCategoryId;
    }
}