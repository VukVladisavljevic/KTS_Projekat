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
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    private Set<Station> stations;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TimeSchedule> schedules;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Ticket> tickets;

    public Line() {
        super();
        this.active = true;
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

    public Set<Station> getStations() {
        return stations;
    }

    public void setStations(Set<Station> stations) {
        this.stations = stations;
    }

    public List<TimeSchedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<TimeSchedule> schedules) {
        this.schedules = schedules;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
