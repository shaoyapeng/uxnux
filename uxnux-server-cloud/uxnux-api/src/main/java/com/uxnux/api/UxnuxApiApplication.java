package com.uxnux.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UxnuxApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UxnuxApiApplication.class, args);
    }

}
