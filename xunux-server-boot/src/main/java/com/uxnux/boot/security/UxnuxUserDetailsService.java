package com.uxnux.boot.security;

import com.uxnux.boot.exception.ServiceException;
import com.uxnux.boot.model.entity.sys.SysUser;
import com.uxnux.boot.service.sys.SysUserService;
import com.uxnux.boot.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SysUserService sysUserService;

    @Override
    public UxnuxUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UxnuxUserDetails uxnuxUserDetails = new UxnuxUserDetails();
        SysUser sysUser = null;
        try {
            sysUser = sysUserService.getSysUserByUserNo(s);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if (sysUser == null || StringUtils.isBlack(sysUser.getPassword())) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        uxnuxUserDetails.setPassword(sysUser.getPassword());
        uxnuxUserDetails.setUsername(s);
        UxnuxGrantedAuthority uxnuxGrantedAuthority = new UxnuxGrantedAuthority();
        uxnuxGrantedAuthority.setAuthority("/test3");
        uxnuxUserDetails.setAuthorities(new ArrayList<UxnuxGrantedAuthority>());
        return uxnuxUserDetails;
    }
}
