package com.kits.project.model;

import javax.persistence.*;

@Entity
@Table(name="PRICELIST")
public class Pricelist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Schedule schedule;

    // mozda nije ok
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    @Column(name = "price")
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
