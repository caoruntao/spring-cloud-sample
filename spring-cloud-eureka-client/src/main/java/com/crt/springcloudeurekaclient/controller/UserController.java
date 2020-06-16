package com.crt.springcloudeurekaclient.controller;

import com.crt.springcloudeurekaclient.domain.User;
import com.crt.springcloudeurekaclient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/save")
    public User saveUser(@RequestBody User user){
        if(userService.saveUser(user)){
            return user;
        }
        return null;
    }

    @GetMapping("/user/findAll")
    public Collection<User> findAll(){
        return userService.findAll();
    }
}
