package com.example.cureme.Controller;

import com.example.cureme.Entity.Notification;
import com.example.cureme.Entity.Patient;
import com.example.cureme.Entity.VitalSigns;
import com.example.cureme.Repository.PatientsRepository;
import com.example.cureme.Repository.ReadingRepository;
import com.example.cureme.Service.FamilyMemberService;
import com.example.cureme.Service.PatientsService;
import com.example.cureme.Service.ReadingService;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/reading")
public class ReadingController {
    @Autowired
    private PatientsService patientsService;
    @Autowired
    private ReadingService readingService;
    @Autowired
    private FamilyMemberService familyMemberService;
    @Autowired
    private ReadingRepository readingRepository;

    @GetMapping(path="/")
    private String reading(Model model){
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

    //add some data to the database
//    @GetMapping(path = "/db")
//    private String add() throws ParseException {
//        Patient patient = patientsService.selectPatient(10).get(0);
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = dateFormat.parse("2021-03-01 00:00:00");
//        for(int i = 0; i < 100; i++){
//            VitalSigns vitalSigns= new VitalSigns();
//            date = new Date(date.getTime()+ 6*60*60*1000);
//            vitalSigns.setDatetime(date);
//            vitalSigns.setBreathingRate((Integer)10+ (int)(Math.random() * (31)));
//            vitalSigns.setPulse((Integer)40+ (int)(Math.random() * (81)));
//            vitalSigns.setDiastolicBP((Integer)30 + (int)(Math.random() * (40)));
//            vitalSigns.setSystolicBP((Integer)60+ (int)(Math.random() * (60)));
//            vitalSigns.setSpo2((Integer)70+ (int)(Math.random() * (25)));
//            vitalSigns.setGCS((Integer)0+ (int)(Math.random() * (15)));
//            vitalSigns.setPatient(patient);
//            readingRepository.save(vitalSigns);
//        }
//        return "Schedule";
//    }

    //get data from json
    @GetMapping(path = "/data/{patientId}")
    public @ResponseBody List<VitalSigns> pulse(@PathVariable(name = "patientId") Integer patientId){
        return readingService.getPatientPulse(patientId);
    }

    @GetMapping(path = "/submit-vital-signs")
    private String submitVitalSigns(Model model) {
        HttpSession session = getRequest().getSession();
        Integer currentUserId = (Integer) session.getAttribute("currentUserId");
        String diseases = patientsService.selectPatient(currentUserId).get(0).getDisease();
        model.addAttribute("diseases", diseases);
        return "SubmitVitalSigns";
    }


    @PostMapping(path = "/vital-signs-submit-form")
    public String addNotificationSubmitForm(@RequestParam Integer breathing_rate, Integer systolic_BP, Integer pulse,
                                            Integer spo2, Integer diastolic_BP, Integer eye, Integer verbal, Integer motor){
        HttpSession session = getRequest().getSession();
        Integer currentUserId = (Integer) session.getAttribute("currentUserId");
        readingService.add(breathing_rate, systolic_BP, pulse,spo2, diastolic_BP, eye, verbal, motor, currentUserId);
        return "redirect:/reading/submit-vital-signs";
    }

    @GetMapping(path = "/my-vital-signs")
    private String myVitalSigns(Model model) {
        HttpSession session = getRequest().getSession();
        Integer currentUserId = (Integer) session.getAttribute("currentUserId");
        Patient selectedPatient = patientsService.selectPatient(currentUserId).get(0);
        model.addAttribute("selectedPatient", selectedPatient);
        return "PatientVitalSigns";
    }

    @GetMapping(path = "/submit-vital-signs-by-fm")
    private String submitVitalSignsByFM(Model model) {
        HttpSession session = getRequest().getSession();
        Integer currentUserId = (Integer) session.getAttribute("currentUserId");
        List<Patient> patients = familyMemberService.viewPatients(currentUserId);
        model.addAttribute("patients", patients);
        return "SubmitVitalSignsByFM";
    }

    @GetMapping(path = "/submit-vital-signs-by-fm/{patientId}")
    private String submitVitalSignsOfPatientByFM(Model model, @PathVariable Integer patientId) {
        HttpSession session = getRequest().getSession();
        Integer currentUserId = (Integer) session.getAttribute("currentUserId");
        List<Patient> patients = familyMemberService.viewPatients(currentUserId);
        model.addAttribute("patients", patients);
        String diseases = patientsService.selectPatient(patientId).get(0).getDisease();
        model.addAttribute("diseases", diseases);
        List<Patient> selectedPatients= patientsService.selectPatient(patientId);
        model.addAttribute("selectedPatient", selectedPatients.get(0));
        return "SubmitVitalSignsByFM";
    }

    @PostMapping(path = "/vital-signs-submit-form/{patient_id}")
    private String addVitalSignsSubmitFormById(@RequestParam Integer breathing_rate, Integer systolic_BP, Integer pulse,
                                                 Integer spo2, Integer diastolic_BP, Integer eye, Integer verbal, Integer motor,
                                                 @PathVariable Integer patient_id){
        readingService.add(breathing_rate, systolic_BP, pulse,spo2, diastolic_BP, eye, verbal, motor, patient_id);
        return "redirect:/reading/submit-vital-signs-by-fm";
    }

    @GetMapping(path="/view-vital-sign-by-fm")
    private String viewReadingByFM(Model model){
        HttpSession session = getRequest().getSession();
        Integer currentUserId = (Integer) session.getAttribute("currentUserId");
        List<Patient> patients = familyMemberService.viewPatients(currentUserId);
        model.addAttribute("patients", patients);
        return "ViewVitalSignsByFM";
    }

    @GetMapping(path = "/view-vital-sign-by-fm/{patientId}")
    private String patientReadingByFM(Model model,@PathVariable Integer patientId){
        HttpSession session = getRequest().getSession();
        Integer currentUserId = (Integer) session.getAttribute("currentUserId");
        List<Patient> patients = familyMemberService.viewPatients(currentUserId);
        model.addAttribute("patients", patients);
        List<Patient> selectedPatients= patientsService.selectPatient(patientId);
        model.addAttribute("selectedPatient", selectedPatients.get(0));
        model.addAttribute("selectedPatientId", selectedPatients.get(0).getPatientId());
        return "ViewVitalSignsByFM";
    }

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
}

