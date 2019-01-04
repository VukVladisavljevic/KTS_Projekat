package com.kits.project.DTOs;

import com.kits.project.model.Line;
import com.kits.project.model.Station;

import java.util.ArrayList;

public class MapLinesDTO {
    public ArrayList<Station> waypoints;
    public Station source;
    public Station destination;
    public Line line;

    public MapLinesDTO(){}

    public MapLinesDTO(Station source, Station destination, Line line){
        this.source = source;
        this.destination = destination;
        this.line = line;
    }
}
