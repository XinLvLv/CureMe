package com.example.cureme.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.cureme.Entity.Patient;

import java.util.List;

@Repository
public interface PatientsRepository extends CrudRepository<Patient,Integer>
{
    @Query(value = "select * from patient", nativeQuery = true)
    List<Patient> listAll();

    @Query(value = "select * from patient where patient_id = ?", nativeQuery = true)
    List<Patient> selectPatients(Integer patientId);

    @Query(value = "select * from patient where doctor_id = ?", nativeQuery = true)
    List<Patient> currentUserPatients(Integer doctorId);
}
