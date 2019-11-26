package com.uxnux.boot.service.sys;

import com.uxnux.boot.model.entity.sys.SysUser;
import com.uxnux.boot.exception.ServiceException;


public interface SysUserService {

    /**
     * 保存实体
     * @param entity
     * @return
     * @throws ServiceException
     */
	SysUser saveSysUser(SysUser entity) throws ServiceException;

    /**
     * 删除实体
     * @param entity
     * @return
     * @throws ServiceException
     */
	void deleteSysUser(SysUser entity) throws ServiceException;

    /**
     * 根据id删除实体
     * @param id
     * @return
     * @throws ServiceException
     */
	void deleteSysUser(String id) throws ServiceException;

    /**
     * 获得实体
     * @param id
     * @return
     * @throws ServiceException
     */
	SysUser getSysUser(String id) throws ServiceException;

    /**
     * 根据用户名查询用户
     * @return
     * @throws ServiceException
     */
	SysUser getSysUserByUserNo(String userNo) throws ServiceException;

}
