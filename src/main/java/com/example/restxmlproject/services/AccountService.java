package com.example.restxmlproject.services;

import com.example.restxmlproject.entities.AccountEntity;
import com.example.restxmlproject.repositories.AccountRepository;
import com.example.restxmlproject.utils.LogInManager;
import com.example.restxmlproject.utils.ServerUtil;
import com.example.restxmlproject.utils.SignUtil;
import com.example.restxmlproject.utils.XmlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

@Service
public class AccountService {
    private final AccountRepository repository;

    @Autowired
    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public int createAccount(Double amount) throws Exception {
        AccountEntity account = new AccountEntity();
        account.setUser(LogInManager.getLoggedInUser());
        account.setAmount(amount);
        this.repository.save(account);
        return account.getId();
    }

    public void topUpAccount(Integer accountId, Double topUpAmount) {
        try {
            AccountEntity foundAccount = this.repository.findById(accountId).orElse(null);

            if (foundAccount == null) {
                throw new Exception("No account with such id found!");
            }

            foundAccount.setAmount(foundAccount.getAmount() + topUpAmount);

            Document pushMessage = XmlBuilder.buildPushMessage(LogInManager.getLoggedInUser(), foundAccount);

            SignUtil.signFile(pushMessage);
            int i = ServerUtil.sendToServer(pushMessage);
            if (i == 200) {
                this.repository.save(foundAccount);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
