package com.kits.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kits.project.DTOs.StationDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lupus on 10/30/2018.
 */

@Entity
@Table(name="STATIONS")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private float lat;

    @Column(nullable = false)
    private float lng;

    @Column(nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "idline", nullable = false)
    @JsonIgnoreProperties("stations")
    private Set<Line> lines;

    @Column
    private boolean active;

    public Station() { }

    public Station(long id) {
        this.id = id;
        this.lines = new HashSet<>();
    }

    public Station(String address, String name, Set<Line> lines,float lat, float lng, boolean active) {
        this.address = address;
        this.name = name;
        this.lines = lines;
        this.active = active;
        this.lat = lat;
        this.lng = lng;
    }

    public Station(StationDTO stationDTO) {
        this.address = stationDTO.address;
        this.name = stationDTO.name;
        this.lines = new HashSet<>();
        this.active = stationDTO.active;
        this.lat = stationDTO.lat;
        this.lng = stationDTO.lng;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Line> getLines() {
        return lines;
    }

    public void setLines(Set<Line> lines) {
        this.lines = lines;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public float getLat() {
        return this.lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return this.lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Station{" +
                "name='" + name + '\'' +
                ", lines=" + lines +
                '}';
    }
}
