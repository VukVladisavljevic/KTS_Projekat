package com.kits.project.repositories;

import com.kits.project.model.Line;
import com.kits.project.model.LineStationsOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface LineStationsOrderRepository extends JpaRepository<LineStationsOrder, Long> {
    ArrayList<LineStationsOrder> findByLineOrderByOrderAsc(Line line);
}
