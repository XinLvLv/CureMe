package com.example.cureme.Repository;

import com.example.cureme.Entity.Doctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor,Integer> {

    @Query(value = "select * from doctor where email = ?", nativeQuery = true)
    List<Doctor> selectDoctorByEmail(String email);

    @Query(value = "select * from doctor where doctor_id = ?", nativeQuery = true)
    List<Doctor> selectDoctorById(Integer id);
}
