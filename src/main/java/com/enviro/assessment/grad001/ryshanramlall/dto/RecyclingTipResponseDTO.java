package com.enviro.assessment.grad001.ryshanramlall.dto;

public class RecyclingTipResponseDTO {
    private Long id;
    private String tip;
    private Long wasteCategoryId;
    private String wasteCategoryName;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getWasteCategoryName() {
        return wasteCategoryName;
    }

    public void setWasteCategoryName(String wasteCategoryName) {
        this.wasteCategoryName = wasteCategoryName;
    }
}

