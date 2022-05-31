package com.niit.boardapplication.controller;


import com.niit.boardapplication.exception.TaskAlreadyExistsException;
import com.niit.boardapplication.exception.TaskNotFoundException;
import com.niit.boardapplication.model.Message;
import com.niit.boardapplication.model.Task;
import com.niit.boardapplication.model.User;
import com.niit.boardapplication.service.KanBanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/task")
public class KanBanController {




    //creating reference
    KanBanServiceImpl kanBanService;
    User user;
    //Constructor DI
    @Autowired
    public KanBanController(KanBanServiceImpl kanBanService) {
        this.kanBanService = kanBanService;

    }

    //-->http://localhost:9898/task/v2
    @PostMapping("/v2")
    public ResponseEntity<?> sendTask(@RequestBody Task task) throws TaskAlreadyExistsException{
        try{
            return new ResponseEntity<>(kanBanService.addUserTask(task),HttpStatus.OK);
        }
        catch(TaskAlreadyExistsException ta){
            throw new TaskAlreadyExistsException();
        }
    }


    //-->http://localhost:9898/task/v2/
    @PutMapping("/v2")
    public ResponseEntity<?> modifyTask(@RequestBody Task task) throws TaskNotFoundException
    {
        try{
            return new ResponseEntity<>(kanBanService.updateUserTask(task), HttpStatus.OK);
        }
        catch(TaskNotFoundException tnf){

            throw new TaskNotFoundException();
    }
    }

    //-->http://localhost:9898/task/v2/{userId}
    @GetMapping("/v2/{userId}")
    public ResponseEntity<?> extractTasksById(@PathVariable int userId) throws TaskNotFoundException
    {
        try{
            return new ResponseEntity<>(kanBanService.showAllTasksByUserId(userId), HttpStatus.OK);
        }
        catch(TaskNotFoundException tnf){

            throw new TaskNotFoundException();
        }
    }

    //-->http://localhost:9898/task/v2
    @DeleteMapping("/v2")
    public ResponseEntity<?> deleteTask(@RequestBody Task task) throws TaskNotFoundException{
        try{
            kanBanService.removeTask(task);
            return new ResponseEntity<>(new Message("Success", "Record Deleted"), HttpStatus.OK);
        }
        catch(TaskNotFoundException tnf){
            throw new TaskNotFoundException();
        }
    }

    //-->http://localhost:9898/task/v2/{taskId}
    @GetMapping("/v2/card/{taskId}")
    public ResponseEntity<?> getTaskByTaskId(@PathVariable int taskId) throws TaskNotFoundException{
        try{
            return new ResponseEntity<>(kanBanService.getTaskById(taskId), HttpStatus.OK);
        }
        catch(TaskNotFoundException tnf){
            throw new TaskNotFoundException();
        }
    }


}
