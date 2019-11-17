package com.uxnux.boot.security;

import com.uxnux.boot.utils.CryptoUtils;
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
        return CryptoUtils.encryptAES((String)charSequence);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        System.out.println(MD5Encoder.encode(s.getBytes()));
        return s.equals(CryptoUtils.encryptAES((String)charSequence));
    }
}
