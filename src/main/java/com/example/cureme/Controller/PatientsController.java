package com.example.cureme.Controller;
import com.example.cureme.Service.PatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


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
                                     String City, String Mail, String Disease)
    {
        patientsService.add(FirstName, LastName, Address, PhoneNumber, City, Mail, Disease);
        return "redirect:/";
    }
}
