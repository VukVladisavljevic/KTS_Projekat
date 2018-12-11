package com.kits.project.services.interfaces;

import com.kits.project.DTOs.TimeScheduleDTO;
import com.kits.project.DTOs.UserDTO;
import com.kits.project.model.TimeSchedule;
import com.kits.project.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserServiceInterface {

    public User register(UserDTO userDTO);


    public User updateUser(Long userID, UserDTO userDTO);


    public boolean archiveUser(Long userID);


    public User login();
}
