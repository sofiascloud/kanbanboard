package com.niit.authenticationapplication.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue
    private int userId;
    private String userName;
    @Column(unique = true)
    private String emailId;
    private String password;


}
