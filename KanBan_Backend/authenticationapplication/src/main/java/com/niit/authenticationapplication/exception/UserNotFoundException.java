package com.niit.authenticationapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason="user detail not found")
public class UserNotFoundException extends Exception{
}
