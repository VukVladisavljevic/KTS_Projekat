package com.kits.project.repositories;

import com.kits.project.model.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LineRepository extends JpaRepository<Line, Long> {

    Line findByName(String name);

    @Modifying
    @Query(value = "DELETE FROM stations_lines WHERE lines_idline = ?1", nativeQuery = true)
    void deleteLineStations(long id);
}
