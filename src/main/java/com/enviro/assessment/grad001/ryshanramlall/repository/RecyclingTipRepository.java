package com.enviro.assessment.grad001.ryshanramlall.repository;

import com.enviro.assessment.grad001.ryshanramlall.model.RecyclingTip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecyclingTipRepository extends JpaRepository<RecyclingTip, Long> {
    // Custom queries can be added here if needed
}
