package com.example.cureme.Controller;

import com.example.cureme.Entity.Doctor;
import com.example.cureme.Entity.Patient;
import com.example.cureme.Service.DoctorService;
import com.example.cureme.Service.PatientsService;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping
public class CureMeController {
    @Autowired
    DoctorService doctorService;
    @Autowired
    PatientsService patientsService;
    @GetMapping(path="/doctor-login")

    private String doctorLogin() { return "DoctorLogin"; }

    @GetMapping(path="/patient-login")
    private String patientLogin() { return "PatientLogin"; }

    @GetMapping(path = "/doctor-home")
    private String doctorHome(Model model) {
        HttpSession session = getRequest().getSession();
        Integer currentUserId = (Integer) session.getAttribute("currentUserId");
        Doctor doctor = doctorService.currentDoctor(currentUserId).get(0);
        model.addAttribute("currentUser", doctor);
        return "DoctorHome"; }

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
