package com.example.springbootpostgres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class SpringbootPostgresApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootPostgresApplication.class, args);
    }

}
