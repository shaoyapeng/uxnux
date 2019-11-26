package com.uxnux.boot.service.sys.impl;

import com.uxnux.boot.model.entity.sys.SysUserRoleRel;
import com.uxnux.boot.repository.sys.SysUserRoleRelRepository;
import com.uxnux.boot.service.AbstractService;
import com.uxnux.boot.service.sys.SysUserRoleRelService;
import com.uxnux.boot.aop.LogAnnotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;

/**
 * @Author: 10785
 * @Date: 2019/11/26 12:41
 * @Version: 1.0
 */
@Service("sysUserRoleRelService")
public class SysUserRoleRelServiceImpl extends AbstractService implements SysUserRoleRelService {

	@Autowired
	private SysUserRoleRelRepository sysUserRoleRelRepository;
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	@LogAnnotation(describe="保存SysUserRoleRel")
	public SysUserRoleRel saveSysUserRoleRel(SysUserRoleRel entity) {return sysUserRoleRelRepository.save(entity);}

	@Override
	@LogAnnotation(describe="删除SysUserRoleRel")
	public void deleteSysUserRoleRel(SysUserRoleRel entity) {sysUserRoleRelRepository.delete(entity);}

	@Override
	@LogAnnotation(describe="根据id删除SysUserRoleRel")
	public void deleteSysUserRoleRel(String id) {sysUserRoleRelRepository.deleteById(id);}

	@Override
	@LogAnnotation(describe="根据id获取SysUserRoleRel")
	public SysUserRoleRel getSysUserRoleRel(String pkId) {return sysUserRoleRelRepository.findById(pkId).get();}

}
