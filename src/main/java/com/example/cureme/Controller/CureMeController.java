package com.example.cureme.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class CureMeController {
    @GetMapping(path="/")
    private String login() { return "Login"; }

    @GetMapping(path = "/home")
    private String home() {return "Home"; }
}
