package com.example.academix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AcademiXApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcademiXApplication.class, args);
    }

}
