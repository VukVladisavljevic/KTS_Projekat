package com.kits.project.DTOs;

import java.io.Serializable;
import java.util.Date;

public class TicketDTO implements Serializable {

    public String token;

    public String ticketType;

    public String id;

    public Date startTime;

    public Date endTime;

    public TicketDTO() {}

    public TicketDTO(String token, String ticketType, Date startTime, Date endTime, String id) {
        this.token = token;
        this.ticketType = ticketType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.id = id;
    }
}
