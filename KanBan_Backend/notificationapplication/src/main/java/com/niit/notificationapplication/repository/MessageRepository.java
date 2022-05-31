package com.niit.notificationapplication.repository;


import com.niit.notificationapplication.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<Task, Integer> {
}
