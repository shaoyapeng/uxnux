package com.uxnux.auth.common.expection;

import com.uxnux.zuul.common.enums.ErrorCodeEnum;

/**
 * @Author: 10785
 * @Date: 2019/11/9 11:33
 * @Version: 1.0
 */
public class BaseExpection extends RuntimeException {

    private int code;

    public BaseExpection() {
    }

    public BaseExpection(Throwable cause) {
        super(cause);
    }

    public BaseExpection(String message) {
        super(message);
    }

    public BaseExpection(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseExpection(int code, String message) {
        super(message);
        this.code = code;
    }

    public BaseExpection(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
    }

    public BaseExpection(ErrorCodeEnum codeEnum, Object... args) {
        super(String.format(codeEnum.msg(), args));
        this.code = codeEnum.code();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
