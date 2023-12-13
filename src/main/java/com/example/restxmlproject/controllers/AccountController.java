package com.example.restxmlproject.controllers;

import com.example.restxmlproject.entities.AccountEntity;
import com.example.restxmlproject.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService service;

    @Autowired
    public AccountController(AccountService serv) {
        service = serv;
    }

    @PostMapping("/create")
    public void register(@RequestBody Double amount) {
        service.createAccount(amount);
    }


    @PostMapping("/topUp/{id}/{amount}")
    public void topUp(
            @PathVariable("id") Integer id,
            @PathVariable("amount") Double amount) {
        service.topUpAccount(id, amount);
    }
}
