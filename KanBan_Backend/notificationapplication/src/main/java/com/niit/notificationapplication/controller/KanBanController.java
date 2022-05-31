package com.niit.notificationapplication.controller;

import com.niit.notificationapplication.config.SmtpMailSender;
import com.niit.notificationapplication.model.Task;
import com.niit.notificationapplication.model.User;
import com.niit.notificationapplication.service.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;


@RestController
@RequestMapping("/msg")
public class KanBanController {

    //creating reference
    SmtpMailSender smtpMailSender;
    MessageServiceImpl messageService;

    //Constructor DI
    @Autowired
    public KanBanController( SmtpMailSender smtpMailSender,MessageServiceImpl messageService) {
        this.messageService=messageService;
        this.smtpMailSender = smtpMailSender;
    }


    //-->http://localhost:7878/task/v3
    @PostMapping("/v3") //At Task Addition
    public void sendTask(@RequestBody User user) throws  MessagingException {
        smtpMailSender.send(user.getEmailId(),"KanBan Registeration", "Welcome "+user.getUserName()
                +"You Registered Successfully");

    }

    //-->http://localhost:7878/task/v3
//    @PutMapping("/v3") //At Task updation
//    public void updateTask(@RequestBody Task task) throws  MessagingException {
//        smtpMailSender.send(task.getEmailId(),"KanBan CRUD Operation","Task successfully updated.");
//        System.out.println("Update mail sent");
//    }
//
//    //-->http://localhost:7878/task/v3
//    @DeleteMapping("/v3") //At Task deletion
//    public void delTask(@RequestBody Task task) throws  MessagingException {
//        smtpMailSender.send(task.getEmailId(),"KanBan CRUD Operation","Task successfully deleted.");
//        System.out.println("Delete mail sent");
//        messageService.delTask(task); //real delete function
//    }

}
