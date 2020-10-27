package com.example.cureme.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.cureme.Entity.Patients;

@Repository
public interface PatientsRepository extends CrudRepository<Patients,Integer>
{

}
