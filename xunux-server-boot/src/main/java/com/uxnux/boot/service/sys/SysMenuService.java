package com.uxnux.boot.service.sys;

import com.uxnux.boot.model.entity.sys.SysMenu;
import com.uxnux.boot.exception.ServiceException;


public interface SysMenuService {

    /**
     * 保存实体
     * @param entity
     * @return
     * @throws ServiceException
     */
	SysMenu saveSysMenu(SysMenu entity) throws ServiceException;

    /**
     * 删除实体
     * @param entity
     * @return
     * @throws ServiceException
     */
	void deleteSysMenu(SysMenu entity) throws ServiceException;

    /**
     * 根据id删除实体
     * @param id
     * @return
     * @throws ServiceException
     */
	void deleteSysMenu(String id) throws ServiceException;

    /**
     * 获得实体
     * @param id
     * @return
     * @throws ServiceException
     */
	SysMenu getSysMenu(String id) throws ServiceException;

}
