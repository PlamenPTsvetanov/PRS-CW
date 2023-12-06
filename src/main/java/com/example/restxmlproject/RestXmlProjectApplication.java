package com.example.restxmlproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.example.restxmlproject"})
public class RestXmlProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestXmlProjectApplication.class, args);
    }

}
