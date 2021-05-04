package com.example.cureme.Service;

import com.example.cureme.Entity.Patient;
import com.example.cureme.Entity.VitalSigns;
import com.example.cureme.Repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.Math;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class ReadingService {
    @Autowired
    private ReadingRepository readingRepository;
    @Autowired
    private PatientsService patientsService;

    public List<VitalSigns> getPatientPulse(Integer patientId) {
        return readingRepository.getPatientPulse(patientId);
    }

    public void add(Integer breathing_rate, Integer systolic_bp, Integer pulse, Integer spo2, Integer diastolic_BP, Integer eye, Integer verbal, Integer motor, Integer patient_id) {
        VitalSigns vitalSigns = new VitalSigns();
        vitalSigns.setBreathingRate(breathing_rate);
        vitalSigns.setSystolicBP(systolic_bp);
        vitalSigns.setPulse(pulse);
        vitalSigns.setSpo2(spo2);
        vitalSigns.setDiastolicBP(diastolic_BP);
        Integer gcs_score = eye + verbal + motor;
        vitalSigns.setGCS(gcs_score);
        Patient patient = patientsService.selectPatient(patient_id).get(0);
        vitalSigns.setPatient(patient);
        Date now = new Date();
        vitalSigns.setDatetime(now);
        readingRepository.save(vitalSigns);
    }
}
