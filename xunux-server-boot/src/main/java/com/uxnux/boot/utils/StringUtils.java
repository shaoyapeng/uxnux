package com.uxnux.boot.utils;

/**
 * @Author: 10785
 * @Date: 2019/11/15 13:34
 * @Version: 1.0
 */
public class StringUtils {

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() <= 0;
    }

    public static boolean isBlack(String str) {
        return isEmpty(str) || str == "null";
    }
}
