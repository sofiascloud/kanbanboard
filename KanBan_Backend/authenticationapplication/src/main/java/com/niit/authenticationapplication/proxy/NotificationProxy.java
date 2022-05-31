package com.niit.authenticationapplication.proxy;

import com.niit.authenticationapplication.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(name="Notification-service", url="localhost:7878") // details for where to connect
public interface NotificationProxy {

    @PostMapping("/msg/v3")
    public ResponseEntity<?> RegisterUser(@RequestBody User user);


}
