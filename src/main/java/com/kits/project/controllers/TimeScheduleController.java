package com.kits.project.controllers;

import com.kits.project.DTOs.TimeScheduleDTO;
import com.kits.project.model.TimeSchedule;
import com.kits.project.services.interfaces.TimeScheduleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("api")
@RestController
public class TimeScheduleController {

    @Autowired
    TimeScheduleServiceInterface timeScheduleService;

    @RequestMapping(value = "/time-schedule/create",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TimeSchedule> create(@RequestBody TimeScheduleDTO schedule){
        TimeSchedule newSchedule = timeScheduleService.addNewTimeSchedule(schedule);
        return new ResponseEntity<>(newSchedule, HttpStatus.OK);
    }

//    @RequestMapping(value = "/time-schedules", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<ArrayList<TimeSchedule>> index(){
//        ArrayList<TimeSchedule> allSchedules = timeScheduleService.getAll();
//        return new ResponseEntity<>(allSchedules, HttpStatus.OK);
//    }

    @RequestMapping(value = "/time-schedule/{scheduleId}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TimeSchedule> update(@RequestBody TimeScheduleDTO schedule, @PathVariable Long scheduleId){
        TimeSchedule updatedSchedule = timeScheduleService.updateTimeSchedule(scheduleId, schedule);
        if(updatedSchedule != null) {
            return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @RequestMapping(value = "/time-schedule/line/{lineId}", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<ArrayList<TimeSchedule>> getTimeScheduleForLine(@PathVariable Long lineId){
//        ArrayList<TimeSchedule> scheduleForLine = timeScheduleService.getTimeScheduleForLine(lineId);
//        return new ResponseEntity<>(scheduleForLine, HttpStatus.OK);
//    }

//    @RequestMapping(value = "/time-schedule/station/{stationId}", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<ArrayList<TimeSchedule>> getTimeScheduleForStation(@PathVariable Long stationId){
//        ArrayList<TimeSchedule> scheduleForStation = timeScheduleService.getTimeScheduleForStation(stationId);
//        return new ResponseEntity<>(scheduleForStation, HttpStatus.OK);
//    }

//    @RequestMapping(value = "/time-schedule/{scheduleId}/add-station/{stationId}", method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<TimeSchedule> addStation(@PathVariable Long scheduleId,  @PathVariable Long stationId){
//        TimeSchedule updatedSchedule = timeScheduleService.addStation(scheduleId, stationId);
//        if(updatedSchedule != null) {
//            return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @RequestMapping(value = "/time-schedule/{scheduleId}/add-line/{lineId}", method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<TimeSchedule> addLine(@PathVariable Long scheduleId,  @PathVariable Long lineId){
//        TimeSchedule updatedSchedule = timeScheduleService.addLine(scheduleId, lineId);
//        if(updatedSchedule != null) {
//            return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

}
