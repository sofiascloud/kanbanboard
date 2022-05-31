package com.niit.boardapplication.service;

import com.niit.boardapplication.exception.UserAlreadyExistsException;
import com.niit.boardapplication.exception.UserNotFoundException;
import com.niit.boardapplication.model.User;

public interface UserService {

    //Abstract Method to add user in mongo
    public abstract User createUser(User user) throws UserAlreadyExistsException;

    //Abstract Method to delete User from mongo
    public abstract boolean removeUser(int UserId) throws UserNotFoundException;

    //Abstract Method to find user by userId
    public abstract User showUser(int UserId) throws UserNotFoundException;
}
