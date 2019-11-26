package com.uxnux.boot.repository.sys;

import com.uxnux.boot.model.entity.sys.SysUser;
import com.uxnux.boot.repository.BaseRepository;

import org.springframework.stereotype.Repository;

/**
 * @Author: 10785
 * @Date: 2019/11/26 12:41
 * @Version: 1.0
 */
@Repository
public interface SysUserRepository extends BaseRepository<SysUser,String> {

    /**
     * 根据userNo查询用户
     * @param userNo
     * @return
     */
    SysUser findByUserNo(String userNo);

}
