package com.example.cureme.Controller;

import com.example.cureme.Entity.Notification;
import com.example.cureme.Entity.Patient;
import com.example.cureme.Service.FamilyMemberService;
import com.example.cureme.Service.NotificationService;
import com.example.cureme.Service.PatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
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
@RequestMapping(path = "/notification")
public class NotificationController {
    @Autowired
    private PatientsService patientsService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private FamilyMemberService familyMemberService;

    @GetMapping(path = "/add-notification")
    private String addNotice(Model model){
        HttpSession session = getRequest().getSession();
        Integer currentUserId = (Integer) session.getAttribute("currentUserId");
        List<Patient> patients = patientsService.patientList(currentUserId);
        model.addAttribute("patients", patients);
        return "PatientNotice";
    }

    @PostMapping(path = "/add-notification-submit-form")
    public String addNotificationSubmitForm(@RequestParam Integer patientId, String title, String detail){
        notificationService.add(patientId,title,detail);
        return "redirect:/notification/";
    }

    @GetMapping(path = "/")
    private String noticeBoard(Model model){
        HttpSession session = getRequest().getSession();
        Integer currentUserId = (Integer) session.getAttribute("currentUserId");
        List<Patient> patients = patientsService.patientList(currentUserId);
        model.addAttribute("patients", patients);
        return "NoticeBoard";}

    @GetMapping(path = "/{patientId}")
    private String patientNotification(Model model,@PathVariable Integer patientId){
        HttpSession session = getRequest().getSession();
        Integer currentUserId = (Integer) session.getAttribute("currentUserId");
        List<Patient> patients = patientsService.patientList(currentUserId);
        model.addAttribute("patients", patients);
        List<Notification> notifications = notificationService.selectByPatientId(patientId);
        model.addAttribute("notifications", notifications);
        List<Patient> selectedPatients= patientsService.selectPatient(patientId);
        model.addAttribute("selectedPatient", selectedPatients.get(0));
        int numberOfNotification = notifications.size();
        model.addAttribute("numbers", numberOfNotification);
        return "NoticeBoard";
    }

    @GetMapping(path = "/my-notification")
    private String myNotification(Model model){
        HttpSession session = getRequest().getSession();
        Integer currentUserId = (Integer) session.getAttribute("currentUserId");
        List<Notification> notifications = notificationService.selectByPatientId(currentUserId);
        model.addAttribute("notifications", notifications);
        return "PatientNotification";
    }

    @GetMapping(path = "/view-notification-by-fm")
    private String viewNoticeByFM(Model model){
        HttpSession session = getRequest().getSession();
        Integer currentUserId = (Integer) session.getAttribute("currentUserId");
        List<Patient> patients = familyMemberService.viewPatients(currentUserId);
        model.addAttribute("patients", patients);
        return "NoticeBoardByFM";
    }

    @GetMapping(path = "/view-notification-by-fm/{patientId}")
    private String patientNotificationByFM(Model model,@PathVariable Integer patientId){
        HttpSession session = getRequest().getSession();
        Integer currentUserId = (Integer) session.getAttribute("currentUserId");
        List<Patient> patients = familyMemberService.viewPatients(currentUserId);
        model.addAttribute("patients", patients);
        List<Notification> notifications = notificationService.selectByPatientId(patientId);
        model.addAttribute("notifications", notifications);
        List<Patient> selectedPatients= patientsService.selectPatient(patientId);
        model.addAttribute("selectedPatient", selectedPatients.get(0));
        int numberOfNotification = notifications.size();
        model.addAttribute("numbers", numberOfNotification);
        return "NoticeBoardByFM";
    }

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }


}
