package com.kits.project.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Lupus on 10/30/2018.
 */
@Entity
@Table(name="TICKETS")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@JoinColumn(name = "idline", nullable = false)
    private User user;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Column
    private boolean active;

    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    public Ticket() {
    }

    public Ticket(User user, Date startTime, Date endTime, boolean active) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public TicketType getTicketType() {
        return ticketType;
    }
}
