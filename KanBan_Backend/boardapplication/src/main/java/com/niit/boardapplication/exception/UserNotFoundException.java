package com.niit.boardapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason = "User not registered/User has not created KanBan Board.")
public class UserNotFoundException extends Exception {
}
