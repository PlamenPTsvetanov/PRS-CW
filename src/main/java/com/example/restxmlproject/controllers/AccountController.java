package com.example.restxmlproject.controllers;

import com.example.restxmlproject.entities.AccountEntity;
import com.example.restxmlproject.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService service;

    @Autowired
    public AccountController(AccountService serv){
        service = serv;
    }

    @PostMapping("/create")
    public void register(@RequestBody AccountEntity account){
        service.createAccount(account);
    }


}
