package com.kits.project.repositories;

import com.kits.project.model.Line;
import com.kits.project.model.Transport;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportRepository extends JpaRepository<Transport, Long> {

    List<Transport> findByLine(Line l);

}
