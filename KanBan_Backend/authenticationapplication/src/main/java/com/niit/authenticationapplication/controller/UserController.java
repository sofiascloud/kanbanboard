package com.niit.authenticationapplication.controller;

import com.niit.authenticationapplication.exception.UserAlreadyExistsException;
import com.niit.authenticationapplication.exception.UserNotFoundException;
import com.niit.authenticationapplication.model.User;
import com.niit.authenticationapplication.proxy.Proxy;
import com.niit.authenticationapplication.services.AuthenticationTokenGenerator;
import com.niit.authenticationapplication.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/user/auth")
public class UserController {

    private UserServices userServices;
    private AuthenticationTokenGenerator authTokenGenerator;
    private Proxy proxy;

    @Autowired
    public UserController(UserServices userServices, AuthenticationTokenGenerator authTokenGenerator,
                          Proxy proxy) {
        this.userServices = userServices;
        this.authTokenGenerator = authTokenGenerator;
        this.proxy=proxy;
    }

    //register
    //http://localhost:8888/user/auth/register

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistsException {
        try {
            return new ResponseEntity<>(userServices.registerUser(user), HttpStatus.OK);
        }
        catch(UserAlreadyExistsException ex) {
            throw new UserAlreadyExistsException();
        }
    }

    //http://localhost:8888/user/auth/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) throws UserNotFoundException {
        Map<String, String> map = null;
        try {
            //authenticate the user
            User resultUser= userServices.loginUser(user.getEmailId(),user.getPassword());

            //if the user emailid matches, token is generated
            if(resultUser.getEmailId().equals(user.getEmailId())){
                map= authTokenGenerator.generateToken(resultUser);
            }
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        catch(UserNotFoundException ex){
            throw new UserNotFoundException();
        }
//        finally{
//            proxy.enterUserId(user.getUserId());
//        }
      }
}

