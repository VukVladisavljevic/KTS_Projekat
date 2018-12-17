package com.kits.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kits.project.DTOs.TimeScheduleDTO;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Lupus on 10/30/2018.
 */
@Entity
public class TimeSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "line_id")
    private Line line;

    @Column(name = "time")
    private Time time;

    @OneToMany
    private Collection<TimeScheduleItem> timeScheduleItems;

    public TimeSchedule() { }

    public TimeSchedule(Line line, Time time) {
        this.line = line;
        this.time = time;
    }

    public TimeSchedule(TimeScheduleDTO timeScheduleDTO) {
        this.line = null;
        this.time = null;
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

}
