package com.niit.authenticationapplication.services;

import com.niit.authenticationapplication.exception.UserAlreadyExistsException;
import com.niit.authenticationapplication.exception.UserNotFoundException;
import com.niit.authenticationapplication.model.User;

public interface UserServices {
    public abstract User registerUser(User user) throws UserAlreadyExistsException;
    public abstract User loginUser( String emailId, String password) throws UserNotFoundException;
}
