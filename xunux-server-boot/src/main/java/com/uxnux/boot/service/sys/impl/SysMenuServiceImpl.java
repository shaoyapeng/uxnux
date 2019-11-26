package com.uxnux.boot.service.sys.impl;

import com.uxnux.boot.model.entity.sys.SysMenu;
import com.uxnux.boot.repository.sys.SysMenuRepository;
import com.uxnux.boot.service.AbstractService;
import com.uxnux.boot.aop.LogAnnotation;

import com.uxnux.boot.service.sys.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;

/**
 * @Author: 10785
 * @Date: 2019/11/26 12:41
 * @Version: 1.0
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends AbstractService implements SysMenuService {

	@Autowired
	private SysMenuRepository sysMenuRepository;
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	@LogAnnotation(describe="保存SysMenu")
	public SysMenu saveSysMenu(SysMenu entity) {return sysMenuRepository.save(entity);}

	@Override
	@LogAnnotation(describe="删除SysMenu")
	public void deleteSysMenu(SysMenu entity) {sysMenuRepository.delete(entity);}

	@Override
	@LogAnnotation(describe="根据id删除SysMenu")
	public void deleteSysMenu(String id) {sysMenuRepository.deleteById(id);}

	@Override
	@LogAnnotation(describe="根据id获取SysMenu")
	public SysMenu getSysMenu(String pkId) {return sysMenuRepository.findById(pkId).get();}

}
