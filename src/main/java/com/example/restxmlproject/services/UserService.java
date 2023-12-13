package com.example.restxmlproject.services;

import com.example.restxmlproject.entities.UserEntity;
import com.example.restxmlproject.repositories.UserRepository;
import com.example.restxmlproject.utils.LogInManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;

    @Autowired
    public UserService(UserRepository repository){
        repo = repository;
    }

    public void register(UserEntity entity){
        this.repo.save(entity);
    }

    public void logIn(String username, String password){
        UserEntity user = this.repo.findByUsernameAndPassword(username, password);

        LogInManager.init(user);
    }
}
