package com.kits.project.services.interfaces;


import com.kits.project.DTOs.TicketDTO;
import com.kits.project.DTOs.TimeScheduleDTO;
import com.kits.project.model.Station;
import com.kits.project.model.Ticket;
import com.kits.project.model.TimeSchedule;
import org.springframework.stereotype.Service;

@Service
public interface TimeScheduleServiceInterface {

    public TimeSchedule addNewTimeSchedule(TimeScheduleDTO timeScheduleDTO);


    public TimeSchedule updateTimeSchedule(Long timeScheduleID, TimeScheduleDTO ticketDTO);


    public boolean archiveTimeSchedule(Long timeScheduleID);
}
