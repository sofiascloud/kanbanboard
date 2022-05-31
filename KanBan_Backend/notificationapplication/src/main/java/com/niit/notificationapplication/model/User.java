package com.niit.notificationapplication.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class User {

    private int userId;
    @Id
    private String emailId;
    private String userName;
    private String password;
}
