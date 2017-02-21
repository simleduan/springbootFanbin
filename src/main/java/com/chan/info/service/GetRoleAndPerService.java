package com.chan.info.service;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chan.info.dao.GetRoleAndPerDao;

@Service("getRoleAndPerService")
public class GetRoleAndPerService {

	@Resource
	private GetRoleAndPerDao getRoleAndPerDao;
	//根据username查询用户的角色信息
	@Transactional
	public List<Map<String,String>> getRoleByUsername(String username){
		return getRoleAndPerDao.getRoleByUsername(username);
	}
	
	//根据username查询该用户拥有的所有权限信息
	@Transactional(rollbackFor = Exception.class)
	public List<Map<String,String>> getPermissionByUsername(String username){
		return getRoleAndPerDao.getPermissionByUsername(username);
	}

}
