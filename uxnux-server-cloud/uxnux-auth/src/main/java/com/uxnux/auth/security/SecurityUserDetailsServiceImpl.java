package com.uxnux.auth.security;

import com.uxnux.auth.model.po.SysUser;
import com.uxnux.auth.model.vo.SecurityUserDetails;
import com.uxnux.auth.service.SysUserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author: 10785
 * @Date: 2019/11/9 15:30
 * @Version: 1.0
 */
public class SecurityUserDetailsServiceImpl implements UserDetailsService {

    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.findByUsername(s);
        if (sysUser == null) {
            throw new BadCredentialsException("用户名不存在或者密码错误");
        }
        return new SecurityUserDetails();
    }
}
