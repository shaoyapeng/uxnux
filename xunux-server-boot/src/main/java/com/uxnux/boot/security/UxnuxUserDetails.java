package com.uxnux.boot.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: 10785
 * @Date: 2019/11/14 19:57
 * @Version: 1.0
 */
@Data
public class UxnuxUserDetails implements UserDetails {

    private String username;

    private String password;

    private List<UxnuxGrantedAuthority> uxnuxGrantedAuthorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return uxnuxGrantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 判断账号是否过期
     * @return true 没过期，false，过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 判断账号是否锁定
     * @return true 没过期，false，过期
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 凭证是否过期
     * @return true 没过期，false，过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否用
     * @return true 启用，false，没启用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * 权限list
     * @param uxnuxGrantedAuthorities
     */
    public void setAuthorities(List<UxnuxGrantedAuthority> uxnuxGrantedAuthorities) {
        this.uxnuxGrantedAuthorities = uxnuxGrantedAuthorities;
    }
}
