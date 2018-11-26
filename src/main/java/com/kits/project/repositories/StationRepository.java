package com.kits.project.repositories;

import com.kits.project.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {
    Station findByName(String name);
}
