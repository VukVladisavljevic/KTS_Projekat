package com.kits.project.services.interfaces;


import com.kits.project.DTOs.StationDTO;
import com.kits.project.model.Station;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface StationServiceInterface {

    public Station addNewStation(StationDTO stationDTO);


    public Station updateStation(Long stationID, StationDTO stationDTO);


    public boolean archiveStation(Long lineID);
}
