package com.example.cureme.Service;

import com.example.cureme.Entity.Patients;
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

    public Patients add (String FirstName, String LastName, String Address, String PhoneNumber,
                         String Mail, String Disease, Integer Age, Date DOB, String Gender){
        Patients patient = new Patients();
        patient.setFirstName(FirstName);
        patient.setLastName(LastName);
        patient.setAddress(Address);
        patient.setPhoneNumber(PhoneNumber);
        patient.setMail(Mail);
        patient.setDisease(Disease);
        patient.setPassword();
        patient.setAge(Age);
        patient.setDOB(DOB);
        patient.setGender(Gender);
        patientsRepository.save(patient);
        return patient;
    }

}
