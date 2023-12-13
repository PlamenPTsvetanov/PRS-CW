package com.example.restxmlproject.utils;

import com.example.restxmlproject.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class LogInManager {
    private static UserEntity entity;


    public static void init(UserEntity e){
        entity = e;
    }

    public static UserEntity getLoggedInUser(){
        return entity;
    }
}