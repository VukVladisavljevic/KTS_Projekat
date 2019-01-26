package com.kits.project.services.implementations;

import com.kits.project.DTOs.DepartureDTO;
import com.kits.project.DTOs.TimeScheduleDTO;
import com.kits.project.model.*;
import com.kits.project.repositories.LineRepository;
import com.kits.project.repositories.TimeScheduleRepository;
import com.kits.project.repositories.TransportRepository;
import com.kits.project.services.interfaces.TimeScheduleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

import java.sql.Array;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.List;
import java.util.*;

@Service
public class TimeScheduleServiceImplementation  implements TimeScheduleServiceInterface {

    @Autowired
    private TimeScheduleRepository timeScheduleRepository;

    @Autowired
    private LineRepository lineRepository;

    @Autowired
    private TransportRepository transportRepository;

    @Override
    public TimeSchedule addDeparture(DepartureDTO departureDTO) {
        Line selectedLine = lineRepository.findByName(departureDTO.getLineName());

        if (selectedLine == null) {
            return null;
        }
        List<Transport> allTransportsOnLine = transportRepository.findByLine(selectedLine);

        //dodaj taj date u odgovarajucu listu odgovarajuceg TimeSchedule objekta

        if(selectedLine.getTimeSchedule() == null) {
            TimeSchedule newTimeSchedule = new TimeSchedule();
            newTimeSchedule.addDeparture(departureDTO.getTime(), departureDTO.getDayOfWeek());
            newTimeSchedule.setTransport(allTransportsOnLine.get(0));

            selectedLine.addTimeSchedule(newTimeSchedule);
            newTimeSchedule.setLine(selectedLine);
            lineRepository.flush();
        } else {
            TimeSchedule newTimeSchedule = selectedLine.getTimeSchedule();
            newTimeSchedule.addDeparture(departureDTO.getTime(), departureDTO.getDayOfWeek());
            Random rand = new Random();

            int transportIndex = rand.nextInt(allTransportsOnLine.size());
            newTimeSchedule.setTransport(allTransportsOnLine.get(transportIndex));
            selectedLine.addTimeSchedule(newTimeSchedule);
            newTimeSchedule.setLine(selectedLine);
            lineRepository.flush();
        }

        return null;
    }

    @Override
    public TimeSchedule deleteDeparture(String lineName, String day, String index) {
        Line selectedLine = lineRepository.findByName(lineName);

        if (selectedLine == null) {
            return null;
        }

        TimeSchedule newTimeSchedule = selectedLine.getTimeSchedule();
        newTimeSchedule.deleteDeparture(day, index);

        lineRepository.flush();


        return newTimeSchedule;
    }

    @Override
    public List<String> getDepartures(String lineName, String day) {
        Line line = lineRepository.findByName(lineName);

        TimeSchedule timeSchedule = line.getTimeSchedule();

        if(timeSchedule==null){
            return new ArrayList<>();
        }

        if(day.equals("1")) {
            return timeSchedule.getWorkingDaySchedule();
        } else if (day.equals("2")) {
            return timeSchedule.getSaturdaySchedule();
        } else {
            return timeSchedule.getSundaySchedule();
        }

    }

    @Override
    public TimeSchedule getAllDepartures(String lineName) {
        return null;
    }

    @Override
    public TimeSchedule updateTimeSchedule(Long timeScheduleID, TimeScheduleDTO ticketDTO) {
        return null;
    }

    @Override
    public boolean archiveTimeSchedule(Long timeScheduleID) {
        return false;
    }
}
