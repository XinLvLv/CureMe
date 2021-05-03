package com.example.cureme.Service;

import com.example.cureme.Entity.Notification;
import com.example.cureme.Entity.Patient;
import com.example.cureme.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.*;
import java.util.Date;

@Transactional
@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    PatientsService patientsService;

    public Notification add (Integer patientId, String title, String detail){
        Notification notification = new Notification();
        notification.setTitle(title);
        notification.setDetail(detail);
        Patient patient = patientsService.selectPatient(patientId).get(0);
        notification.setPatient(patient);
        Date now = new Date();
        notification.setDate(now);
        notificationRepository.save(notification);
        return notification;
    }
}
