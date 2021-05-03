package com.example.cureme.Repository;

import com.example.cureme.Entity.Doctor;
import com.example.cureme.Entity.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface NotificationRepository extends CrudRepository<Notification,Integer> {
    @Query(value = "select * from notification where patient_id = ?", nativeQuery = true)
    List<Notification> selectByPatientId(Integer patient_id);
}
