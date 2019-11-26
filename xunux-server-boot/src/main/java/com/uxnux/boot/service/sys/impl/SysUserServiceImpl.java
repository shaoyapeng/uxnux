package com.uxnux.boot.service.sys.impl;

import com.uxnux.boot.exception.ServiceException;
import com.uxnux.boot.model.entity.sys.SysUser;
import com.uxnux.boot.repository.sys.SysUserRepository;
import com.uxnux.boot.service.AbstractService;
import com.uxnux.boot.service.sys.SysUserService;
import com.uxnux.boot.aop.LogAnnotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;

/**
 * @Author: 10785
 * @Date: 2019/11/26 12:41
 * @Version: 1.0
 */
@Service("sysUserService")
public class SysUserServiceImpl extends AbstractService implements SysUserService {

	@Autowired
	private SysUserRepository sysUserRepository;

	@Override
	@LogAnnotation(describe="保存SysUser")
	public SysUser saveSysUser(SysUser entity)throws ServiceException {return sysUserRepository.save(entity);}

	@Override
	@LogAnnotation(describe="删除SysUser")
	public void deleteSysUser(SysUser entity) throws ServiceException {sysUserRepository.delete(entity);}

	@Override
	@LogAnnotation(describe="根据id删除SysUser")
	public void deleteSysUser(String id) throws ServiceException {sysUserRepository.deleteById(id);}

	@Override
	@LogAnnotation(describe="根据id获取SysUser")
	public SysUser getSysUser(String pkId) throws ServiceException {return sysUserRepository.findById(pkId).get();}

	@Override
	@LogAnnotation(describe="根据用户名获取SysUser")
	public SysUser getSysUserByUserNo(String userNo) throws ServiceException {
		return sysUserRepository.findByUserNo(userNo);
	}

}
