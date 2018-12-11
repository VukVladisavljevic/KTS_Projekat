package com.kits.project.controllers;

import com.kits.project.DTOs.StationDTO;
import com.kits.project.model.Station;
import com.kits.project.model.TimeSchedule;
import com.kits.project.services.interfaces.StationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("api")
@RestController
public class StationController {

    @Autowired
    private StationServiceInterface stationService;

//    @RequestMapping(value = "/station/create",method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<TimeSchedule> create(@RequestBody TimeSchedule schedule){
//        TimeSchedule newSchedule = stationService.addNewSchedule(schedule);
//        return new ResponseEntity<>(newSchedule, HttpStatus.OK);
//    }

//    @RequestMapping(value = "/stations", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<ArrayList<Station>> index(){
//        ArrayList<Station> stations = stationService.getAll();
//        return new ResponseEntity<>(stations, HttpStatus.OK);
//    }

    @RequestMapping(value = "/station/{stationId}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Station> update(@RequestBody StationDTO station, @PathVariable Long stationId){
        Station updatedStation = stationService.updateStation(stationId, station);
        if(updatedStation != null) {
            return new ResponseEntity<>(updatedStation, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @RequestMapping(value = "/station/{stationId}/add-line/{lineId}", method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Station> addLine(@PathVariable Long stationId,  @PathVariable Long lineId){
//        Station updatedStation = stationService.addNewStation(stationId, lineId);
//        if(updatedStation != null) {
//            return new ResponseEntity<>(updatedStation, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @RequestMapping(value = "/station/{stationId}/archive", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> archiveStation(@PathVariable Long stationId){
        boolean isArchived= stationService.archiveStation(stationId);
        if(isArchived) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}