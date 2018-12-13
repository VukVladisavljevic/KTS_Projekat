package com.kits.project.services.interfaces;

import com.kits.project.DTOs.ScheduleDTO;
import com.kits.project.model.Schedule;
import org.springframework.stereotype.Service;

@Service
public interface ScheduleServiceInterface{

    Schedule addSchedule(ScheduleDTO scheduleDTO);

    Schedule updateSchedule(Long scheduleId, ScheduleDTO scheduleDTO);
}
