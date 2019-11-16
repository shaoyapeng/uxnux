package com.uxnux.boot.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * @Author: 10785
 * @Date: 2019/11/15 8:54
 * @Version: 1.0
 */
public class UxnuxGrantedAuthority implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return "admin";
    }
}
