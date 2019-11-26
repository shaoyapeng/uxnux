package com.uxnux.boot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 10785
 * @Date: 2019/11/16 15:48
 * @Version: 1.0
 */
@Api(tags = "测试类")
@RestController
public class TestController {

    @RequestMapping("/test1")
    @ApiOperation(value = "测试名称", notes = "测试接口描述", httpMethod = "POST")
    public String test () {
        return "test";
    }

    @RequestMapping("/test2")
    @ApiOperation(value = "测试名称2", notes = "测试接口描述2", httpMethod = "POST")
    public String test2 () {
        return "test2";
    }
}
