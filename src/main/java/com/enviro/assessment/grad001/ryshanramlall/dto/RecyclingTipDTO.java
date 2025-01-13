package com.enviro.assessment.grad001.ryshanramlall.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RecyclingTipDTO {

    // The tip must not be null or blank, and it should have a minimum length
    @NotBlank(message = "Tip cannot be blank.")
    @Size(min = 5, max = 255, message = "Tip must be between 5 and 255 characters.")
    private String tip;

    // The wasteCategoryId must not be null and must be a positive number
    @NotNull(message = "Waste Category ID cannot be null.")
    @Min(value = 1, message = "Waste Category ID must be a positive number.")
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