package com.kits.project.model;

import com.kits.project.DTOs.LineDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
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
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Station> stations;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TimeSchedule> schedules;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Ticket> tickets;

    public Line() {
        super();
        this.active = true;
    }

    public Line(long id) {
        super();
        this.id = id;
    }


    public Line(Long id, String name, boolean active, List<Station> stations, List<TimeSchedule> schedules, List<Ticket> tickets) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.stations = stations;
        this.schedules = schedules;
        this.tickets = tickets;
    }

    public Line(LineDTO lineDTO) {
        this.name = lineDTO.name;
        this.active = lineDTO.active;
        this.stations = new ArrayList<>();
        this.schedules = new ArrayList<>();
        this.tickets = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<TimeSchedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<TimeSchedule> schedules) {
        this.schedules = schedules;
    }

    public Collection<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
