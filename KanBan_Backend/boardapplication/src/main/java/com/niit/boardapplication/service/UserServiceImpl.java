package com.niit.boardapplication.service;

import com.niit.boardapplication.exception.UserAlreadyExistsException;
import com.niit.boardapplication.exception.UserNotFoundException;
import com.niit.boardapplication.model.User;
import com.niit.boardapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    //creating instance of userRepo
    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) throws UserAlreadyExistsException {
        if(userRepository.findById(user.getUserId()).isEmpty()){
            return userRepository.save(user);
        }
        else{
            throw new UserAlreadyExistsException();
        }
    }

    @Override
    public boolean removeUser(int userId) throws UserNotFoundException {
        if(userRepository.findById(userId).isPresent()){
             userRepository.deleteById(userId);
             return true;
        }
        else{
            throw new UserNotFoundException();
        }
    }

    @Override
    public User showUser(int userId) throws UserNotFoundException {
        if(userRepository.findById(userId).isPresent()){
            return userRepository.findById(userId).get();
        }
        else{
            throw new UserNotFoundException();
        }
    }
}
