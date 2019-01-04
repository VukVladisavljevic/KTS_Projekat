package com.kits.project.services.interfaces;

import com.kits.project.DTOs.TimeScheduleDTO;
import com.kits.project.DTOs.UserDTO;
import com.kits.project.model.TimeSchedule;
import com.kits.project.model.User;
import com.kits.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface UserServiceInterface {

    User findByUsername(String username);

    boolean isUsernameTaken(String username);

    User register(UserDTO userDTO);

    User updateUser(Long userID, UserDTO userDTO);

    boolean archiveUser(Long userID);

    User login(UserDTO userDTO);

    void checkUsername(String username);

    User save(User account);

    User findByActivationId(String activationId);
}
