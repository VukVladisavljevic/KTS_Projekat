package com.kits.project.services.interfaces;


import com.kits.project.DTOs.DepartureDTO;
import com.kits.project.DTOs.TicketDTO;
import com.kits.project.DTOs.TimeScheduleDTO;
import com.kits.project.model.Station;
import com.kits.project.model.Ticket;
import com.kits.project.model.TimeSchedule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public interface TimeScheduleServiceInterface {

    public TimeSchedule addDeparture(DepartureDTO departureDTO);

    public TimeSchedule deleteDeparture(String lineName, String day, String index);

    public List<String> getDepartures(String lineName, String day);

    public TimeSchedule getAllDepartures(String day);

    public TimeSchedule updateTimeSchedule(Long timeScheduleID, TimeScheduleDTO ticketDTO);

    public boolean archiveTimeSchedule(Long timeScheduleID);
}
