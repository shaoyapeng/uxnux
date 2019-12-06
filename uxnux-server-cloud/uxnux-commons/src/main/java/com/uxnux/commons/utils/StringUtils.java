package com.uxnux.commons.utils;

/**
 * @Author: 10785
 * @Date: 2019/12/6 14:04
 * @Version: 1.0
 */
public class StringUtils {

    /**
     * 判断字符在是否为空，包括 "null" 和 "NULL"，
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str) || "null".equals(str) || "NULL".equals(str);
    }
}
