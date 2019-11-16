package com.uxnux.boot.security;

import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @Author: 10785
 * @Date: 2019/11/14 19:56
 * @Version: 1.0
 */
@Logger
public class UxnuxUserDetailsService implements UserDetailsService {

    @Override
    public UxnuxUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UxnuxUserDetails uxnuxUserDetails = new UxnuxUserDetails();
        return uxnuxUserDetails;
    }
}
