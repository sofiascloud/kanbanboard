package com.niit.boardapplication.tasktest.repository;

import com.niit.boardapplication.model.Task;
import com.niit.boardapplication.repository.KanBanRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class TaskRepositoryTest {

    @Autowired
    private KanBanRepository kanBanRepository;

    //reference creation
    private Task task;

    @BeforeEach
    public void PreTest() {
        task=new Task(111,"Project","Capstone Project (KanBan Board Application)",
                "23/05/2022","High","NIIT",
                "In-Progress",10,"Dan9191@gmail.com");
    }


    @AfterEach
    public void postTest() {

        task= null;
        kanBanRepository.deleteAll();

    }

    @Test
    public void testTaskNotNullAfterInsertingTaskRecord() {
        kanBanRepository.insert(task);
        Task result = kanBanRepository.findById(task.getTaskId()).get();
        assertNotNull(result);
        //comparing expected and actual attribute values
        assertEquals(result.getTaskId(), task.getTaskId());

    }


    @Test
    public void negationTestUpdateTaskStatus(){
        kanBanRepository.insert(task); // has old data
        Task task_test= kanBanRepository.findById(task.getTaskId()).get();
        task_test.setStatus("Completed");
        kanBanRepository.save(task_test);
        assertNotEquals(task.getStatus(),
                kanBanRepository.findById(task.getTaskId()).get().getStatus());

    }

    @Test
    public void testTaskNullAfterRemovingUserRecord() {
        kanBanRepository.insert(task);
        kanBanRepository.deleteById(task.getTaskId());
        assertEquals(Optional.empty(),kanBanRepository.findById(task.getTaskId()));

    }

    @Test
    public void testfindAllByUserId(){
        kanBanRepository.insert(task);
        List<Task> customTask=kanBanRepository.findAllByUserId(task.getUserId());
        assertNotNull(customTask);
        assertEquals(customTask.get(0).getTaskId(),kanBanRepository.findById(task.getTaskId()).get().getTaskId());
    }
}
