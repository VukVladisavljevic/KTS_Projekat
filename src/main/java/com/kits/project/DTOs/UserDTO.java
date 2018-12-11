package com.kits.project.DTOs;

import java.io.Serializable;

public class UserDTO implements Serializable {

    public String username;

    public String password;

    public String firstName;

    public String lastName;

    public UserDTO() {}

    public UserDTO(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
