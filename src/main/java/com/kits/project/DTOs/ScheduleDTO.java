package com.kits.project.DTOs;

import java.io.Serializable;
import java.util.Date;

public class ScheduleDTO implements Serializable {

    public Date startDate;
    public Date endDate;

    public ScheduleDTO(){}

    public ScheduleDTO(Date startDate, Date endDate){
         this.startDate = startDate;
         this.endDate = endDate;
    }

}
