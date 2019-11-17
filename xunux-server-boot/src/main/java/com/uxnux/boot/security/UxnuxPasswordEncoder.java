package com.uxnux.boot.security;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: 10785
 * @Date: 2019/11/14 20:00
 * @Version: 1.0
 */
public class UxnuxPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return MD5Encoder.encode(((String)charSequence).getBytes());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        System.out.println(MD5Encoder.encode(s.getBytes()));
        return s.equals(MD5Encoder.encode(((String)charSequence).getBytes()));
    }
}
