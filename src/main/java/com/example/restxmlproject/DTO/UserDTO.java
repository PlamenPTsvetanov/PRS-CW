package com.example.restxmlproject.DTO;

import com.example.restxmlproject.entities.UserEntity;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@XmlRootElement
@Getter
@Setter
public class UserDTO implements Serializable {
    private String firstName;
    private String middleName;
    private String familyName;
    private String address;
    private String username;

    public UserDTO() {
    }

    public UserDTO(UserEntity entity) {
        this.firstName = entity.getFirstName();
        this.middleName = entity.getMiddleName();
        this.familyName = entity.getFamilyName();
        this.address = entity.getAddress();
        this.username = entity.getUsername();
    }
}