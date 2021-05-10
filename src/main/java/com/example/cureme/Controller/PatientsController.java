package com.example.cureme.Controller;
import com.example.cureme.Entity.Patient;
import com.example.cureme.Service.PatientsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping
public class PatientsController {
    @Autowired
    private PatientsService patientsService;

    @GetMapping(path="/patient-login")
    private String patientLogin() { return "PatientLogin"; }

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
                if(!patients.get(0).getStatus().equals("Active"))
                    patientsService.changeStatus(userName);
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

    @GetMapping(path = "/patient-home")
    private String patientHome(Model model) {
        HttpSession session = getRequest().getSession();
        Integer currentUserId = (Integer) session.getAttribute("currentUserId");
        List<Patient> selectedPatients= patientsService.selectPatient(currentUserId);
        model.addAttribute("selectedPatient", selectedPatients.get(0));
        return "PatientHome";}

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
}
