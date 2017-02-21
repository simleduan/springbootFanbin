package com.chan.info.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chan.info.dao.UserInfoDao;
import com.chan.info.model.UserInfo;

@Service("userInfoService")
public class UserInfoService {

	@Resource
	private UserInfoDao userInfoDao;
	//根据用户名查询用户是否存在
	@Transactional
	public UserInfo queryUserInfo(String username) {
		return userInfoDao.queryUserInfo(username);
	}
	//添加一条人员信息
	@Transactional
	public int insertPerson(UserInfo userInfo){
		return userInfoDao.insertPerson(userInfo);
	}

}
