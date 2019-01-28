package com.kits.project.services.implementations;

import com.kits.project.DTOs.UserDTO;
import com.kits.project.exception.BadRequestException;
import com.kits.project.exception.NotFoundException;
import com.kits.project.model.AccountAuthority;
import com.kits.project.model.User;
import com.kits.project.repositories.UserRepository;
import com.kits.project.services.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImplementation implements UserServiceInterface {


    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        User account = this.userRepository.findByUsername(username);
        if(account == null) throw new NotFoundException("Account not found!");
        return account;
    }

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
    @Transactional(readOnly = true)
    public boolean isUsernameTaken(String username) {
        User account = this.userRepository.findByUsername(username);
        return account != null;
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
    @Transactional(readOnly = true)
    public void checkUsername(String username) {
        User account = this.userRepository.findByUsername(username);
        if(account != null) throw new BadRequestException("Username is already used!");
    }

    @Override
    @Transactional(readOnly = false)
    public User save(User account) {
        return this.userRepository.save(account);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByActivationId(String activationId) {
        User account = this.userRepository.findByActivationId(activationId);
        if(account == null) throw new NotFoundException("Account not found!");
        return account;
    }

    @Override
    @Transactional(readOnly = true)
    public ArrayList<User> getAllUsers() {
        ArrayList<User> allUsers = this.userRepository.findAll();
        return allUsers;
    }
}
