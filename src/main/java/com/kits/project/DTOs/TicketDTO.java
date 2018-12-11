package com.kits.project.DTOs;

import java.io.Serializable;
import java.util.Date;

public class TicketDTO implements Serializable {

    public UserDTO user;

    public Date startTime;

    public Date endTime;

    public boolean active;

    public TicketDTO() {}

    public TicketDTO(UserDTO user, Date startTime, Date endTime, boolean active) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.active = active;
    }
}
