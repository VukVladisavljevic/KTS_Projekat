package com.kits.project.services.implementations;

import com.kits.project.DTOs.UserDTO;
import com.kits.project.model.User;
import com.kits.project.repositories.UserRepository;
import com.kits.project.services.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImplementation implements UserServiceInterface {


    @Autowired
    private UserRepository userRepository;


    @Override
    public User login(UserDTO userDTO) {

        User user = userRepository.findByUsernameAndPassword(userDTO.username,userDTO.password);

        if(user != null){
            if(user.getPassword().equals(userDTO.password)){
                return user;
            }
        }
        return null;
    }


    @Override
    public User register(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.username);

        if(user == null){
            User retval = userRepository.save(new User(userDTO));
            return retval;
        }
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


}
