package com.kits.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kits.project.DTOs.TimeScheduleDTO;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
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

    @OneToOne(fetch = FetchType.EAGER)
    private Transport transport;

    @Column(name = "time")
    private Time time;

    @ElementCollection
    private List<String> workingDaySchedule;

    @ElementCollection
    private List<String> saturdaySchedule;

    @ElementCollection
    private List<String> sundaySchedule;

    public List<String> getSaturdaySchedule() {
        return saturdaySchedule;
    }

    public void setSaturdaySchedule(List<String> saturdaySchedule) {
        this.saturdaySchedule = saturdaySchedule;
    }

    public List<String> getSundaySchedule() {
        return sundaySchedule;
    }

    public void setSundaySchedule(List<String> sundaySchedule) {
        this.sundaySchedule = sundaySchedule;
    }

    public List<String> getWorkingDaySchedule() {
        return workingDaySchedule;
    }

    public void setWorkingDaySchedule(List<String> workingDaySchedule) {
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

    public boolean addDeparture(String time, String dayOfWeek) {
        switch (dayOfWeek){
            case "1":
                this.getWorkingDaySchedule().add(time);
                return true;
            case "2":
                this.getSaturdaySchedule().add(time);
                return true;
            case "3":
                this.getSundaySchedule().add(time);
                return true;
        }
        return false;
    }

    public boolean deleteDeparture(String dayOfWeek, String index) {
        int depIndex = Integer.parseInt(index);
        switch (dayOfWeek){
            case "1":
                this.getWorkingDaySchedule().remove(depIndex);
                return true;
            case "2":
                this.getSaturdaySchedule().remove(depIndex);
                return true;
            case "3":
                this.getSundaySchedule().remove(depIndex);
                return true;
        }
        return false;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }
}
