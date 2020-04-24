package com.uxnux.activiti;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class UxnuxActivitiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UxnuxActivitiApplication.class, args);
    }

}
