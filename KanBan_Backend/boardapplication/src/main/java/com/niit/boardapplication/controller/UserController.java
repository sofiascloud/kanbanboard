package com.niit.boardapplication.controller;

import com.niit.boardapplication.exception.UserAlreadyExistsException;
import com.niit.boardapplication.exception.UserNotFoundException;
import com.niit.boardapplication.model.User;
import com.niit.boardapplication.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/kanban")
@RestController
public class UserController {

    //creating instance for userServiceImpl
    UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    //-->http://localhost:9898/kanban/v1
    @PostMapping("/v1")
    public ResponseEntity<?> sendUser(@RequestBody User user) throws UserAlreadyExistsException {
        try{
            return new ResponseEntity<>(userServiceImpl.createUser(user), HttpStatus.OK);
        }
        catch(UserAlreadyExistsException ae){
            throw new UserAlreadyExistsException();
        }
    }

    //-->http://localhost:9898/kanban/v1/{userId}
    @DeleteMapping("/v1/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId) throws UserNotFoundException {
        try{
            userServiceImpl.removeUser(userId);
            return new ResponseEntity<>("User with userId " + userId +" is Deleted", HttpStatus.OK);
        }
        catch(UserNotFoundException nf){
            throw new UserNotFoundException();
        }
    }

    //-->http://localhost:9898/kanban/v1/{userId}
    @GetMapping("/v1/{userId}")
    public ResponseEntity<?> findUser(@PathVariable int userId) throws UserNotFoundException {
        try{

            return new ResponseEntity<>( userServiceImpl.showUser(userId), HttpStatus.OK);
        }
        catch(UserNotFoundException nf){
            throw new UserNotFoundException();
        }

    }

}
