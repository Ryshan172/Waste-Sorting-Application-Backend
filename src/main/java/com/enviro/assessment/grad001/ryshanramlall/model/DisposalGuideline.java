package com.enviro.assessment.grad001.ryshanramlall.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "disposal_guidelines")
public class DisposalGuideline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long disposalGuidelineId;

    @NotEmpty(message = "Guideline must not be empty")
    @Column(length = 1000) // Optional, to enforce a max length for guidelines
    private String guideline;

    // All entries on Disposal_Guidelines table are linked to the Waste_Category table.
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "waste_category_id", nullable = false)
    private WasteCategory wasteCategory;

    // Getters and Setters
    public Long getDisposalGuidelineId() {
        return disposalGuidelineId;
    }

    public void setDisposalGuidelineId(Long disposalGuidelineId) {
        this.disposalGuidelineId = disposalGuidelineId;
    }

    public String getGuideline() {
        return guideline;
    }

    public void setGuideline(String guideline) {
        this.guideline = guideline;
    }

    public WasteCategory getWasteCategory() {
        return wasteCategory;
    }

    public void setWasteCategory(WasteCategory wasteCategory) {
        this.wasteCategory = wasteCategory;
    }
}