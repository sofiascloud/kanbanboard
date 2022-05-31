package com.niit.boardapplication.service;


import com.niit.boardapplication.exception.TaskAlreadyExistsException;
import com.niit.boardapplication.exception.TaskNotFoundException;
import com.niit.boardapplication.model.Task;
import com.niit.boardapplication.rabbitmq.Producer;
import com.niit.boardapplication.rabbitmq.TaskDTO;
import com.niit.boardapplication.repository.KanBanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KanBanServiceImpl implements KanBanService {


    public KanBanServiceImpl() {
    }

    //creating instance
    KanBanRepository kanBanRepository;
    Producer producer;

    //Dependency Injection
    @Autowired
    public KanBanServiceImpl(KanBanRepository kanBanRepository,  Producer producer) {
        this.kanBanRepository = kanBanRepository;
        this.producer=producer;
    }



    //Method to add task
    @Override
    public Task addUserTask(Task task) throws TaskAlreadyExistsException {
      if(kanBanRepository.findById(task.getTaskId()).isEmpty()){

          TaskDTO taskdto=new TaskDTO();
          taskdto.setTaskId(task.getTaskId());
          taskdto.setTaskTitle(task.getTaskTitle());
          taskdto.setTaskDesc(task.getTaskDesc());
          taskdto.setTaskDeadline(task.getTaskDeadline());
          taskdto.setPriority(task.getPriority());
          taskdto.setAssignee(task.getAssignee());
          taskdto.setStatus(task.getStatus());
          taskdto.setUserId(task.getUserId());
          taskdto.setEmailId(task.getEmailId());
          producer.sendMessageToMq(taskdto);
          kanBanRepository.save(task);

          return task;
        }
      else{
          throw new TaskAlreadyExistsException();
      }
    }

    //Method to update task
    @Override
    public Task updateUserTask(Task task) throws TaskNotFoundException {
       if(kanBanRepository.findById(task.getTaskId()).isPresent()){

           TaskDTO taskdto=new TaskDTO();
           taskdto.setTaskId(task.getTaskId());
           taskdto.setTaskTitle(task.getTaskTitle());
           taskdto.setTaskDesc(task.getTaskDesc());
           taskdto.setTaskDeadline(task.getTaskDeadline());
           taskdto.setPriority(task.getPriority());
           taskdto.setAssignee(task.getAssignee());
           taskdto.setStatus(task.getStatus());
           taskdto.setUserId(task.getUserId());
           taskdto.setEmailId(task.getEmailId());
           producer.sendMessageToMq(taskdto);

           kanBanRepository.save(task);

           return task;
       }
       else{
           throw new TaskNotFoundException();
       }
    }

    //Method to get task by userId
    @Override
    public List<Task> showAllTasksByUserId(int userId) throws TaskNotFoundException {
        if(kanBanRepository.findAllByUserId(userId).isEmpty()){
            throw new TaskNotFoundException();
        }
        else{
            return kanBanRepository.findAllByUserId(userId);
        }
    }

    //Method to delete task
    @Override
    public void removeTask(Task task) throws TaskNotFoundException {
        if(kanBanRepository.findById(task.getTaskId()).isPresent()){
            kanBanRepository.deleteById(task.getTaskId());
            System.out.println("Task Deleted");
        }
        else{
            throw new TaskNotFoundException();
        }
    }

    @Override
    public Task getTaskById(int taskId) throws TaskNotFoundException {
        if(kanBanRepository.findById(taskId).isPresent()){
            return kanBanRepository.findById(taskId).get();
        }
        else{
            throw new TaskNotFoundException();
        }
    }
}



