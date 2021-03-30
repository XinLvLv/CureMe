package com.example.cureme.Controller;

import com.example.cureme.Entity.Patient;
import com.example.cureme.Service.PatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(path = "/reading")
public class ReadingController {
    @Autowired
    private PatientsService patientsService;

    @GetMapping(path="/")
    private String schedule(Model model){
        HttpSession session = getRequest().getSession();
        Integer currentUserId = (Integer) session.getAttribute("currentUserId");
        List<Patient> patients = patientsService.patientList(currentUserId);
        model.addAttribute("patients", patients);
        return "Readings";
    }

    @GetMapping(path = "/{patientId}")
    private String patientReading(Model model,@PathVariable Integer patientId){
        HttpSession session = getRequest().getSession();
        Integer currentUserId = (Integer) session.getAttribute("currentUserId");
        List<Patient> patients = patientsService.patientList(currentUserId);
        List<Patient> selectedPatients= patientsService.selectPatient(patientId);
        model.addAttribute("patients", patients);
        model.addAttribute("selectedPatient", selectedPatients.get(0));
        model.addAttribute("selectedPatientId", selectedPatients.get(0).getPatientId());
        return "Readings";
    }
    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
}
