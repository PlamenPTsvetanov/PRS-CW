package com.example.restxmlproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.example.restxmlproject",
        "com.example.restxmlproject.controllers",
        "com.example.restxmlproject.entities",
        "com.example.restxmlproject.repositories",
        "com.example.restxmlproject.security",
        "com.example.restxmlproject.services",
})
public class RestXmlProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestXmlProjectApplication.class, args);
    }

}
