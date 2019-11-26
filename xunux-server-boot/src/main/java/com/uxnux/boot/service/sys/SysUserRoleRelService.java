package com.uxnux.boot.service.sys;

import com.uxnux.boot.model.entity.sys.SysUserRoleRel;
import com.uxnux.boot.exception.ServiceException;


public interface SysUserRoleRelService {

    /**
     * 保存实体
     * @param entity
     * @return
     * @throws ServiceException
     */
	SysUserRoleRel saveSysUserRoleRel(SysUserRoleRel entity) throws ServiceException;

    /**
     * 删除实体
     * @param entity
     * @return
     * @throws ServiceException
     */
	void deleteSysUserRoleRel(SysUserRoleRel entity) throws ServiceException;

    /**
     * 根据id删除实体
     * @param id
     * @return
     * @throws ServiceException
     */
	void deleteSysUserRoleRel(String id) throws ServiceException;

    /**
     * 获得实体
     * @param id
     * @return
     * @throws ServiceException
     */
	SysUserRoleRel getSysUserRoleRel(String id) throws ServiceException;

}
