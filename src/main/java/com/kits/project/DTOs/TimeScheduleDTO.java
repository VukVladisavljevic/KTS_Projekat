package com.kits.project.DTOs;

import java.io.Serializable;

public class TimeScheduleDTO implements Serializable {

    public LineDTO line;


    //TODO Sta je ovo, valjda lista treba
    //private Time time;

    public StationDTO station;

    public TimeScheduleDTO() {}

    public TimeScheduleDTO(LineDTO line, StationDTO station) {
        this.line = line;
        this.station = station;
    }
}
