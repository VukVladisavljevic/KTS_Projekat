package com.kits.project.model;

import com.kits.project.DTOs.PricelistDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="PRICELIST")
public class Pricelist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    //private Schedule schedule;
    @Column(name = "startdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "enddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    // mozda nije ok
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    @Column(name = "price")
    private double price;

    // Getters and setters

    /**public void setSchedule(Schedule schedule) {
     this.schedule = schedule;
     }**/

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**public Schedule getSchedule() {
     return schedule;
     }**/

    public TicketType getTicketType() {
        return ticketType;
    }

    public double getPrice() {
        return price;
    }

    // Constructors
    public Pricelist(){

    }

    public Pricelist(PricelistDTO pricelistDTO){
        this.startDate = pricelistDTO.startDate;
        this.endDate = pricelistDTO.endDate;
        this.price = pricelistDTO.price;
        this.ticketType = pricelistDTO.ticketType;
    }
/*
    @Override
    public String toString() {
        return "{startDate:\"" + startDate +
                "\",endDate:\"" + endDate +
                "\",ticketType:\"" + ticketType +
                "\",price:" + price +
                "}";
    }*/
}
