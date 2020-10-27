package com.example.cureme.Service;

import com.example.cureme.Entity.Patients;
import com.example.cureme.Repository.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class PatientsService {
    @Autowired
    private PatientsRepository patientsRepository;

    public boolean add ( String FirstName, String LastName, String Address, String PhoneNumber,
    String City, String Mail, String Disease){
        Patients patient = new Patients();
        patient.setFirstName(FirstName);
        patient.setLastName(LastName);
        patient.setAddress(Address);
        patient.setPhoneNumber(PhoneNumber);
        patient.setCity(City);
        patient.setMail(Mail);
        patient.setDisease(Disease);
        patientsRepository.save(patient);
        return true;
    }
}
