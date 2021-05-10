package com.example.cureme.Repository;

import com.example.cureme.Entity.FamilyMember;
import com.example.cureme.Entity.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FamilyMemberRepository extends CrudRepository<FamilyMember,Integer> {
    @Query(value = "select * from family_member where email = ?", nativeQuery = true )
    List<FamilyMember> selectFamilyMemberByUserName(String userName);

    @Query(value = "select * from family_member where family_member_id = ?", nativeQuery = true )
    List<FamilyMember> selectFamilyMemberById(Integer currentUserId);
}
