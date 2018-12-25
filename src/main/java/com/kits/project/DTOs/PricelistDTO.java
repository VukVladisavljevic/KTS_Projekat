package com.kits.project.DTOs;

import com.kits.project.model.Schedule;
import com.kits.project.model.TicketType;

import java.io.Serializable;

public class PricelistDTO implements Serializable {

    public ScheduleDTO schedule;
    public TicketType ticketType;
    public double price;

    public PricelistDTO(){}

    public PricelistDTO(ScheduleDTO schedule, TicketType ticketType, double price){
        this.schedule = schedule;
        this.ticketType = ticketType;
        this.price = price;
    }
}
