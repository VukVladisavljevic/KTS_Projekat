package com.kits.project.DTOs;

import java.io.Serializable;
import java.util.Date;

public class TicketDTO implements Serializable {

    public String token;

    public String ticketType;

    public TicketDTO() {}

    public TicketDTO(String token, String ticketType) {
        this.token = token;
        this.ticketType = ticketType;
    }
}
