package com.example.cureme.Service;

import com.example.cureme.Entity.FamilyMember;
import com.example.cureme.Entity.Patient;
import com.example.cureme.Repository.FamilyMemberRepository;
import com.example.cureme.Repository.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class FamilyMemberService {
    @Autowired
    private PatientsService patientsService;
    @Autowired
    private FamilyMemberRepository familyMemberRepository;
    @Autowired
    private MailService mailService;

    public FamilyMember add(Integer patient_id, String firstName, String lastName, String email){
        FamilyMember familyMember = new FamilyMember();
        familyMember.setEmail(email);
        familyMember.setFirstName(firstName);
        familyMember.setLastName(lastName);
        familyMember.setPassword();
        Patient patient = patientsService.selectPatient(patient_id).get(0);
        familyMember.getPatients().add(patient);
        familyMemberRepository.save(familyMember);
        String title = "Welcome to CareMe";
        String detail = "Use your username and password to sign in.\n";
        detail += "username : ";
        detail += email;
        detail += "\npassword : ";
        detail += patient.getPassword();
        detail += "\nPlease log in through : http://localhost:8080/family-member-login";
        mailService.sendSimpleMail(email, title, detail);
        return familyMember;
    }


    public Set<FamilyMember> myFamilyMember(Integer currentUserId) {
        Patient patient = patientsService.selectPatient(currentUserId).get(0);
        return patient.getFamilyMembers();
    }

    public List<FamilyMember> selectFamilyMemberByUserName(String userName) {
        return familyMemberRepository.selectFamilyMemberByUserName(userName);
    }

    public List<Patient> viewPatients(Integer currentUserId) {
        Set<Patient> patientsSet = familyMemberRepository.selectFamilyMemberById(currentUserId).get(0).getPatients();
        List<Patient> patientsList = new ArrayList<>(patientsSet);
        return patientsList;
    }
}
