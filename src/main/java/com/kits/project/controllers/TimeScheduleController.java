package com.kits.project.controllers;

import com.kits.project.DTOs.DepartureDTO;
import com.kits.project.DTOs.TimeScheduleDTO;
import com.kits.project.model.TimeSchedule;
import com.kits.project.services.interfaces.TimeScheduleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RequestMapping("api")
@RestController
public class TimeScheduleController {

    @Autowired
    private TimeScheduleServiceInterface timeScheduleService;

    @RequestMapping(value = "/time-schedule/create",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TimeSchedule> create(@RequestBody DepartureDTO schedule){
        TimeSchedule newSchedule = timeScheduleService.addDeparture(schedule);
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

    @RequestMapping(value = "/time-schedule/line/{lineName}/{day}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<String>> getTimeScheduleForLine(@PathVariable String lineName, @PathVariable String day ){
        List<String> scheduleForLine = timeScheduleService.getDepartures(lineName, day);

        ArrayList<String> departures = new ArrayList<String>();
        for (String l : scheduleForLine){
            departures.add(l);
        }
        return new ResponseEntity<ArrayList<String>>(departures, HttpStatus.OK);
    }

    @RequestMapping(value = "/time-schedule/line/{lineName}/{day}/{index}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TimeSchedule> deleteDepartureForLine(@PathVariable String lineName, @PathVariable String day, @PathVariable String index ) {

        System.out.println(lineName + " " + day + " " + index);
        TimeSchedule newSchedule = timeScheduleService.deleteDeparture(lineName, day, index);
        return new ResponseEntity<>(newSchedule, HttpStatus.OK);
    }

}
