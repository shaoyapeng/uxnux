package com.uxnux.auth.service;

import com.uxnux.auth.model.po.SysUser;

/**
 * @Author: 10785
 * @Date: 2019/11/9 15:42
 * @Version: 1.0
 */
public interface SysUserService {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    public SysUser findByUsername(String username);
}
