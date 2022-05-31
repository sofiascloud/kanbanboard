package com.niit.notificationapplication.rabbitMq;

import com.niit.notificationapplication.config.SmtpMailSender;
import com.niit.notificationapplication.model.Task;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class Consumer {

    @Autowired
    SmtpMailSender smtpMailSender;

    @RabbitListener(queues = "task_queue")
    public void getUserDtoFromRabbitMq(TaskDTO taskdto) throws MessagingException {

        Task task= new Task();
        task.setTaskId(taskdto.getTaskId());
        task.setTaskTitle(taskdto.getTaskTitle());
        task.setTaskDesc(taskdto.getTaskDesc());
        task.setTaskDeadline(taskdto.getTaskDeadline());
        task.setPriority(taskdto.getPriority());
        task.setAssignee(taskdto.getAssignee());
        task.setStatus(taskdto.getStatus());
        task.setUserId(taskdto.getUserId());
        task.setEmailId(taskdto.getEmailId());

        System.out.println("Task Body in Consumer -->"+task.getEmailId());
        smtpMailSender.send(task.getEmailId(), "KanBanBoard Application",
         "Your CRUD operation is successful. Check the task body for more details"+"\n \t,"+
                "Recent task-->"+"\n \t,"+"Task Id--"+task.getTaskId()+"\n \t,"+"Task Title--"+task.getTaskTitle()
                 +"\n \t,"+"Task Description--"+task.getTaskDesc()+"\n \t,"+"Task DeadLine--"+task.getTaskDeadline()+"\n \t,"+"Task Priority"+task.getPriority()+
                "Task Assignee--"+task.getAssignee()+"\n \t,"+"Task Status"+task.getStatus()+"\n \t,"+"User Id--"+task.getUserId());
    }
}