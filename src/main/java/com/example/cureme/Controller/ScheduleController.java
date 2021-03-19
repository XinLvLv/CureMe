package com.example.cureme.Controller;

import com.example.cureme.Entity.Patient;
import com.example.cureme.Service.PatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/schedule")
public class ScheduleController {
    @Autowired
    private PatientsService patientsService;

    @GetMapping(path="/")
    private String schedule(Model model){
        List<Patient> patients = patientsService.patientList();
        model.addAttribute("patients", patients);
        return "Schedule";
    }

    @GetMapping(path = "/{patientId}")
    private String patientSchedule(Model model,@PathVariable Integer patientId){
        List<Patient> patients = patientsService.patientList();
        List<Patient> selectedPatients= patientsService.selectPatient(patientId);
        model.addAttribute("patients", patients);
        model.addAttribute("selectedPatient", selectedPatients.get(0));
        model.addAttribute("selectedPatientId", selectedPatients.get(0).getPatientId());
        return "Schedule";
    }

    @PostMapping(path = "/editSchedule/{patientId}")
    private String editPatientSchedule(@RequestParam Integer schedule, @PathVariable Integer patientId){
        patientsService.changeSchedule(schedule, patientId);
        return "redirect:/schedule/{patientId}";
    }
}
