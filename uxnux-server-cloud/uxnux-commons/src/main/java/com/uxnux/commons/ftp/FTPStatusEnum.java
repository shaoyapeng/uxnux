package com.uxnux.commons.ftp;

/**
 * @Author: 10785
 * @Date: 2019/12/5 14:30
 * @Version: 1.0
 */
public enum FTPStatusEnum {
    /**
     * ftp连接成功
     */
    CONNECT_SUCCESS(100, "ftp连接成功"),

    /**
     * ftp连接失败
     */
    CONNECT_FAIL(101, "ftp连接失败"),

    /**
     * ftp连接异常
     */
    CONNECT_ERROR(103, "ftp连接异常"),

    /**
     * 操作成功
     */
    OPERATION_SUCCESS(201, "ftp操作成功"),

    /**
     * ftp操作失败
     */
    OPERATION_FAIL(202, "ftp操作失败"),

    /**
     * ftp操作异常
     */
    OPERATION_ERROR(203, "ftp操作异常"),
    /**
     * 其他的未知异常
     */
    ERROR(999, "系统异常");
    /**
     *
     */
    private Integer code;
    /**
     *
     */
    private String msg;


    FTPStatusEnum (Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    @Override
    public String toString() {
        return "CODE: " + this.code + "MSG: " + this.msg;
    }
}
