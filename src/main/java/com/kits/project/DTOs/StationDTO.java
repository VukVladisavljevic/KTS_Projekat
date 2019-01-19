package com.kits.project.DTOs;

import java.io.Serializable;
import java.util.Set;

public class StationDTO implements Serializable {

    public long id;

    public String address;

    public String name;

    public Set<LineDTO> lines;

    public boolean active;

    public float lat;

    public float lng;

    public StationDTO() {}

    public StationDTO(long id, String address, String name, Set<LineDTO> lines,float lat, float lng, boolean active) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.lines = lines;
        this.active = active;
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "StationDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
