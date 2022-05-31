package com.niit.notificationapplication.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class Task {

    @Id
    private @Getter @Setter int taskId;             //no duplication
    private @Getter @Setter String taskTitle;
    private @Getter @Setter String taskDesc;
    private @Getter @Setter String taskDeadline;
    private @Getter @Setter String priority;
    private @Getter @Setter String assignee;
    private @Getter @Setter String status;        // (setting cards in front-end lane)
    private @Getter @Setter int userId;
    private @Getter @Setter String emailId;

}
