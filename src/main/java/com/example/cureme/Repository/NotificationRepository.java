package com.example.cureme.Repository;

import com.example.cureme.Entity.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends CrudRepository<Notification,Integer> {
}
