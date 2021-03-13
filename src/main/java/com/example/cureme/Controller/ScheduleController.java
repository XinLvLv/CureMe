package com.example.cureme.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ScheduleController {
    @GetMapping(path="/schedule")
    private String NewSchedule(){return "Schedule";}
}
