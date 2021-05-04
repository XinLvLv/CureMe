package com.example.cureme.Repository;

import com.example.cureme.Entity.FamilyMember;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FamilyMemberRepository extends CrudRepository<FamilyMember,Integer> {
    @Query(value = "select * from family_member where patient_id = ?", nativeQuery = true)
    List<FamilyMember> selectByPatientId(Integer currentUserId);
}
