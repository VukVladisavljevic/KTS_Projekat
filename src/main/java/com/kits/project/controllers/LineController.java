package com.kits.project.controllers;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.kits.project.DTOs.LineDTO;
import com.kits.project.DTOs.MapLinesDTO;
import com.kits.project.model.Line;
import com.kits.project.model.Station;
import com.kits.project.services.interfaces.LineServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RequestMapping("api")
@RestController
public class LineController {

    @Autowired
    private LineServiceInterface lineServiceInterface;

    @RequestMapping(value = "/line/create",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Line> create(@RequestBody LineDTO line){
        Line newLine = lineServiceInterface.addNewLine(line);
        return new ResponseEntity<Line>(newLine, HttpStatus.OK);
    }

    @RequestMapping(value = "/line/{lineId}",method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable int lineId){
        lineServiceInterface.deleteLine(Integer.toUnsignedLong(lineId));
        return new ResponseEntity(true, HttpStatus.OK);
    }

    @RequestMapping(value = "/lines", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Line>> index(){
        List<Line> allLinesList = lineServiceInterface.getAllLines();
        ArrayList<Line> allLines = new ArrayList<Line>();
        for (Line l : allLinesList){
            allLines.add(l);
        }
        return new ResponseEntity<ArrayList<Line>>(allLines, HttpStatus.OK);
    }

    @RequestMapping(value = "/line/{lineId}/stations", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Station>> getStationsForLine(@PathVariable Long lineId){
        ArrayList<Station> linesForStation = lineServiceInterface.getStationForLine(lineId);
        return new ResponseEntity<>(linesForStation, HttpStatus.OK);
    }

    @RequestMapping(value = "/lines/map", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<MapLinesDTO> getLinesForMap(){
        ArrayList<MapLinesDTO> linesForMap = lineServiceInterface.getLinesForMap();
        return linesForMap;
    }

    @RequestMapping(value = "/line/{lineId}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Line> update(@RequestBody LineDTO line, @PathVariable Long lineId){
        Line updatedLine= lineServiceInterface.updateLine(lineId, line);
        if(updatedLine != null) {
            return new ResponseEntity<>(updatedLine, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/line/{lineId}/add-station/{stationId}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Line> addStation(@PathVariable Long lineId,  @PathVariable Long stationId){
        Line updatedLine= lineServiceInterface.addStation(lineId, stationId);
        if(updatedLine != null) {
            return new ResponseEntity<>(updatedLine, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/line/{lineId}/add-time-schedule/{scheduleId}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Line> addTimeSchedule(@PathVariable Long lineId,  @PathVariable Long scheduleId){
        Line updatedLine= lineServiceInterface.addTimeSchedule(lineId, scheduleId);
        if(updatedLine != null) {
            return new ResponseEntity<>(updatedLine, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/line/{lineId}/add-ticket/{ticketId}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Line> addTicket(@PathVariable Long lineId,  @PathVariable Long ticketId){
        Line updatedLine= lineServiceInterface.addTicket(lineId, ticketId);
        if(updatedLine != null) {
            return new ResponseEntity<>(updatedLine, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/line/{lineId}/archive", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Line> archiveLine(@PathVariable Long lineId){
        boolean isArchived= lineServiceInterface.archiveLine(lineId);
        if(isArchived) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}