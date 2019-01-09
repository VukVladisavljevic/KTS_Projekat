package com.kits.project.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kits.project.DTOs.UserDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lupus on 10/30/2018.
 */
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    @Version
    private int version;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(nullable = false, columnDefinition = "BOOL DEFAULT FALSE")
    private boolean confirmed;

    @Column(nullable = false)
    private String email;

    @Column
    private String activationId;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JsonManagedReference
    private List<AccountAuthority> accountAuthorities;

    //------------------------------

    public User() {
        this.accountAuthorities = new ArrayList<>();
        this.confirmed = false;
    }

    public User(String username, String password, int version, String firstName, String lastName, boolean confirmed, String email, String activationId) {
        this.username = username;
        this.version = version;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.confirmed = confirmed;
        this.email = email;
        this.activationId = activationId;
    }

    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(UserDTO userDTO) {
        this.username = userDTO.username;
        this.password = userDTO.password;
        this.firstName = userDTO.firstName;
        this.lastName = userDTO.lastName;
    }

    public User(String username, String password) {
        this.username = username;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);
        this.accountAuthorities = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getVersion() { return version; }

    public void setVersion(int version) { this.version = version; }

    public boolean isConfirmed() { return confirmed; }

    public void setConfirmed(boolean confirmed) { this.confirmed = confirmed; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getActivationId() { return activationId; }

    public void setActivationId(String activationId) { this.activationId = activationId; }

    public List<AccountAuthority> getAccountAuthorities() { return accountAuthorities; }

    public void setAccountAuthorities(List<AccountAuthority> accountAuthorities) { this.accountAuthorities = accountAuthorities; }
}
