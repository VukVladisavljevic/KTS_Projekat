package com.kits.project.controllers;

import com.kits.project.model.Transport;
import com.kits.project.repositories.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@Controller
public class LiveLocationController {

    @Autowired
    private TransportRepository transportRepository;

    @MessageMapping("/location")
    public List<Transport> handle() {
        return transportRepository.findAll();
    }
}
