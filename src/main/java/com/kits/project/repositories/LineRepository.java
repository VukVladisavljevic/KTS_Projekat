package com.kits.project.repositories;

import com.kits.project.model.Line;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineRepository extends JpaRepository<Line, Long> {
    Line findByName(String name);
}
