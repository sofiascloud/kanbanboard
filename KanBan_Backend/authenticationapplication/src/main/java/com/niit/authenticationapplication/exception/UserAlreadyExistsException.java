package com.niit.authenticationapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT, reason="email already registered")
public class UserAlreadyExistsException extends Exception{
}
