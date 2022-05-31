package com.niit.boardapplication.service;


import com.niit.boardapplication.exception.TaskAlreadyExistsException;
import com.niit.boardapplication.exception.TaskNotFoundException;
import com.niit.boardapplication.model.Task;
import org.springframework.messaging.MessagingException;


import java.util.List;

public interface KanBanService {

    //abstract method to add Task
    public abstract Task addUserTask(Task task) throws TaskAlreadyExistsException, MessagingException;

    //abstract method to update Task
    public abstract  Task updateUserTask(Task task) throws TaskNotFoundException;

    //abstract method to get all based on user
    public abstract List<Task> showAllTasksByUserId(int userId) throws TaskNotFoundException;

    //abstract method to delete the task
    public abstract void removeTask(Task task) throws TaskNotFoundException;

    //abstract method to get task by taskId
    public abstract Task getTaskById(int taskId) throws TaskNotFoundException;

}
