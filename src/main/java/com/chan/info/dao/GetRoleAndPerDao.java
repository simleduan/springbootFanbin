package com.chan.info.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Repository("getRoleAndPerDao")
@EnableTransactionManagement
public interface GetRoleAndPerDao {
	//根据username查询用户的角色信息
	@Transactional(rollbackFor = Exception.class)
	public List<Map<String,String>> getRoleByUsername(String username);
	
	//根据username查询该用户拥有的所有权限信息
	@Transactional(rollbackFor = Exception.class)
	public List<Map<String,String>> getPermissionByUsername(String username);

}
