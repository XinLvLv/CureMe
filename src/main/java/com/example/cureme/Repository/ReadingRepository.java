package com.example.cureme.Repository;

import com.example.cureme.Entity.VitalSigns;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReadingRepository extends CrudRepository<VitalSigns,Integer> {

    @Query(value = "select * from vital_signs where patient_id = ?", nativeQuery = true)
    List<VitalSigns> getPatientPulse(Integer patientId);
}
