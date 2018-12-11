package com.kits.project.services.implementations;

import com.kits.project.DTOs.TimeScheduleDTO;
import com.kits.project.model.TimeSchedule;
import com.kits.project.services.interfaces.TimeScheduleServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class TimeScheduleServiceImplementation  implements TimeScheduleServiceInterface {
    @Override
    public TimeSchedule addNewTimeSchedule(TimeScheduleDTO timeScheduleDTO) {
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
