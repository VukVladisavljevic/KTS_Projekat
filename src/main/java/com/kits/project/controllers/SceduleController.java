package com.kits.project.controllers;

import com.kits.project.services.interfaces.ScheduleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api")
@RestController
public class SceduleController {

    @Autowired
    private ScheduleServiceInterface scheduleService;
}
