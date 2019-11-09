package com.uxnux.auth.common.enums;

/**
 * @Author: 10785
 * @Date: 2019/11/9 11:35
 * @Version: 1.0
 */
public enum ErrorCodeEnum {
    /**
     * token过期
     */
    UX_ECM401(401, "token过期"),
    /**
     * 未知异常
     */
    UX_ECM500(500, "未知异常"),
    /**
     * 无权访问
     */
    UX_ECM403(403, "无权访问"),
    /**
     * 找不到指定资源
     */
    UX_ECM404(404, "找不到指定资源");
    /**
     * code
     */
    private int code;
    /**
     * message
     */
    private String msg;
    public String msg() {
        return msg;
    }
    public int code() {
        return code;
    }

    /**
     * 枚举
     * @param code
     * @param msg
     */
    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * get方法
     * @param code
     * @return
     */
    public static ErrorCodeEnum getEnum(int code) {
        for (ErrorCodeEnum ele : ErrorCodeEnum.values()) {
            if (ele.code() == code) {
                return ele;
            }
        }
        return null;
    }
}
