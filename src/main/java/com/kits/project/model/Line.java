package com.kits.project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kits.project.DTOs.LineDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Lupus on 10/30/2018.
 */
@Entity
public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idline", updatable = false, nullable = false)
    private Long idline;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private boolean active;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "lines")   //  proveri naziv kolone da se ne ponavlja
    @JsonIgnoreProperties("stations")
    private List<Station> stations;

    @JsonManagedReference(value="line_timeSchedule")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy ="line") // proveri
    private TimeSchedule timeSchedule;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)//, mappedBy = "TICKETS")
    private List<Ticket> tickets;

    public Line() {
        super();
        this.active = true;
    }

    public Line(Long ID) {
        super();
        this.active = true;
        this.idline = ID;
    }

    public Line(String name) {
        super();
        this.active = true;
        this.name = name;
        this.stations = new ArrayList<Station>();
    }

    public Line(LineDTO lineDTO) {
        this.active = true;
        this.name = lineDTO.name;
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

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public TimeSchedule getTimeSchedule() {
        return timeSchedule;
    }

    public void setTimeSchedules(TimeSchedule schedule) {
        this.timeSchedule = schedule;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTimeSchedule(TimeSchedule timeSchedule) {
        timeSchedule.setLine(this);
        this.timeSchedule = timeSchedule;
    }
}
