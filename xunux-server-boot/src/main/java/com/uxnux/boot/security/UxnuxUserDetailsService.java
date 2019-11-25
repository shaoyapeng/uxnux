package com.uxnux.boot.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

/**
 * @Author: 10785
 * @Date: 2019/11/14 19:56
 * @Version: 1.0
 */
@Slf4j
public class UxnuxUserDetailsService implements UserDetailsService {

    @Override
    public UxnuxUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UxnuxUserDetails uxnuxUserDetails = new UxnuxUserDetails();
        uxnuxUserDetails.setPassword("AL5riQzgwmMhXZX+5MtU0A==");
        uxnuxUserDetails.setUsername("admin");
        UxnuxGrantedAuthority uxnuxGrantedAuthority = new UxnuxGrantedAuthority();
        uxnuxUserDetails.setAuthorities(new ArrayList<UxnuxGrantedAuthority>());
        return uxnuxUserDetails;
    }
}
