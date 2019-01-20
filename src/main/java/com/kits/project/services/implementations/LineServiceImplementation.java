package com.kits.project.services.implementations;

import com.kits.project.DTOs.LineDTO;
import com.kits.project.DTOs.MapLinesDTO;
import com.kits.project.DTOs.StationDTO;
import com.kits.project.model.Line;
import com.kits.project.model.LineStationsOrder;
import com.kits.project.model.Station;
import com.kits.project.model.Transport;
import com.kits.project.repositories.LineRepository;
import com.kits.project.repositories.LineStationsOrderRepository;
import com.kits.project.repositories.StationRepository;
import com.kits.project.repositories.TransportRepository;
import com.kits.project.services.interfaces.LineServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class LineServiceImplementation implements LineServiceInterface {

    @Autowired
    private LineRepository lineRepository;

    @Autowired
    private LineStationsOrderRepository lineStationsRepository;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private TransportRepository transportRepository;

    @Override
    public Line addNewLine(LineDTO line) {

        Line newLine = new Line(line);

        Line lineExisting = lineRepository.findByName(newLine.getName());
        if (lineExisting != null) {
            return null;
        }
        newLine = lineRepository.save(newLine);

        Transport t = new Transport();
        t.setLine(newLine);
        int index = 0;
        Station firstStation = null;
        for(StationDTO s : line.stations) {
            Station station = stationRepository.findById(s.id).orElse(null);
            if(index == 0){
                firstStation = station;
            }
            LineStationsOrder order = new LineStationsOrder(newLine,
                    station,
                    index);
            index += 1;
            station.getLines().add(newLine);
            stationRepository.save(station);
            lineStationsRepository.save(order);
        }
        t.setStation(firstStation);
        transportRepository.save(t);
        return newLine;
    }

    @Override
    public List<Line> getAllLines() {
        return lineRepository.findAll();
    }

    @Transactional
    @Override
    public boolean deleteLine(long id) {
        lineRepository.delete(new Line(id));
        lineRepository.deleteLineStations(id);
        lineStationsRepository.deleteLineStations(id);
        return true;
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
