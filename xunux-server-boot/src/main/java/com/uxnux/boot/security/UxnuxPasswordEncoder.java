package com.uxnux.boot.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: 10785
 * @Date: 2019/11/14 20:00
 * @Version: 1.0
 */
public class UxnuxPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return null;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return false;
    }
}
