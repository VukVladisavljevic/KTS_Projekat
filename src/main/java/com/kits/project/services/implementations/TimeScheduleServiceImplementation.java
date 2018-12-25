package com.kits.project.services.implementations;

import com.kits.project.DTOs.DepartureDTO;
import com.kits.project.DTOs.TimeScheduleDTO;
import com.kits.project.model.Line;
import com.kits.project.model.Station;
import com.kits.project.model.TimeSchedule;
import com.kits.project.model.TimeScheduleItem;
import com.kits.project.repositories.LineRepository;
import com.kits.project.repositories.TimeScheduleRepository;
import com.kits.project.services.interfaces.TimeScheduleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TimeScheduleServiceImplementation  implements TimeScheduleServiceInterface {

    @Autowired
    private TimeScheduleRepository timeScheduleRepository;

    @Autowired
    private LineRepository lineRepository;

    @Override
    public TimeSchedule addDeparture(DepartureDTO departureDTO) {
        Line selectedLine = lineRepository.findByName(departureDTO.getLineName());

        if (selectedLine == null) {
            return null;
        }

        //konvertuj string datuma u date
        DateFormat formatter = new SimpleDateFormat("hh:mm");

        Date date = null;
        try {
            date = formatter.parse(departureDTO.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //dodaj taj date u odgovarajucu listu odgovarajuceg TimeSchedule objekta

        if(selectedLine.getTimeSchedule() == null) {
            TimeSchedule newTimeSchedule = new TimeSchedule();
            newTimeSchedule.getWorkingDaySchedule().add(date);

            selectedLine.addTimeSchedule(newTimeSchedule);
            lineRepository.flush();
        } else {
            TimeSchedule newTimeSchedule = selectedLine.getTimeSchedule();
            newTimeSchedule.getWorkingDaySchedule().add(date);

            selectedLine.addTimeSchedule(newTimeSchedule);
            lineRepository.flush();
        }

        return null;
    }

    @Override
    public List<Date> getDepartures(String lineName, String day) {
        Line line = lineRepository.findByName(lineName);

        TimeSchedule timeSchedule = line.getTimeSchedule();

        return timeSchedule.getWorkingDaySchedule();
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
