package com.niit.boardapplication.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private @Getter @Setter int userId;          // filter at extraction
    private @Getter @Setter String emailId;  //For messaging Service
}
