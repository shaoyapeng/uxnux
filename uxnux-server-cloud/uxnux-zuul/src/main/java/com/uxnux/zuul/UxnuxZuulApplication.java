package com.uxnux.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class UxnuxZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(UxnuxZuulApplication.class, args);
    }

}
