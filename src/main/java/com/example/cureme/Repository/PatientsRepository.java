package com.example.cureme.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.cureme.Entity.Patient;

import java.util.List;

@Repository
public interface PatientsRepository extends CrudRepository<Patient,Integer>
{
    @Query(value = "select * from patient where doctor_id = ? and status = 'active'", nativeQuery = true)
    List<Patient> listAll(Integer currentUserId);

    @Query(value = "select * from patient where patient_id = ?", nativeQuery = true)
    List<Patient> selectPatients(Integer patientId);

    @Query(value = "select * from patient where doctor_id = ?", nativeQuery = true)
    List<Patient> currentUserPatients(Integer doctorId);

    @Query(value = "select * from patient where email = ?", nativeQuery = true )
    List<Patient> selectPatientByUserName(String userName);
}
