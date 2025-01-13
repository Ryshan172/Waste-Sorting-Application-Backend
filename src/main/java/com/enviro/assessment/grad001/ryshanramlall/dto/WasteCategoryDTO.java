package com.enviro.assessment.grad001.ryshanramlall.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class WasteCategoryDTO {
    // The name must not be null or blank, and its length must be between 2 and 100 characters
    @NotBlank(message = "Name cannot be blank.")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters.")
    private String name;

    // The description must not be null or blank, and its length must be between 10 and 500 characters
    @NotBlank(message = "Description cannot be blank.")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters.")
    private String description;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}