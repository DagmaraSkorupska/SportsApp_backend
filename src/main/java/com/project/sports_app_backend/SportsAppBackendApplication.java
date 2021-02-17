package com.project.sports_app_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication

public class SportsAppBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportsAppBackendApplication.class, args);
    }

}
