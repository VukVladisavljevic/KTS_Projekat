package com.kits.project.repositories;

import com.kits.project.model.Line;
import com.kits.project.model.LineStationsOrder;
import com.kits.project.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface LineStationsOrderRepository extends JpaRepository<LineStationsOrder, Long> {

    ArrayList<LineStationsOrder> findByLineOrderByStationOrderAsc(Line line);

    //LineStationsOrder findByLineAndStation(Line line, Station station);

    //LineStationsOrder findByLineAndStationAndStationOrder(Line line, Station station, int order);

    @Modifying
    @Query(value = "DELETE FROM line_stations_order WHERE line_idline = ?1", nativeQuery = true)
    void deleteLineStations(long id);

    @Query(value = "SELECT * FROM line_stations_order WHERE line_idline = ?1 AND station_order = ?2", nativeQuery = true)
    LineStationsOrder findNextStation(long id, int stationOrder);
}
