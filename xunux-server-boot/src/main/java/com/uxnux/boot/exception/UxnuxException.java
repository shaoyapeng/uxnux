package com.uxnux.boot.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: 10785
 * @Date: 2019/11/18 8:59
 * @Version: 1.0
 */
@Slf4j
public class UxnuxException extends Exception {

    public UxnuxException() {
        super();
    }

    public UxnuxException(String msg) {
        super(msg);
    }

    public UxnuxException(String msg, Throwable t) {
        super(msg, t);
    }
}
