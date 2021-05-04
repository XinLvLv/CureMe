package com.example.cureme.Service;

import com.example.cureme.Entity.FamilyMember;
import com.example.cureme.Entity.Patient;
import com.example.cureme.Repository.FamilyMemberRepository;
import com.example.cureme.Repository.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class FamilyMemberService {
    @Autowired
    private PatientsService patientsService;
    @Autowired
    private FamilyMemberRepository familyMemberRepository;

    public FamilyMember add(Integer patient_id, String firstName, String lastName, String email, String relationship){
        FamilyMember familyMember = new FamilyMember();
        familyMember.setEmail(email);
        familyMember.setFirstName(firstName);
        familyMember.setLastName(lastName);
        familyMember.setRelationship(relationship);
        familyMember.setPassword();
        Patient patient = patientsService.selectPatient(patient_id).get(0);
        familyMember.setPatient(patient);
        familyMemberRepository.save(familyMember);
        return familyMember;
    }

    public List<FamilyMember> selectByPatientId(Integer currentUserId) {
        return familyMemberRepository.selectByPatientId(currentUserId);
    }
}
