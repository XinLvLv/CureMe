package com.example.cureme.Controller;
import com.example.cureme.Entity.Patients;
import com.example.cureme.Service.PatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class PatientsController {
    @Autowired
    private PatientsService patientsService;

    @RequestMapping("/")
    public String addPatient(){
        return "addPatients";
    }

    @PostMapping("/addPatients")
    public String add(@RequestParam String FirstName, String LastName, String Address, String PhoneNumber,
                                     String City, String Mail, String Disease,Model model)
    {
        Patients patient = patientsService.add(FirstName, LastName, Address, PhoneNumber, City, Mail, Disease);
        model.addAttribute("patient", patient);
        return "patientInfo";
    }
}
