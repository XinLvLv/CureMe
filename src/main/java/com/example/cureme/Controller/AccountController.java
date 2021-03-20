package com.example.cureme.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AccountController {
    @GetMapping(path = "/account")
    private String account(){return "Account";}

    @GetMapping(path = "/sign-up")
    private String signUp(){return "SignUp";}
}
