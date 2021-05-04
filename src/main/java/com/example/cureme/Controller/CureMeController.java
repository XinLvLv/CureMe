package com.example.cureme.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class CureMeController {
    @GetMapping(path="/doctor-login")
    private String doctorLogin() { return "DoctorLogin"; }

    @GetMapping(path="/patient-login")
    private String patientLogin() { return "PatientLogin"; }

    @GetMapping(path = "/doctor-home")
    private String doctorHome() {return "DoctorHome"; }

    @GetMapping(path = "/patient-home")
    private String patientHome() {return "PatientHome";}
}
