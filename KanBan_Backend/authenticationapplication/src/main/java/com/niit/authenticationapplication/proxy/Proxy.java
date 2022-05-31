package com.niit.authenticationapplication.proxy;


import com.niit.authenticationapplication.model.User;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name="boardapplication", url="localhost:9898") // details for where to connect
public interface Proxy {

    @PostMapping("/kanban/v1")
    public ResponseEntity<?> createUser(@RequestBody User user);



}
