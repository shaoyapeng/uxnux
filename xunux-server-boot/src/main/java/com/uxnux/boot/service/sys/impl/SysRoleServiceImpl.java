package com.uxnux.boot.service.sys.impl;

import com.uxnux.boot.model.entity.sys.SysRole;
import com.uxnux.boot.repository.sys.SysRoleRepository;
import com.uxnux.boot.service.AbstractService;
import com.uxnux.boot.aop.LogAnnotation;
import com.uxnux.boot.service.sys.SysRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;

/**
 * @Author: 10785
 * @Date: 2019/11/26 12:41
 * @Version: 1.0
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends AbstractService implements SysRoleService {

	@Autowired
	private SysRoleRepository sysRoleRepository;
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	@LogAnnotation(describe="保存SysRole")
	public SysRole saveSysRole(SysRole entity) {return sysRoleRepository.save(entity);}

	@Override
	@LogAnnotation(describe="删除SysRole")
	public void deleteSysRole(SysRole entity) {sysRoleRepository.delete(entity);}

	@Override
	@LogAnnotation(describe="根据id删除SysRole")
	public void deleteSysRole(String id) {sysRoleRepository.deleteById(id);}

	@Override
	@LogAnnotation(describe="根据id获取SysRole")
	public SysRole getSysRole(String pkId) {return sysRoleRepository.findById(pkId).get();}

}
