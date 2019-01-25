package com.kits.project.repositories;

import com.kits.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    User findByActivationId(String activationId);

    ArrayList<User> findAll();
}
