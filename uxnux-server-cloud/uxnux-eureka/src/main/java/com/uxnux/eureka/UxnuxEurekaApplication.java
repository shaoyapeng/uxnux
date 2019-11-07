package com.uxnux.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class UxnuxEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(UxnuxEurekaApplication.class, args);
    }

}
