package com.niit.boardapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.CONFLICT, reason="This task already exists")
public class TaskAlreadyExistsException extends Exception{
}
