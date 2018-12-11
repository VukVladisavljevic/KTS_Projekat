package com.kits.project.services.implementations;


import com.kits.project.DTOs.StationDTO;
import com.kits.project.model.Station;
import com.kits.project.services.interfaces.StationServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class StationServiceImplementation implements StationServiceInterface {
    @Override
    public Station addNewStation(StationDTO stationDTO) {
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
}
