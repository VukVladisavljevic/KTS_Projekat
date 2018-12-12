package com.kits.project.model;

import javax.persistence.*;

@Entity
@Table(name="PRICELIST")
public class Pricelist {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Schedule schedule;

    // mozda nije ok
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    @Column
    private double price;

    // Getters and setters

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public double getPrice() {
        return price;
    }
}
