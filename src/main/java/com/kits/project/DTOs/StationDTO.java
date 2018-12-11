package com.kits.project.DTOs;

import java.io.Serializable;
import java.util.Set;

public class StationDTO implements Serializable {

    public String address;

    public String name;

    public Set<LineDTO> lines;

    public boolean active;

    public StationDTO() {}

    public StationDTO(String address, String name, Set<LineDTO> lines, boolean active) {
        this.address = address;
        this.name = name;
        this.lines = lines;
        this.active = active;
    }
}
