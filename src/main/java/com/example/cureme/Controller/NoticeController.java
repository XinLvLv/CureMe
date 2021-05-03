package com.example.cureme.Controller;

import com.example.cureme.Entity.Patient;
import com.example.cureme.Service.NotificationService;
import com.example.cureme.Service.PatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping
public class NoticeController {
    @Autowired
    private PatientsService patientsService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping(path = "/add-notice")
    private String addNotice(Model model){
        HttpSession session = getRequest().getSession();
        Integer currentUserId = (Integer) session.getAttribute("currentUserId");
        List<Patient> patients = patientsService.patientList(currentUserId);
        model.addAttribute("patients", patients);
        return "PatientNotice";
    }

    @PostMapping(path = "/add-notification-submit-form")
    public String addPatientSubmitForm(@RequestParam Integer patientId, String title, String detail){
        notificationService.add(patientId,title,detail);
        return "redirect:/notice-board";
    }

    @GetMapping(path = "/notice-board")
    private String noticeBoard(){return "NoticeBoard";}

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }


}
