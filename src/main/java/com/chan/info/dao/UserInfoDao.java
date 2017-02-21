package com.chan.info.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.chan.info.model.UserInfo;

@Repository("userInfoDao")
@EnableTransactionManagement
public interface UserInfoDao {
	
	//查询人员信息
	@Transactional(rollbackFor = Exception.class)
	public UserInfo queryUserInfo(String username);

	// 增加人员信息
	@Transactional(rollbackFor = Exception.class)
	public int insertPerson(UserInfo userInfo);
	

}
