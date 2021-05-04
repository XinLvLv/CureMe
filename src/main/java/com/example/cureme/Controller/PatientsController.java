package com.example.cureme.Controller;
import com.example.cureme.Entity.Doctor;
import com.example.cureme.Entity.Patient;
import com.example.cureme.Service.PatientsService;
import com.example.cureme.Utility.SendMessageUtil;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping
public class PatientsController {
    @Autowired
    private PatientsService patientsService;

    @PostMapping(path = "/patient-login-submit-form")
    public String logIn(@RequestParam String userName, String password){
        List<Patient> patients = patientsService.selectPatientByUserName(userName);
        //user doesn't exist
        if(patients.size()==0){
            return "redirect:/patient-login";
        }
        else {
            //correct password
            if(patients.get(0).getPassword().equals(password)){
                HttpSession session = getRequest().getSession();
                session.setAttribute("currentUserId", patients.get(0).getPatientId());
                return "redirect:/patient-home";
            }
            //invalid password
            else {
                return "redirect:/patient-login";
            }
        }
    }

    @GetMapping(path = "/add-patient")
    private String addPatient(){return "AddPatient";}

    @GetMapping(path = "/patient-list")
    private String patientList(Model model){
        HttpSession session = getRequest().getSession();
        Integer currentUserId = (Integer) session.getAttribute("currentUserId");
        List<Patient> patients = patientsService.patientList(currentUserId);
        model.addAttribute("patients", patients);
        return "PatientList";
    }

    @PostMapping(path = "/add-patient-submit-form")
    public String addPatientSubmitForm(@RequestParam  Date dateOfBirth, String firstName, String lastName,
                                       String gender, String address, String phoneNumber, String email, String disease){
        HttpSession session = getRequest().getSession();
        Integer currentUserId = (Integer) session.getAttribute("currentUserId");
        patientsService.add(currentUserId, dateOfBirth, firstName, lastName, gender, address,
                phoneNumber, email, disease);
        return "redirect:/doctor-home";
    }

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
}
