package com.kits.project.DTOs;

import com.kits.project.model.User;

import java.io.Serializable;

public class ProfileDTO implements Serializable {
    public Long id;

    public String username;

    public String firstName;

    public String lastName;

    public String email;

    public int ticketsBought;

    public ProfileDTO() {}

    public ProfileDTO(User account) {
        this.id = account.getId();
        this.email = account.getEmail();
        this.username = account.getUsername();
        this.firstName = account.getFirstName();
        this.lastName = account.getLastName();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTicketsBought() {
        return ticketsBought;
    }

    public void setTicketsBought(int ticketsBought) {
        this.ticketsBought = ticketsBought;
    }
}
