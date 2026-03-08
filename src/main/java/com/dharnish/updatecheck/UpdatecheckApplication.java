package com.dharnish.updatecheck;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.dharnish.updatecheck.entity.User;
import com.dharnish.updatecheck.repository.UserRepository;

@SpringBootApplication
@EnableScheduling
public class UpdatecheckApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpdatecheckApplication.class, args);
    }

    
    }
