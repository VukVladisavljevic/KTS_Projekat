package com.kits.project.DTOs;

import java.io.Serializable;

public class PassengerDTO implements Serializable {

    public String name;

    public PassengerDTO() {}


    public PassengerDTO(String name) {
        this.name = name;
    }
}
