package com.uxnux.boot.utils;

import java.util.Map;

/**
 * @Author: 10785
 * @Date: 2019/11/15 12:01
 * @Version: 1.0
 */
public class ObjectUtils {

    public static boolean isEmpty(Object o) {
        return o == null;
    }

    public static boolean isEmptyMap(Map map) {
        return map == null || map.isEmpty();
    }


}
