package com.kits.project.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Time;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TimeScheduleItem {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String time;

    public TimeScheduleItem() {}

    public TimeScheduleItem(Time time) {
        this.time = "vreme";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
