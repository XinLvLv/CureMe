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
@RequestMapping
public class PatientsController {
    @Autowired
    private PatientsService patientsService;

    @GetMapping(path = "/add-patient")
    private String AddPatient(){return "AddPatient";}

    @GetMapping(path = "/patient-list")
    private String PatientList(){return "PatientList";}

}
