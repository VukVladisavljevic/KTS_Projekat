package com.kits.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kits.project.DTOs.TimeScheduleDTO;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Lupus on 10/30/2018.
 */
@Entity(name = "TIMESCHEDULE")
public class TimeSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    //@Column(name = "line")


    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value="line_timeSchedule")
    @JoinColumn(name = "idline", nullable = false)
    private Line line;

    @Column(name = "time")
    private Time time;

    @ElementCollection
    private List<Date> workingDaySchedule;

    public List<Date> getWorkingDaySchedule() {
        return workingDaySchedule;
    }

    public void setWorkingDaySchedule(List<Date> workingDaySchedule) {
        this.workingDaySchedule = workingDaySchedule;
    }

    //@Column(name = "station")
    @ManyToOne(fetch = FetchType.LAZY)
    private Station station;

    @Enumerated(EnumType.STRING)
    private Day day;

    public TimeSchedule() {
        this.workingDaySchedule = new ArrayList<>();
    }

    public TimeSchedule(Line line, Time time, Station station) {
        this.line = line;
        this.time = time;
        this.station = station;
        this.workingDaySchedule = new ArrayList<>();
    }

    public TimeSchedule(TimeScheduleDTO timeScheduleDTO) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Day getDay() {
        return day;
    }
}
