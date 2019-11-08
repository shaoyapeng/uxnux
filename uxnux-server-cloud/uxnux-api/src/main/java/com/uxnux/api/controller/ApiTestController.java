package com.uxnux.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 10785
 * @Date: 2019/11/8 9:57
 * @Version: 1.0
 */
@RestController
public class ApiTestController {

    @RequestMapping("/test")
    public String getTest () {
        return "test";
    }
}
