package com.kits.project.DTOs;

public class DepartureDTO {

    private String time;
    private String dayOfWeek;

    private String lineName;

    public DepartureDTO() {}

    public DepartureDTO(String time, String dayOfWeek, String lineName) {
        this.time = time;
        this.dayOfWeek = dayOfWeek;
        this.lineName = lineName;
    }

    public String getTime() {
        return time;
    }


    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
