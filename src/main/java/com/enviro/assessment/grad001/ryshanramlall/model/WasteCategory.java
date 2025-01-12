package com.enviro.assessment.grad001.ryshanramlall.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
@Table(name = "waste_categories")
public class WasteCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name must not be empty")
    private String name;

    private String description;

    // Bidirectional relationships remain here and not be via Data Transfer Objects (DTOs)
    // Relationship with Disposal_Guidelines table
    @OneToMany(mappedBy = "wasteCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DisposalGuideline> disposalGuidelines;

    // Relationship with Recycling_Tips table
    // Operations performed on the WasteCategory (e.g., save, delete) will cascade to its associated RecyclingTip entities.
    @OneToMany(mappedBy = "wasteCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecyclingTip> recyclingTips;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<DisposalGuideline> getDisposalGuidelines() {
        return disposalGuidelines;
    }

    public void setDisposalGuidelines(List<DisposalGuideline> disposalGuidelines) {
        this.disposalGuidelines = disposalGuidelines;
    }

    public List<RecyclingTip> getRecyclingTips() {
        return recyclingTips;
    }

    public void setRecyclingTips(List<RecyclingTip> recyclingTips) {
        this.recyclingTips = recyclingTips;
    }
}