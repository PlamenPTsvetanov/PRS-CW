package com.example.restxmlproject.DTO;

import com.example.restxmlproject.entities.AccountEntity;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@XmlRootElement
@Getter
@Setter
public class AccountDTO implements Serializable {
    private Double amount;

    public AccountDTO() {
    }

    public AccountDTO(AccountEntity entity) {
        this.amount = entity.getAmount();
    }
}