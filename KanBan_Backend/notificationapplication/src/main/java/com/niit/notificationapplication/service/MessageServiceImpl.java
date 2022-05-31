package com.niit.notificationapplication.service;

import com.niit.notificationapplication.model.Task;
import com.niit.notificationapplication.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    //instance
    @Autowired
    MessageRepository messageRepository;

    @Override
    public void delTask(Task task) {
    messageRepository.deleteById(task.getTaskId());
    }
}
