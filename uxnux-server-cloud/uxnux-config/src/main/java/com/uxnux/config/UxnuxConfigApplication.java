package com.uxnux.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class UxnuxConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(UxnuxConfigApplication.class, args);
    }

}
