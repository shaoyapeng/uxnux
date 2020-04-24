package com.uxnux.activiti.config;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 10785
 * @Date: 2019/12/23 12:25
 * @Version: 1.0
 */
@Data
public class ActivitiResult<T> implements Serializable {
    private static final long serialVersionUID = -7995058338163551623L;

    private static final int SUCCESS_CODE = 200;

    private static final int ERROR_CODE = 200;

    private String msg;

    private int code;

    // private String requestId;

    private T data;

    private boolean success;

    private ActivitiResult() {}

    private ActivitiResult(String msg) {
        this.msg = msg;
    }

    private ActivitiResult(T data) {
        this.data = data;
    }

    private ActivitiResult(T data, Integer code) {
        this.data = data;
        this.code = code;
    }

    private ActivitiResult(T data, String msg) {
        this.data = data;
        this.msg = msg;
    }

    public static <T> ActivitiResult<T> OK(T data) {
        return new ActivitiResult(data);
    }

    public static <T> ActivitiResult<T> OK(String msg) {
        return new ActivitiResult(msg, SUCCESS_CODE);
    }

    public static <T> ActivitiResult<T> OK(T data, String msg) {
        return new ActivitiResult(data, msg);
    }

    public static <T> ActivitiResult<T> Error(String msg) {
        return new ActivitiResult(msg, ERROR_CODE);
    }
}
