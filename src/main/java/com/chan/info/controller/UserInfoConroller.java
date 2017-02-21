package com.chan.info.controller;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chan.info.model.UserInfo;
import com.chan.info.service.UserInfoService;

@RestController
public class UserInfoConroller {

	@Resource
	private UserInfoService userInfoService;

	@RequestMapping("addPerson")
	public String personAdd(String username, String password, String name) {
		System.out.println("我正在添加人员信息！！！！！！！！！！！！！！！！");
		System.out.println("我获取到数据了么 username--- " + username);
		System.out.println("我获取到数据了么 password--- " + password);
		System.out.println("我获取到数据了么 name--- " + name);
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername(username);
		// 参数1 ：source 原密码
		// 参数2 ：salt 盐(相同的密码用不同的盐运算出来的加密串不同)
		// 参数3 ：hashIterations 散列次数 2 加密（ 加密（原密码） ）
		Md5Hash md5 = new Md5Hash(password, "", 1);
		System.out.println("我原来的密码是>>>>>>>>" + password);
		System.out.println("我md5后的密码是>>>>>>>>" + md5);
		userInfo.setPassword(md5.toString());
		userInfo.setName(name);
		userInfo.setSalt("");
		userInfo.setState((byte) 0);

		System.out.println("现在的人员信息是" + userInfo);

		int num = userInfoService.insertPerson(userInfo);
		
		System.out.println("我增加了多少条信息呢？？？？>>>>>>>>" + num);
		
		if (num != 0) {
			System.out.println("我添加成功了。。。。。。。。。。。。");
			return "添加成功了";
			
		} else {
			System.out.println("我添加失败了。。。。。。。。。。。。");
			return "添加失败了";
		}
	}
//	@RequiresPermissions("vip:del")
	@RequestMapping("delPerson")
	public String personAdd() {
		
		return "你在执行删除操作";
		
	}
	
	//使用MD5进行散列密码
	//21232f297a57a5a743894a0e4a801fc3		32位
	@Test
	public void testMD5() {
		String str = "admin";
		String salt = "";
		Md5Hash md5 = new Md5Hash(str, salt, 1);
		System.out.println(md5);
	}
	
	//使用SHA256进行散列密码
	//8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918		64位
	@Test
	public void testSHA256() {
		String str = "admin";
		String salt = "";
		String string = new Sha256Hash(str, salt).toString();
		System.out.println(string);
		
	}

}
