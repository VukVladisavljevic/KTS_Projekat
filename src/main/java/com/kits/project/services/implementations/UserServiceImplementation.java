package com.kits.project.services.implementations;

import com.kits.project.DTOs.UserDTO;
import com.kits.project.model.User;
import com.kits.project.services.interfaces.UserServiceInterface;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImplementation implements UserServiceInterface {

    @Override
    public User register(UserDTO userDTO) {
        return null;
    }

    @Override
    public User updateUser(Long userID, UserDTO userDTO) {
        return null;
    }

    @Override
    public boolean archiveUser(Long userID) {
        return false;
    }

    @Override
    public User login() {
        return null;
    }
}
