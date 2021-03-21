package com.example.cureme.Service;

import com.example.cureme.Entity.Doctor;
import com.example.cureme.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor add(String firstName, String lastName, String briefIntroduction, String specialization, String phoneNumber,
                      String email, Date startOfCareer, Date dateOfBirth){
        Doctor doctor = new Doctor();
        doctor.setFirstName(firstName);
        doctor.setLastName(lastName);
        doctor.setBriefIntroduction(briefIntroduction);
        doctor.setSpecialization(specialization);
        doctor.setPhoneNumber(phoneNumber);
        doctor.setEmail(email);
        doctor.setStartOfCareer(startOfCareer);
        doctor.setDateOfBirth(dateOfBirth);
        doctorRepository.save(doctor);
        return doctor;
    }

    public List<Doctor> currentDoctor(String email){
        return doctorRepository.selectDoctorByEmail(email);
    }


}
