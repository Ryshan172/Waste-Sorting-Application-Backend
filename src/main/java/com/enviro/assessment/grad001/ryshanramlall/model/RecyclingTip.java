package com.enviro.assessment.grad001.ryshanramlall.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "recycling_tips")
public class RecyclingTip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recyclingTipId;

    @NotEmpty(message = "Tip must not be empty")
    private String tip;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "waste_category_id", nullable = false)
    private WasteCategory wasteCategory;

    // Getters and Setters
    public Long getRecyclingTipId() {
        return recyclingTipId;
    }

    public void setRecyclingTipId(Long recyclingTipId) {
        this.recyclingTipId = recyclingTipId;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public WasteCategory getWasteCategory() {
        return wasteCategory;
    }

    public void setWasteCategory(WasteCategory wasteCategory) {
        this.wasteCategory = wasteCategory;
    }
}