package com.uxnux.auth.service.impl;

import com.uxnux.auth.model.po.SysUser;
import com.uxnux.auth.service.SysUserService;

/**
 * @Author: 10785
 * @Date: 2019/11/9 15:47
 * @Version: 1.0
 */
public class SysUserServiceImpl implements SysUserService {

    @Override
    public SysUser findByUsername(String username) {
        return new SysUser();
    }
}
