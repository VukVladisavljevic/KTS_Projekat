package com.kits.project.DTOs;

import java.io.Serializable;

public class ChangeProfileDTO implements Serializable {
    private String firstName;
    private String lastName;
    private String password;
    private String newPassword;
    private String email;

    public ChangeProfileDTO() {}

    public ChangeProfileDTO(String firstName, String lastName, String password, String newPassword, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.newPassword = newPassword;
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
