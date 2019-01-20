package com.kits.project.DTOs;

import com.kits.project.model.Line;
import com.kits.project.model.Station;

public class TransportDTO {

    public long idLine;
    public Station station;
    public long id;

    public TransportDTO(long id, long idLine, Station station) {
        this.id = id;
        this.idLine = idLine;
        this.station = station;
    }
}
