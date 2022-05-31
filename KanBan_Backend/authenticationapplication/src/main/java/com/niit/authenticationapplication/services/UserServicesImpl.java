package com.niit.authenticationapplication.services;

import com.niit.authenticationapplication.exception.UserAlreadyExistsException;
import com.niit.authenticationapplication.exception.UserNotFoundException;
import com.niit.authenticationapplication.model.User;
import com.niit.authenticationapplication.proxy.NotificationProxy;
import com.niit.authenticationapplication.proxy.Proxy;

import com.niit.authenticationapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {

    //Creating an instance of userrepository
    UserRepository userRepository;
    Proxy proxy;
    NotificationProxy notificationProxy;

    @Autowired
    public UserServicesImpl(UserRepository userRepository, Proxy proxy, NotificationProxy notificationProxy) {
        this.userRepository = userRepository;
        this.proxy = proxy;
        this.notificationProxy = notificationProxy;
    }


    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        if(userRepository.findById(user.getUserId()).isEmpty()){
            userRepository.save(user);
           proxy.createUser(user); //proxy
            notificationProxy.RegisterUser(user) ; //proxy
            return user;
        } else {
            throw new UserAlreadyExistsException();
        }
    }

    @Override
    public User loginUser( String emailId, String password) throws UserNotFoundException {
        User user = userRepository.findByEmailIdAndPassword(emailId, password);
        //if user the available, login
        if ((user.getEmailId().equals(emailId)) && (user.getPassword().equals(password))){
            System.out.println("success");
//            ResponseEntity repsonse= proxy.enterUserId(userId);
            return user;
        }
        else{
            System.out.println("fail");
            throw new UserNotFoundException();
        }

    }
}
