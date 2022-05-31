package com.niit.boardapplication.repository;

import com.niit.boardapplication.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface KanBanRepository extends MongoRepository<Task,Integer> {

    //custom abstract method declared to filter the tasks based on userId
    public abstract List<Task> findAllByUserId(int userId);

}
