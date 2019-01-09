package com.kits.project.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class LineStationsOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    private Line line;

    @ManyToOne
    private Station station;

    @Column(name = "station_order")
    private int stationOrder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station nextStation) {
        this.station = nextStation;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Line getLine() {
        return line;
    }

    public int getOrder() {
        return stationOrder;
    }

    public void setOrder(int order) {
        this.stationOrder = order;
    }

    @Override
    public String toString() {
        return "LineStationsOrder{" +
                "id=" + id +
                ", stationOrder=" + stationOrder +
                '}';
    }
}
