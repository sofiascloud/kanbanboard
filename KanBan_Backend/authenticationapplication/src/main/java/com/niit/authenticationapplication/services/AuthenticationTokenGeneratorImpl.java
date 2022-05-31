package com.niit.authenticationapplication.services;

import com.niit.authenticationapplication.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationTokenGeneratorImpl implements AuthenticationTokenGenerator {

    @Override
    public Map<String, String> generateToken(User user) {
            // for setting up expiration timer for the token
            Date cd = new Date();
           cd.setMinutes(cd.getMinutes()+5);
          //using Jwts.builder() to create a jwt token (specifying jwt attribute subject, issued at etc.
        String jwtToken = Jwts.builder().setSubject(user.getUserName()).
                setIssuedAt(new Date())
                .setExpiration(cd) //setting up expiration timer for the token
                .signWith(SignatureAlgorithm.HS256, "mykey").compact();//creating signed token with hMAC using signwith method

        Map <String, String> map = new HashMap<>();
        map.put("token", jwtToken);
        map.put("message", "User logged in successfully");
        return map;
    }
}
