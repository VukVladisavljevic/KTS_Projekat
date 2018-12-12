package com.kits.project.services.implementations;

import com.kits.project.DTOs.LineDTO;
import com.kits.project.model.Line;
import com.kits.project.model.Station;
import com.kits.project.repositories.LineRepository;
import com.kits.project.services.interfaces.LineServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class LineServiceImplementation implements LineServiceInterface {

    @Autowired
    private LineRepository lineRepository;

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
    public ArrayList<Station> getStationForLine(Long lineID) {
        return null;
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
