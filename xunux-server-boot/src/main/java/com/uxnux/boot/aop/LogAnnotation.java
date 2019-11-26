package com.uxnux.boot.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @Author: 10785
 * @Date: 2019/11/26 12:48
 * @Version: 1.0
 */
@Target(ElementType.METHOD)
public @interface LogAnnotation {
    String describe();
}
