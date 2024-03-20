package com.example.cineverse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CineverseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CineverseApplication.class, args);
    }

    // kdh..
}
