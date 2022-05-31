package com.niit.authenticationapplication.services;

import com.niit.authenticationapplication.exception.UserAlreadyExistsException;
import com.niit.authenticationapplication.exception.UserNotFoundException;
import com.niit.authenticationapplication.model.User;
import com.niit.authenticationapplication.proxy.NotificationProxy;
import com.niit.authenticationapplication.proxy.Proxy;
import com.niit.authenticationapplication.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import javax.persistence.Column;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServicesTest {

    //reference for User
    private User user1, user2;


    //creating a mock layer of repository for testing purpose
    @Mock
    private UserRepository userRepository;

    @Mock
    Proxy proxy;

    @Mock
    NotificationProxy notificationProxy;

    //creating a reference of service layer and inject the mock
    @InjectMocks
    private UserServicesImpl userServicesImpl;

    @BeforeEach()
    public void setUp(){
        user1= new User(1,"kevin", "kevin@gmail.com", "kev123");
        user2= new User(2,"Kevin Mathew", "kevin@gmail.com", "kev123");
    }

    @AfterEach()
        public void tearDown(){
            user1=null;
            user2=null;
            userRepository.deleteAll();
        }


    @Test
    public void givenUserDetailRegisterReturnSavedUser()throws UserAlreadyExistsException {
        when(userRepository.findById(user1.getUserId())).thenReturn(Optional.ofNullable(null));
        when(userRepository.save(user1)).thenReturn(user1);
        assertEquals(user1, userServicesImpl.registerUser(user1));
        verify(userRepository, times(1)).findById(any()); //the no.of times findbyid to be called
        verify(userRepository, times(1)).save(any());

    }

    //For Registration
    @Test()
    public void givenSameUserEmailReturnException()throws UserAlreadyExistsException{
        when(userRepository.findById(user2.getUserId())).thenReturn(Optional.ofNullable(user2));
        assertThrows(UserAlreadyExistsException.class, ()->userServicesImpl.registerUser(user2));
        verify(userRepository, times(1)).findById(any()); //the no.of times findbyid to be called
        System.out.println("GivenSameUserEmailReturnException: Registration Failed. Email Already Exists");
    }

    @Test()
    public void givenUserDetailLoginAndReturnUser()throws UserNotFoundException{

        when(userRepository.findByEmailIdAndPassword(user1.getEmailId(),user1.getPassword())).thenReturn(user1);
        assertEquals(user1, userServicesImpl.loginUser(user1.getEmailId(),user1.getPassword()));
        verify(userRepository, times(1)).findByEmailIdAndPassword(any(),any()); //the no.of times findByEmailIdAndPassword to be called
    }

    //if user not register
//    @Test()
//    public void givenNonRegisteredUserForLoginReturnError()throws UserNotFoundException{
//
//        when(userRepository.findByEmailIdAndPassword("no@gmail.com", "123")).thenReturn(Optional.ofNullable());
//        assertThrows(UserNotFoundException.class, ()->userServicesImpl.loginUser("no@gmail.com", "123"));//the no.of times findbyid to be called
//        verify(userRepository, times(1)).findByEmailIdAndPassword("no@gmail.com", "123");
//        System.out.println("error");
//
//    }

}
