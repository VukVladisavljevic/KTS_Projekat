package com.kits.project.DTOs;

import com.kits.project.model.Schedule;
import com.kits.project.model.TicketType;

import java.io.Serializable;
import java.util.Date;

public class PricelistDTO implements Serializable {

    //public ScheduleDTO schedule;
    public Date startDate;
    public Date endDate;
    public TicketType ticketType;
    public double price;

    public PricelistDTO(){}

    public PricelistDTO(Date startDate, Date endDate, TicketType ticketType, double price){
        //this.schedule = schedule;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ticketType = ticketType;
        this.price = price;
    }
}
