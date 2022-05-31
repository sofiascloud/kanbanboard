package com.niit.boardapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document
public class User {

    @Id
    private int userId;
    private String userName;
//    private String emailId;
    private String password;

}
