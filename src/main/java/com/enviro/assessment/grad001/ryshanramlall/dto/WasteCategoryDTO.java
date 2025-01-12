package com.enviro.assessment.grad001.ryshanramlall.dto;

public class WasteCategoryDTO {
    // Encapsulates data for entries in Waste Category table
    // Captures and validates data

    private String name;
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