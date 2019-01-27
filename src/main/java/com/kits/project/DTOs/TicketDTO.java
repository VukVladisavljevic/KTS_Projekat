package com.kits.project.DTOs;

import java.io.Serializable;
import java.util.Date;

public class TicketDTO implements Serializable {

    public String token;

    public String ticketType;

    public String id;

    public Date startTime;

    public Date endTime;

    public boolean active;

    public double price;

    public TicketDTO() {}

    public TicketDTO(String token, String ticketType, Date startTime, Date endTime, String id, boolean active) {
        this.token = token;
        this.ticketType = ticketType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.id = id;
        this.active = active;
    }
}
