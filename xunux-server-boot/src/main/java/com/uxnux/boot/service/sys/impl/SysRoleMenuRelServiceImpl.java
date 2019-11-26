package com.uxnux.boot.service.sys.impl;

import com.uxnux.boot.model.entity.sys.SysRoleMenuRel;
import com.uxnux.boot.repository.sys.SysRoleMenuRelRepository;
import com.uxnux.boot.service.AbstractService;
import com.uxnux.boot.service.sys.SysRoleMenuRelService;
import com.uxnux.boot.aop.LogAnnotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;

/**
 * @Author: 10785
 * @Date: 2019/11/26 12:41
 * @Version: 1.0
 */
@Service("sysRoleMenuRelService")
public class SysRoleMenuRelServiceImpl extends AbstractService implements SysRoleMenuRelService {

	@Autowired
	private SysRoleMenuRelRepository sysRoleMenuRelRepository;
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	@LogAnnotation(describe="保存SysRoleMenuRel")
	public SysRoleMenuRel saveSysRoleMenuRel(SysRoleMenuRel entity) {return sysRoleMenuRelRepository.save(entity);}

	@Override
	@LogAnnotation(describe="删除SysRoleMenuRel")
	public void deleteSysRoleMenuRel(SysRoleMenuRel entity) {sysRoleMenuRelRepository.delete(entity);}

	@Override
	@LogAnnotation(describe="根据id删除SysRoleMenuRel")
	public void deleteSysRoleMenuRel(String id) {sysRoleMenuRelRepository.deleteById(id);}

	@Override
	@LogAnnotation(describe="根据id获取SysRoleMenuRel")
	public SysRoleMenuRel getSysRoleMenuRel(String pkId) {return sysRoleMenuRelRepository.findById(pkId).get();}

}
