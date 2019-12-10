package com.uxnux.commons.utils;

/**
 * @Author: 10785
 * @Date: 2019/12/9 13:03
 * @Version: 1.0
 */
public enum UxnuxEnum {

    /**
     *
     */
    SUCCESS(200),
    /**
     *
     */
    FAIL(400),
    /**
     *
     */
    ERROR(500),
    /**
     *
     */
    WARN(300);

    private Integer code;

    UxnuxEnum(Integer code) {
        this.code = code;
    }
}
