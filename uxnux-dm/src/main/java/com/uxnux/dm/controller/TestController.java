package com.uxnux.dm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 10785
 * @Date: 2019/12/2 15:01
 * @Version: 1.0
 */
@RestController
public class TestController {

    @RequestMapping("/test/1")
    public String test() {
        return "test";
    }
}
