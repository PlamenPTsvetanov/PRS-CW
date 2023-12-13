package com.example.restxmlproject.controllers;

import com.example.restxmlproject.entities.UserEntity;
import com.example.restxmlproject.services.UserService;
import com.example.restxmlproject.utils.LoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService serv){
        service = serv;
    }

    @PostMapping("/register")
    public void register(@RequestBody UserEntity user){
        service.register(user);
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginModel model){
        service.logIn(model.getUsername(), model.getPassword());
    }
}
