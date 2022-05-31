package com.niit.authenticationapplication.services;

import com.niit.authenticationapplication.model.User;

import java.util.Map;

public interface AuthenticationTokenGenerator {
    public abstract Map<String, String> generateToken(User user);
}
