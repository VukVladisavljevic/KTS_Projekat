package com.kits.project.services.implementations;

import com.kits.project.DTOs.ScheduleDTO;
import com.kits.project.model.Schedule;
import com.kits.project.services.interfaces.ScheduleServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImplementation implements ScheduleServiceInterface {

    @Override
    public Schedule addSchedule(ScheduleDTO scheduleDTO){return null;}

    @Override
    public Schedule updateSchedule(Long scheduleId, ScheduleDTO scheduleDTO) {return null;}
}
