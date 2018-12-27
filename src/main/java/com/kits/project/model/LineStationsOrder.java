package com.kits.project.model;

import javax.persistence.*;

@Entity
@Table
public class LineStationsOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Line line;

    @ManyToOne(fetch = FetchType.LAZY)
    private Station station;

    @Column(name = "order")
    private int order;

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
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
