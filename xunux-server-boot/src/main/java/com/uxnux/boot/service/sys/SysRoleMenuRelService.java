package com.uxnux.boot.service.sys;

import com.uxnux.boot.model.entity.sys.SysRoleMenuRel;
import com.uxnux.boot.exception.ServiceException;


public interface SysRoleMenuRelService {

    /**
     * 保存实体
     * @param entity
     * @return
     * @throws ServiceException
     */
	SysRoleMenuRel saveSysRoleMenuRel(SysRoleMenuRel entity) throws ServiceException;

    /**
     * 删除实体
     * @param entity
     * @return
     * @throws ServiceException
     */
	void deleteSysRoleMenuRel(SysRoleMenuRel entity) throws ServiceException;

    /**
     * 根据id删除实体
     * @param id
     * @return
     * @throws ServiceException
     */
	void deleteSysRoleMenuRel(String id) throws ServiceException;

    /**
     * 获得实体
     * @param id
     * @return
     * @throws ServiceException
     */
	SysRoleMenuRel getSysRoleMenuRel(String id) throws ServiceException;

}
