package com.uxnux.boot.service.sys;

import com.uxnux.boot.model.entity.sys.SysRole;
import com.uxnux.boot.exception.ServiceException;


public interface SysRoleService {

    /**
     * 保存实体
     * @param entity
     * @return
     * @throws ServiceException
     */
	SysRole saveSysRole(SysRole entity) throws ServiceException;

    /**
     * 删除实体
     * @param entity
     * @return
     * @throws ServiceException
     */
	void deleteSysRole(SysRole entity) throws ServiceException;

    /**
     * 根据id删除实体
     * @param id
     * @return
     * @throws ServiceException
     */
	void deleteSysRole(String id) throws ServiceException;

    /**
     * 获得实体
     * @param id
     * @return
     * @throws ServiceException
     */
	SysRole getSysRole(String id) throws ServiceException;

}
