package com.kits.project.services.implementations;


import com.kits.project.DTOs.StationDTO;
import com.kits.project.model.Station;
import com.kits.project.repositories.StationRepository;
import com.kits.project.services.interfaces.StationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StationServiceImplementation implements StationServiceInterface {

    @Autowired
    private StationRepository stationRepository;

    @Override
    public Station addNewStation(StationDTO stationDTO) {

        Station stationExisting = stationRepository.findByName(stationDTO.name);

        if (stationExisting == null) {
            Station newStation = stationRepository.save(new Station(stationDTO));
            return newStation;
        }

        return null;
    }

    @Override
    public Station updateStation(Long stationID, StationDTO stationDTO) {
        return null;
    }

    @Override
    public boolean archiveStation(Long lineID) {
        return false;
    }

    @Override
    public List<Station> getStations() {
        List<Station> stations = stationRepository.findAll();
//        for(Station s : stations) {
//            s.setLines(null);
//        }
        return  stations;

    }
}
