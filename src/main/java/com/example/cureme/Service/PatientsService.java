package com.example.cureme.Service;

import com.example.cureme.Entity.Patient;
import com.example.cureme.Repository.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class PatientsService {
    @Autowired
    private PatientsRepository patientsRepository;

    public Patient add ( Date dateOfBirth, String firstName, String lastName, String gender, String address,
                         String phoneNumber, String email, String disease){
        Patient patient = new Patient();
        patient.setDateOfBirth(dateOfBirth);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setGender(gender);
        patient.setAddress(address);
        patient.setPhoneNumber(phoneNumber);
        patient.setEmail(email);
        patient.setDisease(disease);
        patient.setPassword();
        patient.setStatus("Pending");
        patient.setSchedule(3);
        patientsRepository.save(patient);
        return patient;
    }

    public List<Patient> patientList(){return patientsRepository.listAll();}

    public List<Patient> selectPatient(Integer patientId) {
        return patientsRepository.selectPatients(patientId);
    }

    public void changeSchedule(Integer schedule, Integer patientId){
        Patient patient=patientsRepository.selectPatients(patientId).get(0);
        patient.setSchedule(schedule);
    }

    public List<Patient> currentUserPatients(Integer doctorId){
        return patientsRepository.currentUserPatients(doctorId);
    }
}
