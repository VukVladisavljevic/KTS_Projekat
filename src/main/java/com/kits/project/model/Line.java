package com.kits.project.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Lupus on 10/30/2018.
 */
@Entity
@Table(name="LINES")
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idline", updatable = false, nullable = false)
    private Long idline;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private boolean active;

    //@ManyToOne(fetch = FetchType.LAZY)
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "lines")   //  proveri naziv kolone da se ne ponavlja
    private Set<Station> stations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy ="line") // proveri
    private List<TimeSchedule> timeSchedules;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)//, mappedBy = "TICKETS")
    private List<Ticket> tickets;

    public Line() {
        super();
        this.active = true;
    }

    public Long getIdLine() {
        return idline;
    }

    public void setIdLine(Long id) {
        this.idline = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Station> getStations() {
        return stations;
    }

    public void setStations(Set<Station> stations) {
        this.stations = stations;
    }

    public List<TimeSchedule> getTimeSchedules() {
        return timeSchedules;
    }

    public void setTimeSchedules(List<TimeSchedule> schedules) {
        this.timeSchedules = schedules;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
