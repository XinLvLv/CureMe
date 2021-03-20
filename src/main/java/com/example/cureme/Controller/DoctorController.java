package com.example.cureme.Controller;

import com.example.cureme.Entity.Doctor;
import com.example.cureme.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping(path = "/sign-up-submit-form")
    public String signUpSubmitForm(@RequestParam String firstName, String lastName, String briefIntroduction, String specialization, String phoneNumber,
                                   String email, Date startOfCareer, Date dateOfBirth ){
        doctorService.add(firstName, lastName, briefIntroduction, specialization, phoneNumber,
                email, startOfCareer, dateOfBirth);
        return "redirect:";
    }
}
