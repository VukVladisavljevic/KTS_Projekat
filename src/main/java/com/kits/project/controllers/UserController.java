package com.kits.project.controllers;

import com.kits.project.DTOs.UserDTO;
import com.kits.project.model.User;
import com.kits.project.services.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api")
@RestController
public class UserController {

    @Autowired
    private UserServiceInterface userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User login(@RequestBody UserDTO userDTO){
        return userService.login(userDTO);
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User register(@RequestBody UserDTO userDTO){
        return userService.register(userDTO);
    }

//    @RequestMapping(value = "/logout",method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void logout(@RequestBody UserDTO u){
//        return authService.logout(u);
//    }
}