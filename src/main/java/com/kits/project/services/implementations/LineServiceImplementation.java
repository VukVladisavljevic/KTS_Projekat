package com.kits.project.services.implementations;

import com.kits.project.DTOs.LineDTO;
import com.kits.project.DTOs.MapLinesDTO;
import com.kits.project.model.Line;
import com.kits.project.model.LineStationsOrder;
import com.kits.project.model.Station;
import com.kits.project.repositories.LineRepository;
import com.kits.project.repositories.LineStationsOrderRepository;
import com.kits.project.repositories.StationRepository;
import com.kits.project.services.interfaces.LineServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Service
public class LineServiceImplementation implements LineServiceInterface {

    @Autowired
    private LineRepository lineRepository;

    @Autowired
    private LineStationsOrderRepository lineStationsRepository;

    @Autowired
    private StationRepository stationRepository;

    @Override
    public Line addNewLine(LineDTO lineDTO) {

        Line lineExisting = lineRepository.findByName(lineDTO.name);

        if (lineExisting == null) {
            Line newLine = lineRepository.save(new Line(lineDTO));
            return newLine;
        }

        return null;
    }

    @Override
    public List<Line> getAllLines() {

        return lineRepository.findAll();
    }

    @Override
    public List<MapLinesDTO> getLinesForMap() {
        List<Line> lines = lineRepository.findAll();
        List<MapLinesDTO> mapLines = new ArrayList<>();
        for(Line line : lines){
            ArrayList<Station> stations = this.getStationForLine(line.getIdLine());
            MapLinesDTO mapLine = new MapLinesDTO(stations.get(0), stations.get(stations.size() -1), line);
            stations.remove(0);
            stations.remove(stations.size() -1 );
            mapLine.waypoints = stations;
            mapLines.add(mapLine);
        }
        return mapLines;
    }

    @Override
    public ArrayList<Station> getStationForLine(Long lineID) {
        ArrayList<LineStationsOrder> orderedStations = lineStationsRepository.findByLineOrderByStationOrderAsc(new Line(lineID));
         System.out.println(orderedStations);
        ArrayList<Station> stations = new ArrayList<>();
        for(LineStationsOrder order:orderedStations){
            System.out.println(order.getStation().getId());
            Station s = stationRepository.findById(order.getStation().getId()).orElse(null);
            stations.add(s);
            System.out.println(order);
        }
        System.out.println(stations);
        return stations;
    }

    @Override
    public Line updateLine(Long lineID, LineDTO lineDTO) {
        return null;
    }

    @Override
    public Line addStation(Long lineID, Long stationID) {
        return null;
    }

    @Override
    public Line addTimeSchedule(Long lineID, Long scheduleID) {
        return null;
    }

    @Override
    public Line addTicket(Long lineID, Long ticketID) {
        return null;
    }

    @Override
    public boolean archiveLine(Long lineID) {
        return false;
    }
}
