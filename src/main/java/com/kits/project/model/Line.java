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

  //  @ManyToOne(fetch = FetchType.LAZY)
    private Collection<Station> stations;

    @OneToOne(mappedBy = "line", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private TimeSchedule schedule;

   // @OneToMany(fetch = FetchType.LAZY)
    private Collection<Ticket> tickets;

    public Line() {
        super();
        this.active = true;
    }

    public Line(Long id, String name, boolean active, Collection<Station> stations, Collection<TimeSchedule> schedules, Collection<Ticket> tickets) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.stations = stations;
       // this.schedules = schedules;
        this.tickets = tickets;
    }

    public Line(LineDTO lineDTO) {
        this.name = lineDTO.name;
        this.active = lineDTO.active;
        this.stations = new ArrayList<>();
       // this.schedules = new ArrayList<>();
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

    public Collection<Station> getStations() {
        return stations;
    }

    public void setStations(Collection<Station> stations) {
        this.stations = stations;
    }

    public TimeSchedule getSchedules() {
        return schedule;
    }

    public void setSchedules(TimeSchedule schedule) {
        this.schedule = schedule;
    }

    public Collection<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }
}
