package com.kits.project.controllers;

import com.kits.project.model.Line;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("api")
@RestController
public class LineController {
    @Autowired
    private LineService lineService;

    @RequestMapping(value = "/line/create",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Line> create(@RequestBody Line line){
        Line newLine = lineService.addNewLine(line);
        return new ResponseEntity<>(newLine, HttpStatus.OK);
    }

    @RequestMapping(value = "/lines", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Line>> index(){
        ArrayList<Line> allLines = lineService.getAllLines();
        return new ResponseEntity<>(allLines, HttpStatus.OK);
    }

    @RequestMapping(value = "/line/station/{stationId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Line>> getStationsForLine(@PathVariable Long stationId){
        ArrayList<Line> linesForStation = lineService.getStationsForLine(stationId);
        return new ResponseEntity<>(linesForStation, HttpStatus.OK);
    }

    @RequestMapping(value = "/line/{lineId}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Line> update(@RequestBody Line line, @PathVariable Long lineId){
        Line updatedLine= lineService.update(lineId, line);
        if(updatedLine != null) {
            return new ResponseEntity<>(updatedLine, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/line/{lineId}/add-station/{stationId}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Line> addStation(@PathVariable Long lineId,  @PathVariable Long stationId){
        Line updatedLine= lineService.addStation(lineId, stationId);
        if(updatedLine != null) {
            return new ResponseEntity<>(updatedLine, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/line/{lineId}/add-time-schedule/{scheduleId}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Line> addTimeSchedule(@PathVariable Long lineId,  @PathVariable Long scheduleId){
        Line updatedLine= lineService.addTimeSchedule(lineId, scheduleId);
        if(updatedLine != null) {
            return new ResponseEntity<>(updatedLine, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/line/{lineId}/add-ticket/{ticketId}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Line> addTicket(@PathVariable Long lineId,  @PathVariable Long ticketId){
        Line updatedLine= lineService.addTicket(lineId, ticketId);
        if(updatedLine != null) {
            return new ResponseEntity<>(updatedLine, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/line/{lineId}/archive", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Line> archiveLine(@PathVariable Long lineId){
        boolean isArchived= lineService.archiveLine(lineId);
        if(isArchived) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}