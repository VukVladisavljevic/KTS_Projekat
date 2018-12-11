package com.kits.project.DTOs;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class LineDTO implements Serializable {

    public String name;

    public boolean active;

    public Set<StationDTO> stations;

    public List<TimeScheduleDTO> schedules;

    public List<TicketDTO> tickets;

    public LineDTO() {}

    public LineDTO(String name, boolean active, Set<StationDTO> stations, List<TimeScheduleDTO> schedules, List<TicketDTO> tickets) {
        this.name = name;
        this.active = active;
        this.stations = stations;
        this.schedules = schedules;
        this.tickets = tickets;
    }
}
