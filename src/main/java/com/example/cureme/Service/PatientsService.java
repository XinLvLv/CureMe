package com.example.cureme.Service;

import com.example.cureme.Entity.Doctor;
import com.example.cureme.Entity.Patient;
import com.example.cureme.Repository.DoctorRepository;
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
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private MailService mailService;
    public Patient add ( Integer currentUserId, Date dateOfBirth, String firstName, String lastName, String gender, String address,
                         String phoneNumber, String email, String disease){
        Patient patient = new Patient();
        Doctor doctor = doctorService.currentDoctor(currentUserId).get(0);
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
        patient.setDoctor(doctor);
        patientsRepository.save(patient);
        String title = "Welcome to CareMe";
        String detail = "Use your username and password to sign in.\n";
        detail += "username : ";
        detail += email;
        detail += "\npassword : ";
        detail += patient.getPassword();
        detail += "\nPlease log in through : http://localhost:8080/patient-login";
        mailService.sendSimpleMail(email, title, detail);
        return patient;
    }

    public List<Patient> patientList(Integer currentUserId){return patientsRepository.listAll(currentUserId);}

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

    public List<Patient> selectPatientByUserName(String userName) {
        return patientsRepository.selectPatientByUserName(userName);
    }

    public void changeStatus(String userName) {
        Patient patient=patientsRepository.selectPatientByUserName(userName).get(0);
        patient.setStatus("Active");
    }
}
