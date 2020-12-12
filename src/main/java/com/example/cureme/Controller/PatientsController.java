package com.example.cureme.Controller;
import com.example.cureme.Entity.Patients;
import com.example.cureme.Service.PatientsService;
import com.example.cureme.Utility.SendMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


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
                      String Mail, String Disease, String DOB, String Gender, Integer Age, Model model) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Patients patient = patientsService.add(FirstName, LastName, Address, PhoneNumber, Mail, Disease,Age,
                sdf.parse(DOB), Gender);
        model.addAttribute("patient", patient);
        int resultCode = SendMessageUtil.send("xlll","d41d8cd98f00b204e980",PhoneNumber,"Welcome to CareMe. Here we help to secure your health. username:"+patient.getFirstName()+"; password:"+patient.getPassword());
        System.out.println(SendMessageUtil.getMessage(resultCode));
        return "patientInfo";
    }
}
