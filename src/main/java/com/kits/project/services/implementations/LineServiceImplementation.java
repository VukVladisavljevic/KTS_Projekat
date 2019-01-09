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
    public Line addNewLine(Line line) {

        Line lineExisting = lineRepository.findByName(line.getName());
        if (lineExisting != null) {
            return null;
        }
        Line newLine = new Line(line.getName());
        newLine = lineRepository.save(newLine);
        System.out.println(line.getStations().size());

        for(Station station : line.getStations()){
            LineStationsOrder order = new LineStationsOrder(newLine,
                    new Station(station.getId()),
                    line.getStations().indexOf(station));
            station.getLines().add(newLine);
            stationRepository.save(station);
            lineStationsRepository.save(order);
        }
        return newLine;
    }

    @Override
    public List<Line> getAllLines() {

        return lineRepository.findAll();
    }

    @Override
    public ArrayList<MapLinesDTO> getLinesForMap() {
        List<Line> lines = lineRepository.findAll();
        ArrayList<MapLinesDTO> mapLines = new ArrayList<>();
        int index = 0;
        for(Line line : lines){
            index += 1;
            ArrayList<Station> stations = this.getStationForLine(line.getIdLine());
            if(stations.size()==0) {
                continue;
            }
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
        ArrayList<Station> stations = new ArrayList<>();
        for(LineStationsOrder order:orderedStations){
            Station s = stationRepository.findById(order.getStation().getId()).orElse(null);
            s.setLines(null);
            stations.add(s);
        }
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
