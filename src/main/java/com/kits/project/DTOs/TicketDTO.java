package com.kits.project.DTOs;

import java.io.Serializable;
import java.util.Date;

public class TicketDTO implements Serializable {

    public String userEmail;

    public Date startTime;

    public Date endTime;

    public boolean active;

    public String ticketType;


    public TicketDTO() {}

    public TicketDTO(String user, Date startTime, Date endTime, boolean active) {
        this.userEmail = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.active = active;
    }
}
